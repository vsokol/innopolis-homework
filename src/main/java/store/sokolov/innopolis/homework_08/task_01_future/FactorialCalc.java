package store.sokolov.innopolis.homework_08.task_01_future;

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Класс для вычисления факториала.
 * Может использоваться при многопоточном вычислении. Результат возвращает в объекте {@link java.util.concurrent.Future}
 */
public class FactorialCalc implements Callable<FactorialValue<Integer, BigInteger>> {
    /** число для расчета факториала */
    private int number;

    /**
     * Конструктор класса.
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
     * Возращает факториал числа. Предназначен для работы в многопоточной среде с возвратом вычисленного факторила.
     * @return факториал числа
     * @throws Exception
     */
    @Override
    public FactorialValue<Integer, BigInteger> call() throws Exception {
        System.out.println("Поток : " + Thread.currentThread().getName() + ". Старт расчета для " + number + "!");
        BigInteger factorial = getFactorial(number);
        FactorialValue<Integer, BigInteger> fc = new FactorialValue<>(number, factorial);
        System.out.println("Поток : " + Thread.currentThread().getName() + ". Окончание расчета для " + number + "!" /*+ " = " + factorial*/);
        return fc;
    }
}
