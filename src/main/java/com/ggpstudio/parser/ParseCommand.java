package com.ggpstudio.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import picocli.CommandLine;

import java.nio.file.Path;
import java.nio.file.Paths;

@CommandLine.Command(
        name = "dto2json"
)
public class ParseCommand implements Runnable {
    @CommandLine.Option(names = {"-p", "--package"}, required = true)
    private String packageName;
    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    private String file;

    public static void main(String[] args) {
        CommandLine.run(new ParseCommand(), args);
    }

    @SneakyThrows
    public void run() {
        Path currentRelativePath = Paths.get("");
        String currentDir = currentRelativePath.toAbsolutePath().toString();
        Class<?> clazz = JavaClassLoader.loadClassFromFileName(file, packageName, currentDir);
        Object result = DtoProcessor.processDtoClass(clazz);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));
    }
}
