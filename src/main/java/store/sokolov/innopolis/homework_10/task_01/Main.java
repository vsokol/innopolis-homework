package store.sokolov.innopolis.homework_10.task_01;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Дан интерфейс
 *
 * public interface Worker {
 *     void doWork();
 * }
 *
 * Необходимо написать программу, выполняющую следующее:
 *  - Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 *  - После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 *  - Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 *  - Полученный файл подгружается в программу с помощью кастомного загрузчика
 *  - Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 *  -----------------
 *  код класса считывается из первого параметра программы
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //String classFileName = "D:\\java\\innopolis\\STC-31\\homework\\src\\main\\java\\store\\sokolov\\innopolis\\homework_10\\task_01\\SomeClass.java"
        String classFileName = args[0];
        // создаем загрузчик классов
        MyClassLoader classLoader = new MyClassLoader(classFileName);
        Class<?> someClass = Class.forName("SomeClass", true, classLoader);
        // вызываем метод doWork
        Method method = someClass.getMethod("doWork");
        method.invoke(someClass.newInstance());
        // int i = 3;
        // System.out.println("i = " + i + " : i++ = " + i++ + " : ++i = " + ++i);
    }
}
