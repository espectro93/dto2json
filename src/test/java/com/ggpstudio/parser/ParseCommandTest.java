package com.ggpstudio.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class ParseCommandTest {

    @Test
    @SneakyThrows
    void testJavaClassLoader() {
        Path currentRelativePath = Paths.get("");
        String currentDir = currentRelativePath.toAbsolutePath().toString();
        Class<?> clazz = JavaClassLoader.loadClassFromFileName("TestclassDto.class", "com.ggpstudio.parser", currentDir);
        Object result = DtoProcessor.processDtoClass(clazz);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonResult = mapper.writeValueAsString(result);
        TestclassDto testclassDto = mapper.readValue(jsonResult, TestclassDto.class);

        assertThat(testclassDto.getName().length()).isEqualTo(12);
    }

}