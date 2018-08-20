package org.zalando.intellij.swagger.reference;

import org.apache.commons.lang.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GwVicky {


    public String resolveGwFileReference(String originalRefValue, String containingFilePath) {
        YamlValuePath yamlValuePath = new YamlValuePath(originalRefValue);
        if (StringUtils.isEmpty(originalRefValue)) {
            return null;
        }
        boolean gwFile = containingFilePath.matches(".*config[\\/]integration.*");  //todo vd add apis??
        if (gwFile && !originalRefValue.endsWith(".yaml")) {
            String relativeLocationOfContainingFile = containingFilePath.substring(containingFilePath.indexOf("integration/api") + "integration/api/".length() + 1, containingFilePath.lastIndexOf("/"));   //todo vd Use groups here.. also take care of windows stuff here

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

    public static void main(String[] args) {


//        FileReference fileReference = new FileReference(null, "gw.blah.foo.bar");
//        fileReference.resolveFileReferenceWithSubPath("gw.foo.bar");

    }
}


