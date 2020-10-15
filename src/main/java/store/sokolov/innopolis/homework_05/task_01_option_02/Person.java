package store.sokolov.innopolis.homework_05.task_01_option_02;

/**
* Класс человек
*
* @author Vladimir Sokolov
*/
public class Person implements Comparable {
    /** имя человека */
    private String name;
    /** пол человека */
    private Sex sex;
    /** возраст человека */
    private int age;

    /**
     * Конструктор человека
     *
     * @param name - имя человека
     * @param age  - возраст человека
     * @param sex  - пол человека
     */
    public Person(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    /**
     * Возвращает значение поля name
     * @return значение поля name
     */
    public String getName() {
        return name;
    }

    /** Вазвращает значение поля age
     * @return значение поля age
     */
    public int getAge() {
        return age;
    }

    /** Возвращает значение поля sex
     * @return значение поля sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Возвращает строковое представление объекта {@link Person}
     * @return строковое представление текущего объекта
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + (sex == Sex.MAN ? "мужчина" : "женщина") +
                '}';
    }

    /**
     * Сравнивает текущий объект с указанным. Сравнение производится по всем полям класса Person
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

        Person person = (Person) o;
        if (age != person.age) {
            return false;
        }
        if (!name.equals(person.name)) {
            return false;
        }
        return sex == person.sex;
    }

    /**
     * Возвращает hash код текущего объекта
     * @return hash код
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + sex.hashCode();
        return result;
    }

    /**
     * Сравнение текущего объекта с указанным. Результатом будет отрицательное число,
     * если текущий объет предшествует переданному. Рузультатом будет положительное число,
     * если текущий объект следует за переданным. Если объекты равны, то результатом будет 0.
     * Порядок сравнение - имя, возраст, пол. Имена сортируются по алфавиту.
     * Выше в списке тот, кто более старший. Женщины следуют раньше мужчит.
     * @param o - объект, с которым сравнивается текущий
     * @return < 0, если текущий объект предшествует, переданному. > 0, если текущий объект, следует за переданным и 0, если они равны.
     */
    @Override
    public int compareTo(Object o) {
        // Порядок сортировки - имя, возраст, пол
        // имена сортируются по алфавиту
        // выше в списке тот, кто более старший
        // женищины раньше мужчин

        Person person = (Person) o;
        if (this.hashCode() == person.hashCode()) {
            if (this.equals(person)) {
                return 0;
            }
        }
        // сравнение имен
        if (this.name.compareTo(person.name) != 0) {
            return this.name.compareTo(person.name);
        }
        // сравнение возраста
        if (this.age != person.age) {
            return -1 * (this.age - person.age);
        }
        // сравнение пола
        if (this.sex != person.sex) {
            if (this.sex == Sex.WOMAN) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
