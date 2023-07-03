package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.Category;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class StoreApp {
    public static void main(String[] args) {

        Reflections reflections = new Reflections("com.coherentsolutions.domain");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType : subTypes) {
            try {
                Constructor<? extends Category> constructor = subType.getDeclaredConstructor();
                if (!constructor.isAccessible(null)) {
                    System.out.printf("Cannot access constructor of %s, it might be private%n", subType.getSimpleName());
                    continue;
                }
                Category instance = constructor.newInstance();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}