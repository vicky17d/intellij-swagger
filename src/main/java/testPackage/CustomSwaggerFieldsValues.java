package testPackage;

import com.google.common.collect.ImmutableList;
import org.zalando.intellij.swagger.completion.field.model.common.Field;
import org.zalando.intellij.swagger.completion.field.model.common.StringField;
import org.zalando.intellij.swagger.completion.value.model.common.StringValue;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.List;

public class CustomSwaggerFieldsValues {

    public static List<Field> root() {
        return ImmutableList.of(

            new StringField("x-gw-combine"),
            new StringField("x-gw-schema-import"),
            new StringField("x-gw-apihandlers"),
            new StringField("x-gw-permissions"),
            new StringField("x-gw-runlevel"),
            new StringField("x-gw-serialization")
        );
    }

    public static List<Value> format() {
        return ImmutableList.of(

                new StringValue("x-gw-bigdecimal"),
                new StringValue("x-gw-biginteger"),
                new StringValue("x-gw-money")

        );
    }
    
    public static List<Value> runlevel() {
        return ImmutableList.of(

                new StringValue("starting"),
                new StringValue("maintenance"),
                new StringValue("daemons"),
                new StringValue("multiuser")

        );
    }
}