package org.zalando.intellij.swagger.extensions.completion.swagger;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.openapi.extensions.ExtensionPointName;
import org.zalando.intellij.swagger.completion.SwaggerCompletionHelper;
import org.zalando.intellij.swagger.completion.field.FieldCompletion;

import java.util.Optional;

public interface SwaggerJsonCustomKeyCompletionFactory {
    ExtensionPointName<SwaggerJsonCustomKeyCompletionFactory> EP_NAME = ExtensionPointName.create("org.zalando.intellij.swagger.customSwaggerJsonKeyFactory");

    Optional<FieldCompletion> from(final SwaggerCompletionHelper completionHelper,
                                   final CompletionResultSet completionResultSet);
}
