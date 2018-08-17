package org.zalando.intellij.swagger.reference;

import com.intellij.patterns.PsiElementPattern;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceRegistrar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.yaml.YAMLLanguage;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLMapping;
import org.jetbrains.yaml.psi.YAMLQuotedText;
import org.jetbrains.yaml.psi.YAMLValue;
import org.zalando.intellij.swagger.traversal.YamlTraversal;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class YamlReferenceContributor extends ReferenceContributor {

    public YamlReferenceContributor() {
        super(new YamlTraversal());
    }

    @Override
    public void registerReferenceProviders(@NotNull final PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(localDefinitionsPattern(), createLocalReferenceProvider());
        registrar.registerReferenceProvider(localDefinitionsPattern2(), createLocalReferenceProvider());  //todo vd to be removed?

        registrar.registerReferenceProvider(filePattern(), createFileReferenceProvider());
        registrar.registerReferenceProvider(filePattern3(), createFileReferenceProvider());  //todo vd in progress
        registrar.registerReferenceProvider(tagsPattern(), createTagsReferenceProvider());
    }

    private PsiElementPattern.Capture<YAMLQuotedText> localDefinitionsPattern() {
        return psiElement(YAMLQuotedText.class)
                .withParent(psiElement(YAMLKeyValue.class).withName(SwaggerConstants.REF_KEY))
                .withText(StandardPatterns.string().contains(SwaggerConstants.REFERENCE_PREFIX))
                .andNot(StandardPatterns.string().matches(".ya?ml"))
                .withLanguage(YAMLLanguage.INSTANCE);
    }

    private PsiElementPattern.Capture<YAMLQuotedText> localDefinitionsPattern2() {  //todo vd to be removed?
        return psiElement(YAMLQuotedText.class)
                .withParent(psiElement(YAMLKeyValue.class).withName("x-gw-runlevel"))
                .withText(StandardPatterns.string().contains(SwaggerConstants.REFERENCE_PREFIX))
                .andNot(StandardPatterns.string().matches(".ya?ml"))
                .withLanguage(YAMLLanguage.INSTANCE);
    }

    private PsiElementPattern.Capture<YAMLValue> filePattern() {
        return psiElement(YAMLValue.class)
                .withText(StandardPatterns.string().matches("(.)*.ya?ml(.)*"))
                .withLanguage(YAMLLanguage.INSTANCE);
    }

    private PsiElementPattern.Capture<YAMLValue> filePattern3() {
        return psiElement(YAMLValue.class)
                .withSuperParent(3, psiElement(YAMLKeyValue.class).withName("x-gw-combine"));
    }

    private PsiElementPattern.Capture<YAMLValue> tagsPattern() {
        return swagger(psiElement(YAMLValue.class).inside(psiElement(YAMLKeyValue.class).withName(SwaggerConstants.TAGS_KEY))
                .withLanguage(YAMLLanguage.INSTANCE));
    }

    private <T extends PsiElement> PsiElementPattern.Capture<T> swagger(final PsiElementPattern.Capture<T> pattern) {
        return pattern.
                inside(psiElement(YAMLMapping.class).withChild(psiElement().withName(SwaggerConstants.SWAGGER)));
    }
}
