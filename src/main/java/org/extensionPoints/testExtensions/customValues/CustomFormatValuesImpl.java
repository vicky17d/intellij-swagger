package org.extensionPoints.testExtensions.customValues;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomFields;
import org.extensionPoints.CustomValues;
import org.zalando.intellij.swagger.completion.field.model.common.Field;
import org.zalando.intellij.swagger.completion.field.model.common.StringField;
import org.zalando.intellij.swagger.completion.value.model.common.StringValue;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

public class CustomFormatValuesImpl implements CustomValues {
  @Override
  public List<Value> getCustomValues() {
    return ImmutableList.of(
            new StringValue("x-gw-biginteger"),
            new StringValue("x-gw-bigdecimal"),
            new StringValue("x-gw-money")
    );
  }
}
