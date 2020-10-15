package store.sokolov.innopolis.homework_05.task_01_option_02;

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
        System.out.println("--- printPetFileByName ---");
        petFile.printPetFileToConsoleOrderByName();
    }
}

