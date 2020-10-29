package store.sokolov.innopolis.homework_13.task_02;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Класс загрузчик класса SomeClass
 *
 * @author Vladimir Sokolov
 */
public class MyClassLoader extends ClassLoader {
    /** путь и имя файла класса, который нужно загрузить */
    private String className;

    /**
     * Конструктор
     * @param className путь и имя файла класса, который нужно загрузить
     */
    public MyClassLoader(String className) {
        this.className = className;
    }

    /**
     * Загрузка класса
     * @param name название класса
     * @return загруженный класс
     */
    @Override
    protected Class<?> findClass(String name) {
        String compileClass = prepareClass(className);
        File classFile = new File(compileClass);
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(classFile))) {
            byte[] buffer = new byte[(int)classFile.length()];
            int count = inputStream.read(buffer, 0, buffer.length);
            Class<?> clazz = defineClass(name, buffer, 0, buffer.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Подготавливает файл с классом для загрузки
     * @param className - путь и имя файла класса, который нужно загрузить
     * @return путь и имя скомпилированного класса
     */
    public String prepareClass(String className) {
        String compileClass;
        try {
            PrepareClassForLoad prepareClassForLoad = new PrepareClassForLoad(className);
            compileClass = prepareClassForLoad.getPrepareClass();
        } catch (IOException | InterruptedException | ExceptionCompile e) {
            compileClass = "";
            e.printStackTrace();
        }
        return compileClass;
    }
}
