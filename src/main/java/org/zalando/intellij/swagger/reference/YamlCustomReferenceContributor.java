package org.zalando.intellij.swagger.reference;

import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLValue;
import org.zalando.intellij.swagger.StringUtils;
import org.zalando.intellij.swagger.traversal.YamlTraversal;

import java.util.Optional;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class YamlCustomReferenceContributor extends PsiReferenceContributor {

//    public YamlCustomReferenceContributor() {
//        super(new YamlTraversal());
//    }

    @Override
    public void registerReferenceProviders(@NotNull final PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(customPattern(), createFileReferenceProvider());  //todo vd in progress
    }

    private PsiElementPattern.Capture<YAMLValue> customPattern() {
        return psiElement(YAMLValue.class)
                .withSuperParent(3, psiElement(YAMLKeyValue.class).withName("x-gw-combine"));
    }

    PsiReferenceProvider createFileReferenceProvider() {
        return new PsiReferenceProvider() {
            @NotNull
            @Override
            public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                         @NotNull ProcessingContext context) {
                return Optional.ofNullable(element.getText())
                        .map(text -> new PsiReference[]{
                                new CustomFileReference(
                                        element,
                                        StringUtils.removeAllQuotes(text))
                        }).orElse(CustomFileReference.EMPTY_ARRAY);
            }
        };
    }

}
