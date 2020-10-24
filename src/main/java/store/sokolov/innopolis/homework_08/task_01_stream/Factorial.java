package store.sokolov.innopolis.homework_08.task_01_stream;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для вычисления факториалов
 *
 * @author Vladimir Sokolov
 */
public class Factorial {
    /**
     * Многопоточное вычисление факториалов с помощью parallelStream
     * @param numbers список чисел, для вычисления их факториала
     * @return список вычисленных факториалов
     */
    public static List<FactorialValue<Integer, BigInteger>> calc(Collection<Integer> numbers) {
        List listOfFactorial = numbers.parallelStream()
                .map(num -> {
                    if (num < 0) {
                        throw new IllegalArgumentException("Факториал определен только для положительных чисел. Число " + num + " неудовлетворяет этому условию.");
                    }
                    BigInteger factorial = new BigInteger("1");
                    for (int i = 1; i <= num; i++) {
                        factorial = factorial.multiply(BigInteger.valueOf(i));
                    }
                    return new FactorialValue(num, factorial);
                })
                .collect(Collectors.toList());
        //        .forEach(System.out::println);
        return listOfFactorial;
    }
}
