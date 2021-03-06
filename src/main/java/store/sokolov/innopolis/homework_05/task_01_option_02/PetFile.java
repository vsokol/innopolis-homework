package store.sokolov.innopolis.homework_05.task_01_option_02;

import java.util.*;

/**
 * Картотека домашних животных
 * У каждого животного есть уникальный идентификационный номер, кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * Реализовано:
 * - метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * - поиск животного по его кличке (поиск должен быть эффективным)
 * - изменение данных животного по его идентификатору
 * - вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 */
public class PetFile {
    private Map<Pet, String> petFile = new TreeMap<>();

    /**
     * Добавляет домашнего животного
     * @param pet - домашнее животное, которое необходимо добавить в хранилище
     * @throws PetDublicateException если такое домашнее животное уже существует
     * @throws NullPointerException если в качестве домашнего животного или идентификатора домашнего животного передается null
     */
    public void addPet(Pet pet) throws PetDublicateException, NullPointerException {
        if (pet == null) {
            throw new NullPointerException("Не указан домашний питомец");
        }
        if (pet.getId() == null) {
            throw new NullPointerException("У домашнего питомецы не указан идентификационный номер");
        }
        if (!petFile.containsKey(pet)) {
            petFile.put(pet, pet.getId());
        } else {
            throw new PetDublicateException("В картотеке уже есть такой домашний питомец " + pet.toString());
        }
    }

    /**
     * Поиск животного по его кличке
     * @param petName - кличка домашнего животного
     * @return домашнее животное, у которого кличка совпадает с указанным
     */
    public Pet searchByName(String petName) {
        List<Pet> list = new ArrayList<>(petFile.keySet());
        Collections.sort(list, new SortedByPetName());
        int ind = binarySearch(list, 0, list.size() - 1, petName);
        if (ind != -1) {
            return list.get(ind);
        } else {
            return null;
        }
    }

    /**
     * Двоичный поиск домашнего животного с указанной в параметрах кличкой.
     * Алгоритм делит коллекцию на 2 части и сравнивает с серединным элементом.
     * Если элемент совпадает с заданным, то возращается его индекс, иначе рекурсивно
     * вызывает сам себя с указанием новых границ массива в соответсвии с результатом сравнения с серединным элементом
     * @param list - список, в котором ищется заданное кличкой домашнее животное
     * @param first - первый индекс списка. Для первого вызова он равен 0.
     * @param last - последний индекс списка. Для первого вызова он равен размеру списка - 1
     * @param petName - кличка животного, которое мы ищем
     * @return номер индекса в списке найденного животного. Если живоного с такой кличкой нет, то возаращием -1.
     */
    protected int binarySearch(List<Pet> list, int first, int last, String petName) {
        if (last >= first) {
            // находим середину
            int mid = first + (last - first) / 2;
            String midPetName = list.get(mid).getPetName();
            if (petName.equals(midPetName)) {
                // если серединный элемент равен заданному, то возвращаем его индекс
                return mid;
            }
            if (petName.compareTo(midPetName) < 0) {
                // если искомый элемент находится левее серединного, то рекусивно вызываем для левой части списка
                return binarySearch(list, first, mid - 1 , petName);
            } else {
                // иначе искомый элемент находится правее серединного, следовательно рекусивно вызываем для правой части списка
                return binarySearch(list, mid + 1, last, petName);
            }
        }
        return -1;
    }

    /**
     * Изменение данных домашнего животного по его идентификатору. Если все переданные значения равны null, тогда изменений не будет
     * @param id - индентификатор домашнего животного, параметры которого необходимо изменить
     * @param petName - новая кличка домащнего животного
     * @param owner - новый владелец домащнего животного
     * @param weight - новый вес домащнего животного
     * @throws PetNotExists если такого домашнего животного не существует
     * @throws NullPointerException если с таким инедентификатором не оказалось домашнего питомца // хотя такого не должно быть
     */
    public void changePetInfoOnId(String id, String petName, Person owner, Integer weight) throws PetNotExists, NullPointerException {
        if (!petFile.containsValue(id)) {
            throw new PetNotExists("Домашнего животного с идентификатором id = '" + id + "' не существует.");
        }
        if (petName == null && owner == null && weight == null) {
            // ничего не меняем
            return;
            //throw new NullPointerException("Все переданные параметры равны null");
        }
        Pet petOld = null;
        for (Map.Entry<Pet, String> item : petFile.entrySet()) {
            if (id.equals(item.getValue())) {
                petOld = item.getKey();
                break;
            }
        }
        if (petOld == null) {
            throw new NullPointerException("По указанному идентификатору id = '" + id + "' находится {null} домашний питомец");
        }
        Pet petNew = new Pet(petOld.getId(), petOld.getPetName(), petOld.getOwner(), petOld.getWeight());
        if (petName != null) {
            petNew.setPetName(petName);
        }
        if (owner != null) {
            petNew.setOwner(owner);
        }
        if (weight != null) {
            petNew.setWeight(weight);
        }
        petFile.remove(petOld);
        petFile.put(petNew, id);
    }

    /**
     * Возвращает строковое представление объекта {@link PetFile}
     * @return строковое представление текущего объекта
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PetFile{\n");
        for (Map.Entry<Pet, String> item : petFile.entrySet()) {
            sb.append("id = ");
            sb.append(item.getValue());
            sb.append(" : ");
            sb.append(item.getKey());
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Вывод на консоль списка домашних животных в отсортированном порядке.
     * Поля для сортировки – хозяин, кличка животного, вес.
     * Используется compareTo класса {@link Pet}
     */
    public void printPetFileToConsole() {
        for (Pet item : petFile.keySet()) {
            System.out.println(item.toString());
        }
    }

    /**
     * Вывод на консоль списка домашних животных в отсортированном порядке.
     * Поля для сортировки – кличка.
     * Используется вспомогательный класс {@link SortedByPetName} реализующий интерфейс {@link Comparator<Pet>}
     */
    public void printPetFileToConsoleOrderByName() {
        Set<Pet> set = new TreeSet<>(new SortedByPetName());
        set.addAll(petFile.keySet());
        for (Pet item : set) {
            System.out.println(item.toString());
        }
    }

    /**
     * Вспомогательный класс для сравнения двух объектов класса {@link Pet} только по кличкам
     */
    protected static class SortedByPetName implements Comparator<Pet> {
        @Override
        public int compare(Pet o1, Pet o2) {
            return o1.getPetName().compareTo(o2.getPetName());
        }
    }
}
