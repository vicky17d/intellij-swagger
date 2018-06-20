package org.extensionPoints.testExtensions.customValues;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomValues;
import org.zalando.intellij.swagger.completion.value.model.common.StringValue;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

public class CustomTypeValuesImpl implements CustomValues {
  @Override
  public List<Value> getCustomValues() {
    return ImmutableList.of(
            new StringValue("x-gw-type11"),
            new StringValue("x-gw-type22"),
            new StringValue("x-gw-type33")
    );
  }
}
