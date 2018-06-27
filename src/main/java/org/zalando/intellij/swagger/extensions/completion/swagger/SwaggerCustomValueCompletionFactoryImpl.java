package org.zalando.intellij.swagger.extensions.completion.swagger;

import com.intellij.codeInsight.completion.CompletionResultSet;
import org.zalando.intellij.swagger.completion.CompletionHelper;
import org.zalando.intellij.swagger.completion.SwaggerCompletionHelper;
import org.zalando.intellij.swagger.completion.value.ValueCompletion;
import org.zalando.intellij.swagger.traversal.path.swagger.PathResolver;

import java.util.Optional;

public class SwaggerCustomValueCompletionFactoryImpl implements SwaggerCustomValueCompletionFactory {
    @Override
    public Optional<ValueCompletion> from(SwaggerCompletionHelper completionHelper, CompletionResultSet completionResultSet) {
        if (completionHelper.completeFormatValue()) {
            return Optional.of(new CustomFormatValueCompletion(completionHelper, completionResultSet));
        } else if (completeCustomRunLevelValue(completionHelper)) {
            return Optional.of(new CustomRunLevelValueCompletion(completionHelper, completionResultSet));
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
            CustomSwaggerFieldsValues.format().forEach(this::addValue);
        }
    }

    private static class CustomRunLevelValueCompletion extends ValueCompletion {

        CustomRunLevelValueCompletion(final CompletionHelper completionHelper, final CompletionResultSet completionResultSet) {
            super(completionHelper, completionResultSet);
        }

        @Override
        public void fill() {
            CustomSwaggerFieldsValues.runlevel().forEach(this::addValue);
        }
    }

    private  boolean completeCustomRunLevelValue(CompletionHelper completionHelper) {
        return (completionHelper.hasPath("$.**.x-gw-runlevel"));
    }
    
//    private  boolean completeCustomRunLevelValue(CompletionHelper completionHelper) {
//        return ((SwaggerCompletionHelper)completionHelper).getPathResolver().hasPath(completionHelper.getPsiElement(), "$.**.x-gw-runlevel");
//    }

//    default boolean isFormatValue(final PsiElement psiElement) {
//        return hasPath(psiElement, "$.**.format");
//    }
    
    private static class PathResolverImpl implements PathResolver {
//        public boolean hasPath(final PsiElement psiElement, final String pathExpression) {
//            return new PathFinder().isInsidePath(psiElement, pathExpression);
//        }
    }
}


