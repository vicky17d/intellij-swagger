package org.zalando.intellij.swagger.reference;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class CustomFileReference extends PsiReferenceBase<PsiElement> {
    private final String originalRefValue;
    private final PsiElement psiElement;

    public CustomFileReference(@NotNull PsiElement element, @NotNull String originalRefValue) {
        super(element);
        this.originalRefValue = originalRefValue;
        this.psiElement = element;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return resolveCustomReference(originalRefValue, psiElement);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }


    private PsiElement resolveCustomReference(String originalRefValue, PsiElement psiElement) {
        String containingFilePath = psiElement.getContainingFile().getVirtualFile().getPath();


        //todo we need to check a few things here
        // first is that the containing file  path should match config/integration/apis
        // second is the value should not end with .yaml 
        //third is the value directory path should be relative to config/integration/apis
        String regex = ".*config[\\/]integration[\\/]apis[\\/].*";
        if (containingFilePath.matches(regex) && !originalRefValue.endsWith("yaml")) {
            YamlValuePath yamlValuePath = new YamlValuePath(originalRefValue);
            String fileName = yamlValuePath.getFileName();


            PsiFile containingFile = psiElement.getContainingFile();
            PsiDirectory containingDirectory = containingFile.getParent();
            if (containingDirectory != null) {
                final ProjectRootManager projectRootManager = ProjectRootManager.getInstance(psiElement.getProject());
                final VirtualFile root = projectRootManager.getFileIndex().getContentRootForFile(containingDirectory.getVirtualFile());


                Project project = getElement().getProject();
                final PsiManager psiManager = PsiManager.getInstance(project);

                String fullPathOfFileToBeNavigatedTo = yamlValuePath.getFullPath();
                System.out.println("Find the file: ........ " + fullPathOfFileToBeNavigatedTo);

                PsiFile[] finalFiles = FilenameIndex.getFilesByName(project, fileName, GlobalSearchScope.allScope(project));
                PsiFile finalFile = null;
                if (finalFiles.length > 0) {
                    Arrays.stream(finalFiles).forEach(s -> System.out.println(s.getVirtualFile().getCanonicalPath()));  //todo vd to be removed
                    //todo vd should also work if the file is in the base folder that is config/integration/apis
                        finalFile = Arrays.stream(finalFiles).filter(file -> file.getVirtualFile().getCanonicalPath().matches(regex) && file.getVirtualFile().getCanonicalPath().contains(yamlValuePath.getFullPath())).findFirst().orElse(null);
                }
                if (finalFile != null) {
                    System.out.println(finalFile);
                    System.out.println("WELL WELL WELL");
                    return psiManager.findFile(finalFile.getVirtualFile());
                }

                System.out.println("################# Did not work out.. #################");  //todo vd.. add this to logger
            }

        }
        return null;
    }
}
