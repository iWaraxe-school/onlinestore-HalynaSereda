package com.coherentsolutions.consoleapp;

import org.reflections.Reflections;

import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class StoreApp {
    public static void main(String[] args){
        Reflections reflections = new Reflections("onlinestore-HalynaSereda");

        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(SomeType.class).asClass());

        Set<Class<?>> annotated =
                reflections.get(SubTypes.of(TypesAnnotated.with(SomeAnnotation.class)).asClass());
    }
}
