package store.sokolov.innopolis.homework_13.task_01;

import java.util.LinkedList;
import java.util.List;

/**
 * ДЗ_13
 * Задание 1. Необходимо создать программу, которая продемонстрирует утечку памяти в Java. При этом объекты должны
 * не только создаваться, но и периодически частично удаляться, чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("ДЗ_13. Задание 1");
        genOutOfMemoryErrorJavaHeapSpace();
    }

    private static void genOutOfMemoryErrorJavaHeapSpace() {
        int listSize = 1000;
        List<Object[]> list = new LinkedList<>();
        int count = 0;
        while (true) {
            Object[] arr = generateData(listSize);
            if (count % 6 == 0) {
                list.add(arr);
            }
            count++;
        }
    }

    private static Object[] generateData(int length) {
        Object[] arr = new Object[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Object();
        }
        return arr;
    }
}
