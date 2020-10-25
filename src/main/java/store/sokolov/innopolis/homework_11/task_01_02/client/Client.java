package store.sokolov.innopolis.homework_11.task_01_02.client;

import store.sokolov.innopolis.homework_11.task_01_02.PropertyFileReadException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * Задание 2.  Усовершенствовать задание 1:
 * - добавить возможность отправки личных сообщений (unicast).
 * - добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */
public class Client {
    /** порте сервера */
    private static int serverPort;
    /** команда на выход */
    public static String cmdQuit;
    /** имя пользователя */
    public static String userName;
    /** кодировка для консоли */
    public static String consoleCharSet;

    /**
     * Запуск клиента
     * @param args список входных параметров. arg[0] - путь к конфигурационному файлу
     * @throws PropertyFileReadException
     */
    public static void main(String[] args) throws PropertyFileReadException {
        System.out.println("Start client");
        String propertyPath;
        if (args == null || args.length == 0) {
            //new IllegalArgumentException("Не задан путь и имя файла server.properties");
            propertyPath = "client.properties";
        } else {
            propertyPath = args[0];
        }
        readProperty(propertyPath);
        connect();
        System.out.println("Stop client");
    }

    /** Чтение конфигурационного файла
     * @param propertyFile - путь и имя конфигурационного файла
     * @throws PropertyFileReadException
     */
    private static void readProperty(String propertyFile) throws PropertyFileReadException {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(propertyFile), "UTF-8")) {
            Properties property = new Properties();
            property.load(reader);
            serverPort = Integer.parseInt(property.getProperty("server_port"));
            cmdQuit = property.getProperty("cmd_quit");
            userName = property.getProperty("user_name");
            consoleCharSet = property.getProperty("console_char_set");
        } catch (IOException e) {
            throw new PropertyFileReadException("Ошибка чтения файла свойств '" + propertyFile + "'", e);
        }
    }

    /***
     * Стратуем клиент, подключаемя к серверу и общаемся.
     * Писать будем в текущем потоке, а для чтения запустим отдельный поток
     */
    private static void connect() {
        try (Socket socket = new Socket(InetAddress.getByName("localhost"), serverPort)) {
            ReceiveMessage receiveMessage = new ReceiveMessage(socket);
            SendMessage sendMessage = new SendMessage(socket);
            sendMessage.sendUserName(userName);
            // запускаем поток приема сообщений
            receiveMessage.start();
            sendMessage.run();
            // завершение работы отправки сообщений (в консоле написали - stop)
            // отправляем команду на остановку потока приема сообщений
            receiveMessage.interrupt();
            // ожидаем завершения потока приема сообщений
            while (receiveMessage.isAlive()) {
                Thread.sleep(500);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
