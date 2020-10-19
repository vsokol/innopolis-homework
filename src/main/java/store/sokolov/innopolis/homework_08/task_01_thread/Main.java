package store.sokolov.innopolis.homework_08.task_01_thread;

import java.util.Random;

/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
 * Использовать пул потоков для решения задачи.
 *
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти,
 * очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger.
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 *
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее
 */
public class Main {
    public static void main(String[] args) {
        // int[] arrOfInt = {100, 2000, 3000, 400, 500, 600, 700, 800, 900, 1000};
        int[] arrOfInt = generateArray(20);
        IManageFactorial mf = new ManageFactorial(arrOfInt);
        mf.calc(4);
        System.out.println(((ManageFactorial)mf).getMap().toString());
    }

    /**
     * Генерирует массив случайных чисел размером count
     * @param count кол-во элементов массива, которые необходимо сгенерировать
     * @return массив псевдослучайных чисел
     */
    private static int[] generateArray(int count) {
        if (count <= 0) {
            return new int[0];
        }
        Random rnd = new Random();
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = rnd.nextInt(10000);
        }
        return arr;
    }
}
