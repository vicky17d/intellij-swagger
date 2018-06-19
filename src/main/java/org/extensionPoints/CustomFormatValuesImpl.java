package org.extensionPoints;

import com.google.common.collect.ImmutableList;
import org.zalando.intellij.swagger.completion.value.model.common.StringValue;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

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
