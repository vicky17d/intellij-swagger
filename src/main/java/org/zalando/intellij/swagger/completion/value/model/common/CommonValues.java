package org.zalando.intellij.swagger.completion.value.model.common;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomFormatValues;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonValues {

    public static List<Value> types() {
        return ImmutableList.of(
                new StringValue("object"),
                new StringValue("string"),
                new StringValue("number"),
                new StringValue("integer"),
                new StringValue("boolean"),
                new StringValue("array")
        );
    }

    public static List<Value> booleans() {
        return ImmutableList.of(
                new BooleanValue("true"),
                new BooleanValue("false")
        );
    }

    private static List<Value> outOfTheBoxFormats() {
        return ImmutableList.of(
                new StringValue("int32"),
                new StringValue("int64"),
                new StringValue("float"),
                new StringValue("double"),
                new StringValue("byte"),
                new StringValue("binary"),
                new StringValue("date"),
                new StringValue("date-time"),
                new StringValue("password"),
                new StringValue("email"),
                new StringValue("uuid")
        );
    }
    
    public static List<Value> formats() {
        return new ImmutableList.Builder<Value>()
                .addAll(outOfTheBoxFormats())
                .addAll(Arrays.stream(CustomFormatValues.EP_NAME.getExtensions()).flatMap(customFormatValues -> customFormatValues.getCustomFormatValues().stream()).collect(Collectors.toList()))
                .build();
    }
}
