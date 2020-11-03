package store.sokolov.innopolis.homework_02.task_02;

import java.util.Random;

/**
 * Задание 2. Составить программу, генерирующую N случайных чисел.
 * Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 * ----------
 * Кол-во генерируемых чисел задается в первом параметре приложения.
 * Если параметр не задан, меньще или равен 0 или не может быть преобразован к числу, то его значение устанавливается в 1.
 *
 * @author Vladimir Sokolov
 */

public class Main {
    public static void main(String[] args) throws NegativeNumber {
        int N;
        if (args.length != 0) {
            try {
                N = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                N = 1;
                // e.printStackTrace();
            }
        } else {
            N = 1;
        }
        long[] arrGenNumber = generateNumber(N);
//         long[] arrGenNumber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        printNumWithCondition(arrGenNumber);
    }

    /**
     * Генерирует и заполняет массив случайными числами
     *
     * @param countNumber кол-во сгенерированных значений
     * @return возвращает массив значений
     */
    private static long[] generateNumber(int countNumber) {
        if (countNumber <= 0) {
            return new long[0];
        }
        long[] arrGenNumber = new long[countNumber];
        Random rnd = new Random();

        for (int i = 0; i < arrGenNumber.length; i++) {
            arrGenNumber[i] = rnd.nextLong();
        }
        return arrGenNumber;
    }

    /**
     * Выводит на консоль элементы массива, если они удовлетворяют условиям задачи.
     *
     * @param arrNumber массив значений
     * @throws NegativeNumber исключение, выбрасываемое при обработке отрицательного значения
     */
    private static void printNumWithCondition(long[] arrNumber) throws NegativeNumber {
        if (arrNumber == null || arrNumber.length == 0) {
            return;
        }

        for (int i = 0; i < arrNumber.length; i++) {
            if (arrNumber[i] < 0) {
                throw new NegativeNumber("Отрицательное число " + arrNumber[i]);
            }
            double q = Math.sqrt(arrNumber[i]);
            long wholeQpow2 = (long) Math.pow((long) q, 2);
            if (arrNumber[i] == wholeQpow2) {
                System.out.println(arrNumber[i]);
            }
        }
    }
}