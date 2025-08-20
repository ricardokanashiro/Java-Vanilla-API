package app.vanillajavaapi.utils;

import java.util.List;
import java.util.function.Function;

public class SimpleMapper {
    public static <E, D> List<D> mapList(List<E> source, Function<E, D> mapper) {
        return source.stream()
                .map(mapper)
                .toList();
    }
}