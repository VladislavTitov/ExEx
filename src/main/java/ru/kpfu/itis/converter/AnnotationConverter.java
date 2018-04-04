package ru.kpfu.itis.converter;

import org.jetbrains.annotations.Nullable;
import ru.kpfu.itis.exceptions.local.TypeMismatchException;
import ru.kpfu.itis.model.base.Model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnnotationConverter {

    @Nullable
    public static <T> T convert(Object from, Class<T> to) {
        T toInstance = null;
        try {
            toInstance = to.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return convert(from, toInstance);
    }

    @Nullable
    public static <T> T convert(Object from, T toInstance) {
        if (toInstance == null) {
            return null;
        }

        Map<String, Field> fieldsMap = new HashMap<>();
        Map<String, Field> listFieldsMap = new HashMap<>();
        Class fromClass = from.getClass();

        Class to = toInstance.getClass();
        for (Field toField : to.getDeclaredFields()) {
            SharedField sharedField = toField.getDeclaredAnnotation(SharedField.class);
            ListSharedField listSharedField = toField.getDeclaredAnnotation(ListSharedField.class);
            if (sharedField != null) {
                fieldsMap.put(sharedField.name(), toField);
            } else if (listSharedField != null) {
                listFieldsMap.put(listSharedField.name(), toField);
            }
        }


        for (Field fromField : fromClass.getDeclaredFields()) {
            fromField.setAccessible(true);
            try {
                if (fromField.get(from) == null) {
                    continue;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
            SharedField sharedField = fromField.getDeclaredAnnotation(SharedField.class);
            if (sharedField != null) {
                Field toField = fieldsMap.get(sharedField.name());
                if (toField == null) {
                    continue;
                }
                toField.setAccessible(true);
                if (!toField.getType().equals(fromField.getType())) {
                    throw new TypeMismatchException("Type of " + fromClass.getCanonicalName() + "." + fromField.getName() + " not equals " + to.getCanonicalName() + "." + toField.getName());
                }
                try {
                    toField.set(toInstance, fromField.get(from));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            ListSharedField fromListSharedField = fromField.getDeclaredAnnotation(ListSharedField.class);
            if (fromListSharedField != null) {
                Field toField = listFieldsMap.get(fromListSharedField.name());
                if (toField == null) {
                    continue;
                }
                toField.setAccessible(true);

                Class fromGenericType = fromListSharedField.genericType();
                Class toGenericType = toField.getDeclaredAnnotation(ListSharedField.class).genericType();

                if (has(fromGenericType.getInterfaces(), Model.class) &&
                        toGenericType.equals(Long.class)) {
                    try {
                        toField.set(toInstance, Converters.ids((List<? extends Model>) fromField.get(from)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return toInstance;
    }

    private static <M> boolean has(Class<?>[] classes, Class<M> checkingClass) {
        for (Class<?> aClass : classes) {
            if (aClass.equals(checkingClass)) {
                return true;
            }
        }
        return false;
    }

    public static <T> List<T> convertArray(List<?> from, Class<T> to) {
        return from.stream().map(o -> AnnotationConverter.convert(o, to)).collect(Collectors.toList());
    }

}
