package org.extensionPoints.testExtensions.customFields;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomFields;
import org.zalando.intellij.swagger.completion.field.model.common.Field;
import org.zalando.intellij.swagger.completion.field.model.common.StringField;

import java.util.List;

public class CustomRootFieldsImpl implements CustomFields {
  @Override
  public List<Field> getCustomFields() {
    return ImmutableList.of(
            new StringField("x-gw-combine"),
            new StringField("x-gw-schema-import"),
            new StringField("x-gw-apihandlers"),
            new StringField("x-gw-permissions"),
            new StringField("x-gw-runlevel"),
            new StringField("x-gw-serialization")
    );
  }
}
