package store.sokolov.innopolis.homework_08.task_01_stream;

import java.math.BigInteger;
import java.util.*;

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
//        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
        List<FactorialValue<Integer, BigInteger>> factorials = Factorial.calc(generateArray(20));
        print(factorials);
    }

    /**
     * Генерирует массив случайных чисел размером count
     * @param count кол-во элементов массива, которые необходимо сгенерировать
     * @return массив псевдослучайных чисел
     */
    private static List<Integer> generateArray(int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        Random rnd = new Random();
        List<Integer> arr = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            arr.add(rnd.nextInt(10000));
        }
        return arr;
    }

    /**
     * Выводит на консоль вычисленные факториалы
     * @param list список факториалов
     */
    private static void print(List<FactorialValue<Integer, BigInteger>> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (FactorialValue<Integer, BigInteger> item : list) {
            System.out.println(item.getNumber() + "! = " + item.getFactorial());
        }
    }
}
