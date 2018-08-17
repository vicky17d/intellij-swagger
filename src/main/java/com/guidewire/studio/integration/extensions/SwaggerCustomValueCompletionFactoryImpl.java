package com.guidewire.studio.integration.extensions;

import com.intellij.codeInsight.completion.CompletionResultSet;
import org.zalando.intellij.swagger.completion.CompletionHelper;
import org.zalando.intellij.swagger.completion.SwaggerCompletionHelper;
import org.zalando.intellij.swagger.completion.value.ValueCompletion;
import org.zalando.intellij.swagger.extensions.completion.swagger.SwaggerCustomValueCompletionFactory;

import java.util.Optional;

public class SwaggerCustomValueCompletionFactoryImpl implements SwaggerCustomValueCompletionFactory {
  @Override
  public Optional<ValueCompletion> from(SwaggerCompletionHelper completionHelper, CompletionResultSet completionResultSet) {
    if (completionHelper.completeFormatValue()) {
      return Optional.of(new CustomFormatValueCompletion(completionHelper, completionResultSet));
    } else if (completeCustomRunLevelValue(completionHelper)) {
      return Optional.of(new CustomRunLevelValueCompletion(completionHelper, completionResultSet));
    } else if (completeCustomPermissionsValue(completionHelper)) {
      return Optional.of(new CustomPermissionsValueCompletion(completionHelper, completionResultSet));
    } else {
      return Optional.empty();
    }
  }

  private static class CustomFormatValueCompletion extends ValueCompletion {

    CustomFormatValueCompletion(final CompletionHelper completionHelper, final CompletionResultSet completionResultSet) {
      super(completionHelper, completionResultSet);
    }

    @Override
    public void fill() {
      CustomSwaggerFieldsValues.x_gw_format().forEach(this::addValue);
    }
  }

  private boolean completeCustomRunLevelValue(CompletionHelper completionHelper) {
    return (completionHelper.hasPath("$.**.x-gw-runlevel"));
  }

  private static class CustomRunLevelValueCompletion extends ValueCompletion {

    CustomRunLevelValueCompletion(final CompletionHelper completionHelper, final CompletionResultSet completionResultSet) {
      super(completionHelper, completionResultSet);
    }

    @Override
    public void fill() {
      CustomSwaggerFieldsValues.x_gw_runlevel().forEach(this::addValue);
    }
  }

  private boolean completeCustomPermissionsValue(CompletionHelper completionHelper) {
    return (completionHelper.hasPath("$.**.x-gw-permissions"));
  }

  private static class CustomPermissionsValueCompletion extends ValueCompletion {

    CustomPermissionsValueCompletion(final CompletionHelper completionHelper, final CompletionResultSet completionResultSet) {
      super(completionHelper, completionResultSet);
    }

    @Override
    public void fill() {
      CustomSwaggerFieldsValues.x_gw_permissions().forEach(this::addValue);
    }
  }
}


