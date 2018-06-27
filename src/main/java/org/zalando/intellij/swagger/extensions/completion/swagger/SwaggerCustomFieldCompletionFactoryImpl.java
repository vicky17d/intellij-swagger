package org.zalando.intellij.swagger.extensions.completion.swagger;

import com.intellij.codeInsight.completion.CompletionResultSet;
import org.zalando.intellij.swagger.completion.CompletionHelper;
import org.zalando.intellij.swagger.completion.SwaggerCompletionHelper;
import org.zalando.intellij.swagger.completion.field.FieldCompletion;

import java.util.Optional;

public class SwaggerCustomFieldCompletionFactoryImpl implements SwaggerCustomFieldCompletionFactory {

  public Optional<FieldCompletion> from(final SwaggerCompletionHelper completionHelper,
                                        final CompletionResultSet completionResultSet) {
    if (completionHelper.completeRootKey()) {
      return Optional.of(new CustomRootFieldCompletion(completionHelper, completionResultSet));
    } else {
      return Optional.empty();
    }
  }

  private static class CustomRootFieldCompletion extends FieldCompletion {

    CustomRootFieldCompletion(final CompletionHelper completionHelper, final CompletionResultSet completionResultSet) {
      super(completionHelper, completionResultSet);
    }
    public void fill() {
      CustomSwaggerFieldsValues.root().forEach(this::addUnique);
    }
  }

}
