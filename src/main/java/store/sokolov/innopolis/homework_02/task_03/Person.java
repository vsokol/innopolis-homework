package store.sokolov.innopolis.homework_02.task_03;

import java.util.Objects;

/**
 * Класс {@link Person} со свойствами <b>name</b>, <b>sex</b> и <b>age</b>.
 * @author Vladimir Sokolov
 */
public class Person implements Comparable {
    /** Имя человека */
    private String name;
    /** Пол человека */
    private Sex sex;
    /** Возраст человека */
    private int age;

    /**
     * Конструктор объекта типа {@link Person}
     * @param name имя человека
     * @param sex пол человека
     * @param age возраст человека
     * @throws IllegalAge выбрасывается, если возраст человека не попадает в промежуток от 0 до 100 лет
     */
    public Person(String name, Sex sex, int age) throws IllegalAge {
        this.name = name;
        this.sex = sex;
        checkAge(age);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalAge {
        checkAge(age);
        this.age = age;
    }

    /**
     * Проверяет возраст человека. Если он не попадает в промежуток от 0 до 100, то выбрасывает исключение.
     * @param age возраст человека
     * @throws IllegalAge исключение
     */
    private void checkAge(int age) throws IllegalAge {
        if (age < 0 || age > 100) {
            throw new IllegalAge("Возраст должен быть в пределах от 0 до 100", age);
        }
    }

    /**
     * Сравнивает экземпляра объекта
     * @param o - экземпляр объекта, с которым идет сравнение
     * @return <b>True</b>, если 2 экземпляра объекта равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name) &&
                sex == person.sex;
    }

    /**
     * Возвращает Hash код экземпляра объекта
     * @return Hash код текущего объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, sex, age);
    }

    @Override
    public String toString() {
        return (sex == Sex.MAN ? "мужчина" : "женщина") + ", " + age + ", " + name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return new Person(name, sex, age);
        }
        catch (IllegalAge e) {
            return null;
        }
    }

    /**
     * Реализация метода compareTo интерфейса {@link Comparable}
     * @param o экземпляр объекта, с которым сравнивается текущий объект
     * @return
     */
    @Override
    public int compareTo(Object o) {
        // первые идут мужчины
        // выше в списке тот, кто более старший
        // имена сортируются по алфавиту
        Person person = (Person) o;
        if (this.hashCode() == person.hashCode()) {
            if (this.equals(person)) {
                return 0;
            }
        }

        if (this.sex.equals(person.sex)) {
            if (this.age != person.age) {
                return this.age - person.age;
            } else {
                return this.name.compareTo(person.name);
            }
        } else if (this.sex == Sex.MAN && person.sex == Sex.WOMAN) {
            return -1;
        } else {
            return 1;
        }

//        if (this.sex == Sex.MAN && person.sex == Sex.WOMAN) {
//            return -1;
//        }
//        if (this.sex == Sex.WOMAN && person.sex == Sex.MAN) {
//            return 1;
//        }
//        if (this.age != person.age) {
//            return this.age - person.age;
//        }
//        return this.name.compareTo(person.name);
    }
}
