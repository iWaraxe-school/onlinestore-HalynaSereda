package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.Category;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class StoreApp {
    public static void main(String[] args) throws InstantiationException {


        Reflections reflections = new Reflections("com.coherentsolutions.domain");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType : subTypes) {
            try {
                Category instance = subType.getDeclaredConstructor().newInstance();
               }
            catch (InvocationTargetException e) {
            e.printStackTrace();
            } catch (NullPointerException  e) {
            e.printStackTrace();
            } catch (IllegalAccessException e) {
            e.printStackTrace();
            } catch (NoSuchMethodException e) {
            e.printStackTrace();}

            }
               }

    }

