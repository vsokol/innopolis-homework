package store.sokolov.innopolis.homework_05.task_01;

import java.util.*;

/**
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер, кличка,
 * хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * Реализовать:
 *  - метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 *  - поиск животного по его кличке (поиск должен быть эффективным)
 *  - изменение данных животного по его идентификатору
 *  - вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 */
public class Main {
    public static void main(String[] args) throws PetDublicateException, PetNotExists {
        // testPerson();
        // testPet();
        testPetFile();
    }

    private static void testPetFile() throws PetDublicateException, PetNotExists {
        PetFile petFile = new PetFile();
        petFile.addPet(new Pet("111", "Мурка", new Person("Саша", Sex.MAN, 47), 5));
        petFile.addPet(new Pet("222", "Мурка", new Person("Саша", Sex.MAN, 47), 3));
        petFile.addPet(new Pet("333", "Дуська", new Person("Саша", Sex.MAN, 47), 3));
        petFile.addPet(new Pet("444", "Васька", new Person("Оля", Sex.WOMAN, 35), 10));
        petFile.addPet(new Pet("555", "Пушок", new Person("Саша", Sex.WOMAN, 47), 2));
        // проверка на дубликат
        // petFile.addPet(new Pet("555", "Пушок", new Person("Саша", Sex.WOMAN, 47), 2));
        petFile.addPet(new Pet("657", "Пир", new Person("Саша", Sex.WOMAN, 47), 9));
        petFile.changePetInfoOnId("657", "Пират", null, 15);

        System.out.println(petFile.toString());
        System.out.println("--- printPetFile ---");
        petFile.printPetFileToConsole();
        Pet searchPet = petFile.searchByName("Васька");
        if (searchPet != null) {
            System.out.println("Search = " + searchPet.toString());
        }
        //System.out.println("--- printPetFileByName ---");
        //petFile.printPetFileToConsoleOrderByName();
    }

    private static void testPet() {
        List<Pet> list = new ArrayList<>();
        list.add(new Pet("111", "Мурка", new Person("Саша", Sex.MAN, 47), 5));
        list.add(new Pet("222", "Мурка", new Person("Саша", Sex.MAN, 47), 3));
        list.add(new Pet("333", "Дуська", new Person("Саша", Sex.MAN, 47), 3));
        list.add(new Pet("444", "Васька", new Person("Оля", Sex.WOMAN, 35), 10));
        list.add(new Pet("333", "Пушок", new Person("Саша", Sex.WOMAN, 47), 2));

        System.out.println("--- printListPet {as is} ---");
        printList(list);
        Collections.sort(list);
        System.out.println("--- printListPet {sorted} ---");
        printList(list);
    }

    private static void testPerson() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Саша", Sex.MAN, 47));
        list.add(new Person("Оля", Sex.WOMAN, 35));
        list.add(new Person("Саша", Sex.MAN, 25));
        list.add(new Person("Вася", Sex.MAN, 25));
        list.add(new Person("Лена", Sex.WOMAN, 41));
        list.add(new Person("Саша", Sex.WOMAN, 25));

        System.out.println("--- printListPerson {as is} ---");
        printList(list);
        Collections.sort(list);
        System.out.println("--- printListPerson {sorted} ---");
        printList(list);
    }

    private static void printList(List<?> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

}
