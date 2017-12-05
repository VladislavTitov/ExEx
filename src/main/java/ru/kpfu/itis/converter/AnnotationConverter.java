package ru.kpfu.itis.converter;

import org.jetbrains.annotations.Nullable;
import ru.kpfu.itis.exceptions.local.TypeMismatchException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnnotationConverter {

    @Nullable
    public static <T> T convert(Object from, Class<T> to) {
        Map<String, Field> fieldsMap = new HashMap<>();
        Class fromClass = from.getClass();
        T toInstance = null;
        try {
            toInstance = to.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (toInstance == null) {
            return null;
        }

        for (Field toField : to.getDeclaredFields()) {
            SharedField sharedField = toField.getDeclaredAnnotation(SharedField.class);
            if (sharedField != null) {
                fieldsMap.put(sharedField.name(), toField);
            }
        }

        for (Field fromField : fromClass.getDeclaredFields()) {
            SharedField sharedField = fromField.getDeclaredAnnotation(SharedField.class);
            if (sharedField != null) {
                Field toField = fieldsMap.get(sharedField.name());
                if (!toField.getType().equals(fromField.getType())) {
                    throw new TypeMismatchException("Type of " + fromClass.getCanonicalName() + "." + fromField.getName() + " not equals " + to.getCanonicalName() + "." + toField.getName());
                }
                fromField.setAccessible(true);
                toField.setAccessible(true);
                try {
                    toField.set(toInstance, fromField.get(from));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return toInstance;
    }

    public static <T> List<T> convertArray(List<?> from, Class<T> to) {
        return from.stream().map(o -> AnnotationConverter.convert(o, to)).collect(Collectors.toList());
    }

}
