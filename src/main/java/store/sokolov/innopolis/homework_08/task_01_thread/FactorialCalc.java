package store.sokolov.innopolis.homework_08.task_01_thread;

import java.math.BigInteger;

/**
 * Класс для вычисления значения факториала.
 *
 * @author Vladimir Sokolov
 */
public class FactorialCalc extends Thread {
    /** число, для которого вычисляем факториал */
    private int number;
    /** значение факториала */
    private BigInteger factorial;

    /**
     * Конструктор объекта
     * @param number число, для которого необходимо вычислить факториал
     */
    public FactorialCalc(int number) {
        this.number = number;
    }

    /**
     * Вычисление факториала числа.
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
        System.out.println("Поток {" + Thread.currentThread().getName() + "} : Старт... number = " + number);
        factorial = FactorialCalc.getFactorial(number);
        System.out.println("Поток {" + Thread.currentThread().getName() + "} : Стоп... " + number + "! = " + factorial);
    }

    /**
     * Возвращает число, для которого рассчитывается факториал
     * @return число, для которого рассчитывается факториал
     */
    public int getNumber() {
        return number;
    }

    /**
     * Возвращает факториал числа
     * @return факториал числа
     */
    public BigInteger getFactorial() {
        return factorial;
    }
}
