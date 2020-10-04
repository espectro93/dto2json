package com.ggpstudio.parser;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;

public class JavaClassLoader {
    @SneakyThrows
    @SuppressWarnings("UnstableApiUsage")
    public static Class<?> loadClassFromFileName(String fileName, String packageName, String currentDir) {
        Collection<File> fileList = FileUtils.listFilesAndDirs(new File(currentDir), new WildcardFileFilter("*.class"),
                TrueFileFilter.TRUE);
        File specifiedFile = fileList.stream().filter(dirFile -> dirFile.getName().equals(fileName)).findFirst().orElseThrow();

        ClassLoader cl = URLClassLoader.newInstance(sanitizeURL(packageName, specifiedFile, fileName));

        return cl.loadClass(packageName + "." + fileName.replace(".class", ""));
    }

    private static URL[] sanitizeURL(String packageName, File specifiedFile, String fileName) throws MalformedURLException {
        String packageNameUrl = packageName.replace(".", "/");
        String sanitizedUrlString = specifiedFile.getAbsolutePath().replace(packageNameUrl, "").replace(fileName, "").replace("//", "/");

        String os = System.getProperty("os.name");
        String fileSystem = os.contains("Windows") ? "C://" : "file:";

        URL sanitizedUrl = new URL(fileSystem + sanitizedUrlString);
        return new URL[]{sanitizedUrl};
    }
}
