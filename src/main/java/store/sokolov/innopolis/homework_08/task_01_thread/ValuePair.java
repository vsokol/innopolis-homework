package store.sokolov.innopolis.homework_08.task_01_thread;

/**
 * Класс для хранения двух значений
 * @param <T1> тип первого значения
 * @param <T2> тип второго значения
 *
 * @author Vladimir Sokolov
 */
public class ValuePair<T1, T2> {
    /** первое значение */
    private T1 value_1;
    /** второе значение */
    private T2 value_2;

    /**
     * Конструктор для сознания объекта, где второе значение равно null
     * @param value_1 первое значение
     */
    public ValuePair(T1 value_1) {
        this(value_1, null);
    }

    /**
     * Конструктор для создания объекта
     * @param value_1 первое значение
     * @param value_2 второе значение
     */
    public ValuePair(T1 value_1, T2 value_2) {
        this.value_1 = value_1;
        this.value_2 = value_2;
    }

    /**
     * Возвращает первое значение
     * @return первое значение
     */
    public T1 getValue_1() {
        return value_1;
    }

    /**
     * Возаращает второе значение
     * @return второе значение
     */
    public T2 getValue_2() {
        return value_2;
    }

    /**
     * Устанавливает первое значение объекта
     * @param value_1 первое значение
     */
    public void setValue_1(T1 value_1) {
        this.value_1 = value_1;
    }

    /**
     * Устанавливает второе значение объекта
     * @param value_2 второе значение
     */
    public void setValue_2(T2 value_2) {
        this.value_2 = value_2;
    }

    /**
     * Представление объекта в виде строки
     * @return текстовое предтавление объекта
     */
    @Override
    public String toString() {
        return "ValuePair{" +
                value_1 +
                ", " + value_2 +
                '}';
    }
}
