package org.zalando.intellij.swagger.reference;

import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomFileReference extends FileReference {
    private final String originalRefValue;
    private final PsiElement psiElement;

    public CustomFileReference(@NotNull PsiElement element, @NotNull String originalRefValue) {
        super(element, originalRefValue);
        this.originalRefValue = originalRefValue;
        this.psiElement = element;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return resolveCustomReference(originalRefValue, psiElement);
    }


    private PsiElement resolveCustomReference(String originalRefValue, PsiElement psiElement) {
        String containingFilePath = psiElement.getContainingFile().getVirtualFile().getPath();


        //todo we need to check a few things here
        // first is that the containing file  path should match config/integration/apis
        // second is the value should not end with .yaml 
        //third is the value directory path should be relative to config/integration/apis
        if (containingFilePath.matches(".*config[\\/]integration[\\/]apis[\\/].*") && !originalRefValue.endsWith("yaml")) {
            YamlValuePath yamlValuePath = new YamlValuePath(originalRefValue);
            String fileName = yamlValuePath.getFileName();


            PsiFile containingFile = psiElement.getContainingFile();
            PsiDirectory containingDirectory = containingFile.getParent();
            if (containingDirectory != null) {
                System.out.println("We have found the direcotry: " + containingDirectory);      //this is coming as as the directory of the containingfile.. good
                
                final ProjectRootManager projectRootManager = ProjectRootManager.getInstance(psiElement.getProject());
                final VirtualFile root = projectRootManager.getFileIndex().getContentRootForFile(containingDirectory.getVirtualFile());
                System.out.println("Root/Module: ......" + root);
                if (root != null) {
                }
                
            }


            final PsiManager psiManager = PsiManager.getInstance(getElement().getProject());

            
            if (containingDirectory != null ) {
                System.out.println("Here trying to find out the psiFile..");
                PsiFile psiFile = containingDirectory.findFile(fileName);
                if (psiFile != null) {
                    System.out.println("psiFile: " + psiFile.getName());
                    if (psiFile.getVirtualFile() != null) {
                        System.out.println("Target found. #########..");
                        VirtualFile virtualFile = psiFile.getVirtualFile();
                        System.out.println();
                        return psiManager.findFile(virtualFile);
                    } else {
                        System.out.println("Target not found..so fall back to the containing directory");
                        return psiManager.findFile(containingDirectory.getVirtualFile());
                    }
                }

                System.out.println("################# Did not work out.. #################");  //todo vd.. add this to logger
            }

        }
        return null;
    }
}
