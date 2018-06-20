package org.extensionPoints;

import com.intellij.openapi.extensions.AbstractExtensionPointBean;
import com.intellij.openapi.util.LazyInstance;
import com.intellij.util.KeyedLazyInstance;
import com.intellij.util.xmlb.annotations.Attribute;
import org.jetbrains.annotations.NotNull;

public class CustomValuesEP<T> extends AbstractExtensionPointBean implements KeyedLazyInstance<T> {

    @Attribute("key")
    public String key;

    @Attribute("implementationClass")
    public String implementationClass;

    private final LazyInstance<T> myHandler = new LazyInstance<T>() {
        @Override
        protected Class<T> getInstanceClass() throws ClassNotFoundException {
            return findClass(implementationClass);
        }
    };

    @NotNull
    @Override
    public T getInstance() {
        return myHandler.getValue();
    }

    @Override
    public String getKey() {
        return key;
    }

}
