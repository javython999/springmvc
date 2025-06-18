package com.errday.servlet.reflection;

public class ReflectionExample {

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class<?> clazz = Class.forName("java.util.ArrayList");
            //Class<?> clazz = ArrayList.class
            //Class<?> clazz = new ArrayList().getClass();
            System.out.println("Class Name: " + clazz.getName());
            System.out.println("Superclass: " + clazz.getSuperclass());
            System.out.println("\nImplemented Interfaces:");
            for (Class<?> inf : clazz.getInterfaces()) {
                System.out.println("  " + inf.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
