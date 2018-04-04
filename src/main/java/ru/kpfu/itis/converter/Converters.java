package ru.kpfu.itis.converter;

import org.jetbrains.annotations.NotNull;
import ru.kpfu.itis.model.base.Model;

import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    public static <T extends Model> List<Long> ids(@NotNull List<T> objects) {
        return objects.stream().map(Model::getId).collect(Collectors.toList());
    }

}
