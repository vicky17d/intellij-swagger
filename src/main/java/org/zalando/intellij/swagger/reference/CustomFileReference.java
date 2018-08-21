package org.zalando.intellij.swagger.reference;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zalando.intellij.swagger.traversal.path.PathFinder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomFileReference extends FileReference {
    private final String originalRefValue;
//    private final PsiElement psiElement;  //todo vd .. change to getElement()

    public CustomFileReference(@NotNull PsiElement element, @NotNull String originalRefValue) {
        super(element, originalRefValue);
        this.originalRefValue = originalRefValue;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        final String relativePath = StringUtils.substringBefore(originalRefValue, "#/");

        if (relativePath.equals(originalRefValue)) {
            return resolveCustomReference(originalRefValue, getElement());
        }

        return resolveFileReferenceWithSubPath(relativePath);
    }


    public PsiElement resolveCustomReference(String originalRefValue, PsiElement psiElement) {
        String containingFilePath = psiElement.getContainingFile().getVirtualFile().getPath();

        //todo we need to check a few things here
        // first is that the containing file  path should match config/integration/apis
        // second is the value should not end with .yaml 
        //third is the value directory path should be relative to config/integration/apis
        if (containingFilePath.matches(".*config[\\/]integration[\\/].*") && !originalRefValue.endsWith("yaml")) {
            String fileName = resolveGwFileReference(originalRefValue, containingFilePath);
            if (!StringUtils.isEmpty(fileName)) {
                return resolveExactFileReference(fileName);
            }
            return null;
        } else {
            return resolveExactFileReference(originalRefValue);
        }
    }

    @Nullable
    private PsiElement resolveExactFileReference(String relativePath) {
        return getReferencedFile(relativePath)
                .flatMap(referencedFile -> new PathFinder().findByPathFrom("$", referencedFile))
                .orElse(null);
    }

    @Nullable
    private PsiElement resolveFileReferenceWithSubPath(String relativePath) {
        String textAfterRelativePath = StringUtils.substringAfter(originalRefValue, relativePath);
        final String pathExpression = Arrays.stream(
                textAfterRelativePath
                        .substring(2)
                        .split("/"))
                .map(s -> s.replace(".", "\\."))
                .collect(Collectors.joining(".", "$.", ""));

        return getReferencedFile(relativePath)
                .flatMap(referencedFile -> new PathFinder().findByPathFrom(pathExpression, referencedFile))
                .orElse(null);
    }

    private Optional<PsiFile> getReferencedFile(final String relativePath) {

        final Optional<VirtualFile> baseDir = Optional.ofNullable(getElement())
                .map(PsiElement::getContainingFile)
                .map(PsiFile::getVirtualFile)
                .map(VirtualFile::getParent);

        final PsiManager psiManager = PsiManager.getInstance(getElement().getProject());

        return baseDir
                .map(dir -> dir.findFileByRelativePath(relativePath))
                .map(psiManager::findFile);
    }

    public String resolveGwFileReference(String originalRefValue, String containingFilePath) {
        YamlValuePath yamlValuePath = new YamlValuePath(originalRefValue);
        if (StringUtils.isEmpty(originalRefValue)) {
            return null;
        }
        boolean gwFile = containingFilePath.matches(".*config[\\/]integration[\\/]apis[\\/].*");  //todo 1 vd add apis?? done
        if (gwFile && !originalRefValue.endsWith(".yaml")) {
            String relativeLocationOfContainingFile = containingFilePath.substring(containingFilePath.indexOf("integration/api") + "integration/api/".length() + 1, containingFilePath.lastIndexOf("/"));   //todo 2 vd Use groups here.. also take care of windows stuff here
            //todo 3 vd.. also change api to apis in the above line

            String valueParentPath = yamlValuePath.getParentPath();

            String fileName = yamlValuePath.getFileName();
            if (StringUtils.isEmpty(valueParentPath)) {
                return fileName;
            } else {
                Path relativize = Paths.get(relativeLocationOfContainingFile).relativize(Paths.get(valueParentPath));

                String valueFileName = StringUtils.isEmpty(relativize.toString()) ? fileName : relativize + "/" + fileName;

                if (!StringUtils.isEmpty(valueFileName)) {
                    return valueFileName;
                }
            }

        }
        return null;
    }
}
