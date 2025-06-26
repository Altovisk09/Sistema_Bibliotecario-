package com.library.util;

import java.util.List;
import java.util.function.Function;

//Metodo generico para criação de ids, utilizado nas 3 principais entidades do projeto.
public class IdGenerator {
    private IdGenerator(){};
    public static <T> int getNextId(List<T> list, Function<T, Integer> idExtractor){
        return list.stream()
                .mapToInt(idExtractor::apply)
                .max()
                .orElse(0) + 1;
    }
}
