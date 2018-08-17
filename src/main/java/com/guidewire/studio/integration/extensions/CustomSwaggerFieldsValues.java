package com.guidewire.studio.integration.extensions;

import com.google.common.collect.ImmutableList;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.zalando.intellij.swagger.completion.field.model.common.Field;
import org.zalando.intellij.swagger.completion.field.model.common.StringField;
import org.zalando.intellij.swagger.completion.value.model.common.StringValue;
import org.zalando.intellij.swagger.completion.value.model.common.Value;

import java.util.ArrayList;
import java.util.List;

public class CustomSwaggerFieldsValues {
  public static List<Field> x_gw_root() {
    return ImmutableList.of(

            new StringField("x-gw-combine"),
            new StringField("x-gw-schema-import"),
            new StringField("x-gw-apihandlers"),
            new StringField("x-gw-permissions"),
            new StringField("x-gw-runlevel"),
            new StringField("x-gw-serialization"),
            new StringField("$schema"),
            new StringField("schemaName"),
            new StringField("mappers")
    );
  }

  public static List<Value> x_gw_format() {
    return ImmutableList.of(

            new StringValue("x-gw-bigdecimal"),
            new StringValue("x-gw-biginteger"),
            new StringValue("x-gw-money")

    );
  }

  public static List<Value> x_gw_runlevel() {
    return ImmutableList.of(

            new StringValue("starting"),
            new StringValue("maintenance"),
            new StringValue("daemons"),
            new StringValue("multiuser")

    );
  }

  public static List<Value> x_gw_permissions() {
    final Project project = ProjectManager.getInstance().getOpenProjects()[0];  //todo vd.. should work in multiple studios open from same one. fix this
//    Collection<String> permCodes = FileBasedIndex.getInstance().getAllKeys(SystemPermissionsTypecodeIndex.INDEX_ID, project);
    List<Value> values = new ArrayList<>();
//    for (String typecode : permCodes) {
//      values.add(new StringValue(typecode));
//    }
    return values;
  }
}