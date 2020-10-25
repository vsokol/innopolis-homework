package store.sokolov.innopolis.homework_11.task_01_02.server;

import store.sokolov.innopolis.homework_11.task_01_02.PropertyFileReadException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ServerConfig {
    /** порте сервера */
    final public int serverPort;
    /** команда на выход */
    final public String cmdQuit;
    /** Приветствие */
    final public String hello;
    /** Пометка приватных сообщений */
    final public String markOnPrivate;
    /** Сообщение при входе пользователя */
    final public String messageEnter;
    /** Сообщение при выхходе пользователя */
    final public String messageExit;

    /** Конструктор для создания объекта с чтение данных из конфигурационного файла
     * @param propertyFile - путь и имя конфигурационного файла
     * @throws PropertyFileReadException
     */
    public ServerConfig(String propertyFile) throws PropertyFileReadException {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(propertyFile), "UTF-8")) {
            Properties property = new Properties();
            property.load(reader);
            serverPort = Integer.parseInt(property.getProperty("server_port"));
            cmdQuit = property.getProperty("cmd_quit");
            hello = property.getProperty("hello");
            markOnPrivate = property.getProperty("private");
            messageEnter = property.getProperty("msg_enter");
            messageExit = property.getProperty("msg_exit");
        } catch (IOException e) {
            throw new PropertyFileReadException("Ошибка чтения файла свойств '" + propertyFile + "'", e);
        }
    }
}
