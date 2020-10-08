package store.sokolov.innopolis.homework_03.task_03;

/**
 * Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы. Функциональность в целом должна сохраниться.
 * При попытке положить Object в MathBox должно создаваться исключение.
 * ------------
 *
 * @author Vladimir Sokolov
 */
public class Main {
    public static void main(String[] args) {
        // testObjectBox();
        testMathBox();
    }

    /**
     * Тестирование MathBox
     */
    private static void testMathBox() {
        System.out.println("----- testMathBox -----");
        Number[] numbers = new Number[] {123, 123L, 123.45D, 123.23F, (byte)15, (short)19, 123, (Number) 24};
        MathBox<Number> mathBox = new MathBox<Number>(numbers);

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
     * Тестирование ObjectBox
     */
    private static void testObjectBox() {
        System.out.println("----- testObjectBox -----");
        ObjectBox box = new ObjectBox();
        // пустой
        System.out.println(box.dump());
        // наполнение
        Object o = new Object();
        box.addObject(o);
        box.addObject(new Object());
        box.addObject(new Object());
        Integer i = new Integer(5);
        box.addObject(i);
        box.addObject(new Integer(5));
        box.addObject(new Integer(10));
        String s1 = new String("123 321");
        box.addObject(s1);
        String s2 = "000";
        box.addObject(s2);
        System.out.println(box.dump());
        // 2 раза удаление одно и того же объекта
        box.deleteObject(o);
        System.out.println(box.dump());
        box.deleteObject(o);
        System.out.println(box.dump());
        // удаление Integer через ссылку
        box.deleteObject(i);
        System.out.println(box.dump());
        // удаление integer
        box.deleteObject(10);
        System.out.println(box.dump());
        // удаление строки через ссылку на нее
        box.deleteObject(s1);
        System.out.println(box.dump());
        // удаление строки
        box.deleteObject("000");
        System.out.println(box.dump());
    }
}
