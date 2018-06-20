package org.extensionPoints.testExtensions.customFields;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomFields;
import org.zalando.intellij.swagger.completion.field.model.common.Field;
import org.zalando.intellij.swagger.completion.field.model.common.StringField;

import java.util.List;

public class CustomOperationFieldsImpl implements CustomFields {
  @Override
  public List<Field> getCustomFields() {
    return ImmutableList.of(
            new StringField("x-gw-apihandler"),
            new StringField("x-gw-authenticated"),
            new StringField("x-gw-permissions"),
            new StringField("x-gw-runlevel"),
            new StringField("x-gw-serialization"),
            new StringField("x-gw-reserve-db-connection"),
            new StringField("x-gw-extensions")
    );
  }
}
