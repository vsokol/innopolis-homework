package store.sokolov.innopolis.homework_03.task_01;

/**
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 *  - Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 *  - Существует метод summator, возвращающий сумму всех элементов коллекции.
 *  - Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 *  - Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 *  - Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 *  ------------------------
 * @author Vladimir Sokolov
 */
public class Main {
    public static void main(String[] args) {
        Number[] numbers = testData();
        MathBox mathBox = new MathBox(numbers);

        System.out.println(mathBox.toString());
        System.out.println(mathBox.summator());
        mathBox.splitter(2);

        System.out.println(mathBox.toString());
        System.out.println(mathBox.summator());

        MathBox mathBox1 = new MathBox(new Number[]{123, 123L, 123.45D, 123.23F, (byte)15, (short)19, 123, (Number) 24});
        MathBox mathBox2 = new MathBox(new Number[]{123L, 123.45D, 123.23F, (byte)15, (short)19, 123, (Number) 24, 123});
        MathBox mathBox3 = new MathBox(new Number[]{123, 123.45D, 123.23F, (byte)15, (short)19, 123, (Number) 24});

        System.out.println("hashCode (mathBox1) = " + mathBox1.hashCode());
        System.out.println("hashCode (mathBox2) = " + mathBox2.hashCode());
        System.out.println("hashCode (mathBox3) = " + mathBox3.hashCode());

        System.out.println("mathBox1.equals(mathBox2) = " + mathBox1.equals(mathBox2));
        System.out.println("mathBox1.equals(mathBox3) = " + mathBox1.equals(mathBox3));
    }

    /**
     * Формирование тестовых данных
     * @return массив тестовых данных
     */
    private static Number[] testData() {
        return new Number[] {123, 123L, 123.45D, 123.23F, (byte)15, (short)19, 123, (Number) 24};
    }
}
