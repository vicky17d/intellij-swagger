package org.extensionPoints;

import com.intellij.openapi.extensions.ExtensionPointName;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

public interface CustomValues {
    ExtensionPointName<CustomValuesEP> EP_NAME = ExtensionPointName.create("org.gw.swagger.customValues");
    List<Value> getCustomValues();
}
