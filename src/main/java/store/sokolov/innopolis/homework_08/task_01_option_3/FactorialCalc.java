package store.sokolov.innopolis.homework_08.task_01_option_3;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс для вычисления значения факториала во многопоточном режиме.
 * Данные для расчетов объект забирает из очереди queueNumbers.
 * Результаты сохраняет в мапу cash.
 * queueNumbers и cash - совместноиспользуемые потоками объекты
 *
 * @author Vladimir Sokolov
 */
public class FactorialCalc extends Thread {
    /** очередь чисел на вычисление факториала */
    private volatile ConcurrentLinkedQueue<Integer> queueNumbers;
    /** Кэш вычисленных факториалов*/
    private volatile ConcurrentHashMap<Integer, BigInteger> cash;

    /**
     * Консруктор класса
     * @param queueNumbers очередь чисел на вычисление факториала
     * @param cash кэш вычисленных факториалов
     */
    public FactorialCalc(ConcurrentLinkedQueue<Integer> queueNumbers, ConcurrentHashMap<Integer, BigInteger> cash) {
        this.queueNumbers = queueNumbers;
        this.cash = cash;
    }

    /**
     * Вычисление факториала числа.
     *
     * @param number число, для которого необходимо вычислить факториал
     * @return значение факториала указанного числа
     */
    public static BigInteger getFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал определен только для положительных чисел. Число " + number + " неудовлетворяет этому условию.");
        }
        if (number == 0) {
            return new BigInteger("1");
        }
        BigInteger factorial = new BigInteger("1");
        for (int i = 1; i < number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    /**
     * Запуск расчета факториала
     */
    @Override
    public void run() {
        // пока не закончатся элементы в очереди
        while (true) {
            Integer number;
            // извлекаем число из очереди
            synchronized (this) {
                number = queueNumbers.poll();
            }
            if (number == null) {
                break;
            }
            // есть ли такой уже такой элемент в кэше
            BigInteger factorial = null;
            synchronized (this) {
                if (cash.containsKey(number)) {
                    factorial = cash.get(number);
                }
            }
            if (factorial == null) {
                // такого числа в кэше нет, запускаем вычисление
                factorial = getFactorial(number);
                // помещаем в кэш
                synchronized (this) {
                    if (!cash.containsKey(number)) {
                        cash.put(number, factorial);
                    }
                }
            }
        }
    }
}
