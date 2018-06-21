package org.extensionPoints.testExtensions;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomFormatValues;
import org.zalando.intellij.swagger.completion.value.model.common.StringValue;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

//todo vd to be removed
public class CustomFormatValuesImpl extends CustomFormatValues {
  @Override
  public List<Value> getCustomFormatValues() {
    return ImmutableList.of(
            new StringValue("gw-bigdecimal"),
            new StringValue("gw-biginteger"),
            new StringValue("gw-money")
    );
  }
}
