package com.ggpstudio.parser;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("rawtypes")
public class DtoProcessor {
    @SneakyThrows
    public static Object processDtoClass(Class<?> clazz) {

        Class[] constructorTypes = new Class[clazz.getDeclaredFields().length];
        Object[] values = new Object[clazz.getDeclaredFields().length];

        int counter = 0;
        for (Field field : clazz.getDeclaredFields()) {

            Class<?> type = field.getType();

            constructorTypes[counter] = type;
            values[counter] = getValueFor(clazz, type, field.getAnnotations());

            counter++;
        }

        Constructor<?> constructor = clazz.getConstructor(constructorTypes);
        return constructor.newInstance(values);
    }


    private static Object getValueFor(Class<?> clazz, Class<?> type, Annotation[] annotations) {
        if (clazz.getPackageName().equals(type.getPackageName())) {
            return processDtoClass(type);
        }
        if (type.equals(String.class)) {
            return RandomStringUtils.randomAlphabetic(12);
        }
        if (type.equals(int.class) || type.equals(Integer.class)) return 12;

        if (type.equals(BigDecimal.class)) return BigDecimal.ZERO;

        if (type.equals(List.class)) return List.of("12", "34");

        return "1234";
    }
}
