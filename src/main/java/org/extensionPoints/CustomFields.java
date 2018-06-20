package org.extensionPoints;

import com.intellij.openapi.extensions.ExtensionPointName;
import org.zalando.intellij.swagger.completion.field.model.common.Field;

import java.util.List;

public interface CustomFields {
  ExtensionPointName<CustomFieldsEP> EP_NAME = ExtensionPointName.create("org.gw.swagger.customFields");
  List<Field> getCustomFields();
}
