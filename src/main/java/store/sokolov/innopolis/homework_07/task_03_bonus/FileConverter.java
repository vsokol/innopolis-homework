package store.sokolov.innopolis.homework_07.task_03_bonus;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Класс для конвертации файла из одной кодировки в другую.
 * По умолчанию на источкике кодировке UTF-8, а на приемнике UTF-16
 *
 * @author Vladimir Sokolov
 */
public class FileConverter {
    /** Путь и имя файла источника */
    private String fileSource;
    /** Путь и имя файла приемника */
    private String fileDestination;
    /** Кодировка файла источника */
    private Charset charSetSource = StandardCharsets.UTF_8;
    /** Кодировка файла приемника */
    private Charset charSetDestination = StandardCharsets.UTF_16;

    /**
     * Конструктор конвертера. Кодировка источника и приемника задаются по умолчанию.
     * @param fileSource путь и имя файла источника
     * @param fileDestination путь и имя файла приемника
     */
    public FileConverter(String fileSource, String fileDestination) {
        this.fileSource = fileSource;
        this.fileDestination = fileDestination;
    }

    /**
     * Конструктор конвертера
     * @param fileSource путь и имя файла источника
     * @param fileDestination путь и имя файла приемника
     * @param charSetSource кодировка файла источника
     * @param charSetDestination кодировка файла приемника
     */
    public FileConverter(String fileSource, String fileDestination, Charset charSetSource, Charset charSetDestination) {
        this(fileSource, fileDestination);
        this.charSetSource = charSetSource != null ? charSetSource : this.charSetSource;
        this.charSetDestination = charSetDestination != null ? charSetDestination : this.charSetDestination;
    }

    /**
     * Читает файл {@link FileConverter#fileSource} в кодировке {@link FileConverter#charSetSource} и
     * сохраняет его в файл {@link FileConverter#fileDestination} в кодировке {@link FileConverter#charSetDestination}
     * @throws IOException выбрасывается, если возникают ошибки при работе с файлами
     */
    public void convert() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileSource);
        InputStreamReader reader = new InputStreamReader(fileInputStream, charSetSource);

        FileOutputStream fileOutputStream = new FileOutputStream(fileDestination);
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charSetDestination);

        char[] buffer = new char[fileInputStream.available()];
        while (reader.ready()) {
            int count = reader.read(buffer);
            writer.write(buffer);
        }

        writer.flush();
        reader.close();
        fileInputStream.close();
        writer.close();
        fileOutputStream.close();
    }
}
