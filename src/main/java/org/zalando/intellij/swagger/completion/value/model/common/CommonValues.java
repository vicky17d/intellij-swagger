package org.zalando.intellij.swagger.completion.value.model.common;

import com.google.common.collect.ImmutableList;
import org.extensionPoints.CustomValues;
import org.extensionPoints.CustomValuesEP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonValues {

    public static List<Value> types() {
        List<Value> outOfTheBox = Arrays.asList(
                new StringValue("object"),
                new StringValue("string"),
                new StringValue("number"),
                new StringValue("integer"),
                new StringValue("boolean"),
                new StringValue("array")
        );
        return new ImmutableList.Builder<Value>()
                .addAll(outOfTheBox)
                .addAll(customValues("type"))
                .build();
    }

    public static List<Value> booleans() {
        List<Value> outOfTheBox = Arrays.asList(
                new BooleanValue("true"),
                new BooleanValue("false")
        );
        return new ImmutableList.Builder<Value>()
                .addAll(outOfTheBox)
                .addAll(customValues("boolean"))
                .build();
    }

    public static List<Value> formats() {
        List<Value> outOfTheBox = Arrays.asList(
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
        return new ImmutableList.Builder<Value>()
                .addAll(outOfTheBox)
                .addAll(customValues("format"))
                .build();
    }

    private static List<Value> customValues(String key) {

        CustomValues impl;
        List<Value> customValues = new ArrayList<>();
        final CustomValuesEP<CustomValues>[] eps = CustomValues.EP_NAME.getExtensions();
        for (CustomValuesEP<CustomValues> ep : eps) {
            if (ep.key.equals(key)) {
                impl = ep.getInstance();
                customValues.addAll(impl.getCustomValues());
            }
        }
        return customValues;
    }
}
