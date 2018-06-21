package org.extensionPoints;

import com.intellij.openapi.extensions.ExtensionPointName;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

//todo vd to be removed
public abstract class CustomFormatValues {
  public static final ExtensionPointName<CustomFormatValues> EP_NAME = ExtensionPointName.create("org.gw.swagger.customFormatValues");
  public abstract List<Value> getCustomFormatValues();
}
