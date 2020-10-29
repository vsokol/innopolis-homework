package store.sokolov.innopolis.homework_13.task_02;

//import javassist.CannotCompileException;
//import javassist.ClassPool;

import java.util.ArrayList;
import java.util.List;

/**
 * ДЗ_13
 * Задание 2.
 * Необходимо создать программу, которая продемонстрирует утечку памяти в Java. При этом объекты должны
 * не только создаваться, но и периодически частично удаляться, чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError возникающая в Metaspace /Permanent Generation
 * ----
 * запускать с параметром VM -XX:MaxMetaspaceSize=9m
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("ДЗ_13. Задание 2");
        String temp = System.getProperty("java.io.tmpdir");
        List<Object> list = new ArrayList<>();
        int count = 0;
        while (true) {
            count++;
            String className = "OutOfMemoryErrorJavaMetaspace_" + count;
            MyClassLoader classLoader = new MyClassLoader(className);
            Class<?> someClass = Class.forName(className, true, classLoader);
            if (count % 2 == 0) {
                list.add(someClass);
            }
            System.out.println("Загружен класс - " + someClass.getName());
        }
    }

//    private static void OutOfMemoryError() {
//        ClassPool classPool = ClassPool.getDefault();
//        int i = 0;
//        while (true) {
//            i++;
//            Class clazz = classPool.makeClass("OutOfMemoryErrorJavaMetaspace_" + i).toClass();
//            System.out.println(clazz.getName());
//        }
//    }

}
