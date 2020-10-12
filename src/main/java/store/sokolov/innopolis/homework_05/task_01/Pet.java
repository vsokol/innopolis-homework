package store.sokolov.innopolis.homework_05.task_01;

import java.util.UUID;

/**
 * Класс домащнее животное
 *
 * @author Vlidimir Sokolov
 */
public class Pet implements Comparable {
    /** идентификационный номер */
    private String id;
    /** кличка */
    private String petName;
    /** хозяин */
    private Person owner;
    /** вес */
    private int weight;

    /**
     * Конструктор для создания домашнего животного. Иникальный идентификатор генерируется с использованием UUID.randomUUID().toString()
     * @param petName - кличка домашнего животного
     * @param owner - владелец домашнего животного
     * @param weight - вес домашнего животного
     */
    public Pet(String petName, Person owner, int weight) {
        this.id = generateId();
        this.petName = petName;
        this.owner = owner;
        this.weight = weight;
    }

    /**
     * Конструктор для создания домашнего животного. Иникальный идентификатор генерируется с использованием UUID.randomUUID().toString()
     * @param id - уникальный идентификатор домашнего животного
     * @param petName - кличка домашнего животного
     * @param owner - владелец домашнего животного
     * @param weight - вес домашнего животного
     */
    public Pet(String id, String petName, Person owner, int weight) {
        this.id = id;
        this.petName = petName;
        this.owner = owner;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public Person getOwner() {
        return owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Возвращает сгенерированный уникальный идентификатор домашенего животного
     * @return уникальный идентификатор домашенего животного
     */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Возвращает строковое представление объекта {@link Pet}
     * @return строковое представление текущего объекта
     */
    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                ", owner=" + owner.toString() +
                ", weight=" + weight +
                '}';
    }

    /**
     * Сравнивает текущий объект с указанным. Сравнение производится по всем полям класса Pet
     * @param o - объект, с которым идет сравнение
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pet pet = (Pet) o;
        if (weight != pet.weight) {
            return false;
        }
        if (!id.equals(pet.id)) {
            return false;
        }
        if (!petName.equals(pet.petName)) {
            return false;
        }
        return owner.equals(pet.owner);
    }

    /**
     * Возвращает hash код текущего объекта
     * @return hash код
     */
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + petName.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + weight;
        return result;
    }

    /**
     * Сравнение текущего объекта с указанным. Результатом будет отрицательное число,
     * если текущий объет предшествует переданному. Рузультатом будет положительное число,
     * если текущий объект следует за переданным. Если объекты равны, то результатом будет 0.
     * Порядок сравнение - хозяин, кличка животного, вес.
     * Крлички сортируются по алфавиту. Выше в списке тот, кто более легкий.
     * @param o - объект, с которым сравнивается текущий
     * @return < 0, если текущий объект предшествует, переданному. > 0, если текущий объект, следует за переданным и 0, если они равны.
     */
    @Override
    public int compareTo(Object o) {
        // хозяин, кличка животного, вес
        Pet pet = (Pet) o;
        if (this.hashCode() == pet.hashCode()) {
            if (this.equals(pet)) {
                return 0;
            }
        }
        // хозяин
        if (this.owner.compareTo(pet.owner) != 0) {
            return this.owner.compareTo(pet.owner);
        }
        // кличка животного
        if (this.petName.compareTo(pet.petName) != 0) {
            return this.petName.compareTo(pet.petName);
        }
        // вес
        if (this.weight != pet.weight) {
            return (this.weight - pet.weight);
        }

        return 0;
    }
}
