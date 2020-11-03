package store.sokolov.innopolis.homework_02.task_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задание 3. Дан массив объектов Person. Класс Person характеризуется полями
 *      age (возраст, целое число 0-100),
 *      sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN),
 *      name (имя - строка).
 * Создать два класса, методы которых будут реализовывать сортировку объектов.
 * Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
 * первые идут мужчины
 * выше в списке тот, кто более старший
 * имена сортируются по алфавиту
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 * ----------
 * Кол-во элементов, которые необходимо сгенировать задаются в параметрах программы.
 * Если параметр не задан, меньще или равен 0 или не может быть преобразован к числу, то его значение устанавливается в defaultNumberOfPersons = 100.
 *
 * @author Vladimir Sokolov
 */

public class Main {
    public static void main(String[] args) throws IllegalAge, EqualNameAndAge, CloneNotSupportedException {
        int defaultNumberOfPersons = 100;
        int numberOfPersons;
        if (args.length != 0) {
            try {
                numberOfPersons = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                numberOfPersons = defaultNumberOfPersons;
                // e.printStackTrace();
            }
        } else {
            numberOfPersons = defaultNumberOfPersons;
        }

        // заполнение массива для тестовых прогонов
        // List<Person> list1 = testFillList();

        List<Person> list1 = generatePersons(numberOfPersons);
        List<Person> list2 = copyList(list1);

        // печать при тестовых прогонах
        //testPrintLists(list1, list2, false);

        long startTimes1 = System.currentTimeMillis();
        new PersonsBubbleSort().sort(list2);
        long stopTimes1 = System.currentTimeMillis();

        long startTimes2 = System.currentTimeMillis();
        new PersonsSelectionSort().sort(list1);
        long stopTimes2 = System.currentTimeMillis();

        printList(list1);
        System.out.println("Время выполнения (сортировка пузырьком) = " + (stopTimes1 - startTimes1) + " миллисек.");
        System.out.println("Время выполнения (сортировка выбором) = " + (stopTimes2 - startTimes2) + " миллисек.");

        // печать при тестовых прогонах
        //testPrintLists(list1, list2, true);
    }

    /**
     * Генерация экземпляров объекта класса {@link Person}
     * @param count кол-во создаваемых элементов
     * @return массив людей
     * @throws IllegalAge исключение возникающее, если возраст не попадает в интервал 0..100. Пробрасываем выше.
     */
    private static List<Person> generatePersons(int count) throws IllegalAge {
        List<Person> list = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            Person person = new Person(generateName(), rnd.nextBoolean() ? Sex.MAN : Sex.WOMAN, rnd.nextInt(100));
            list.add(person);
        }
        return list;
    }

    /**
     * Генерация имен людей
     * @return сгенерированное имя человека
     */
    private static String generateName() {
        char[] chars = "абвгдеёжзийклмнопрстуфхцчшщьыъэюя".toCharArray();
        int minLen = 3, maxLen = 15;
        Random rnd = new Random();
        int length = rnd.nextInt(maxLen - minLen + 1) + minLen;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars[rnd.nextInt(32)]);
        }
        return sb.substring(0,1).toUpperCase() + sb.substring(1);
    }

    /**
     * Заполнение тестового массива для тестирования сортировки
     * @return тестовый массив с экземплярами объектов класса {@link Person}
     * @throws IllegalAge исключение возникающее, если возраст не попадает в интервал 0..100. Пробрасываем выше.
     */
    private static List<Person> testFillList() throws IllegalAge {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Оля", Sex.WOMAN, 35));
        list.add(new Person("Вася", Sex.MAN, 25));
        list.add(new Person("Лена", Sex.WOMAN, 41));
        list.add(new Person("Вика", Sex.WOMAN, 35));
        list.add(new Person("Саша", Sex.MAN, 25));
        list.add(new Person("Саша", Sex.MAN, 47));
        // полный дубль для проверки генерации ислючения EqualNameAndAge при сортировках
        // list.add(new Person("Саша", Sex.MAN, 47));
        return list;
    }

    /**
     * Копирование массива
     * @param list массив, который нужно скопировать
     * @return массив идентичный входному
     * @throws CloneNotSupportedException
     */
    private static List<Person> copyList(List<Person> list) throws CloneNotSupportedException {
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }
        List<Person> result = new ArrayList<>();
        for (Person item : list) {
            result.add((Person) item.clone());
        }
        return result;
    }

    private static void testPrintLists(List<Person> list1, List<Person> list2, boolean isSorted) {
        System.out.println("-- list1" + (isSorted ? "-sorted" : "") + " ----------");
        printList(list1);
        System.out.println("-- list2" + (isSorted ? "-sorted" : "") + " ----------");
        printList(list2);
    }

    /**
     * Вывод в консоль массива экземпляров объката класса {@link Person}
     * @param list массив экземпляров объектов класса {@link Person}, который необходимо распечатать
     */
    private static void printList(List<Person> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + (i+1) + ": " + list.get(i).toString());
        }
    }
}
