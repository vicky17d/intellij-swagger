package org.zalando.intellij.swagger.reference;

import org.apache.commons.lang.StringUtils;

public class YamlValuePath {
    public static final String EMPTY = "";
    private String path;

    public YamlValuePath(String path) {
        this.path = path;
    }

    public boolean isRelativePath() {
        String substring = path;
        if (hasVersion()) {
            int indexOfHyphen = path.lastIndexOf('-');
            substring = path.substring(0, indexOfHyphen); //vd this is good.. because we are checking if teh version exists and then getting the last index of hiphen 
        }
        return substring.contains(".");
    }

    public boolean hasVersion() {
        return path.matches(".*-[0-9]\\.[0-9]"); //todo vd it can be 1.0 or 1.0.1 and so on
    }

    public String getParentPathOriginal() {
        if (!isRelativePath()) {
            return EMPTY;
        }
        String substring = path;
        if (hasVersion()) {
            int indexOfHyphen = path.lastIndexOf('-');
            substring = path.substring(0, indexOfHyphen);
        }
        int indexOfLastDotInYamlValuePath = substring.lastIndexOf('.');
        if (indexOfLastDotInYamlValuePath > 0) {
            return path.substring(0, indexOfLastDotInYamlValuePath);
        }
        return EMPTY;
}

    public String getFileName() {
        return getFileNameWithoutExtension() + ".swagger.yaml";
    }

    public String getFileNameWithoutExtension() {
        if (StringUtils.isEmpty(getParentPathOriginal())) {
            return path;
        }
        return path.substring(path.indexOf(getParentPathOriginal()) + getParentPathOriginal().length() + 1);
    }

    public String getParentPath() {
        if (!StringUtils.isEmpty(getParentPathOriginal())) {
            return getParentPathOriginal().replace(".", "/");     //todo vd .. this needs to be fixed... does not work if hte path has ../../ in the path  -- it is probably ok because there are naming standards and path standards
        } else {
            return EMPTY;  //todo vd null or emptly???
        }
    }
}