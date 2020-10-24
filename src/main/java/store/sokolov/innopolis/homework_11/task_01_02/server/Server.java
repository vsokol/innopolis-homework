package store.sokolov.innopolis.homework_11.task_01_02.server;

import store.sokolov.innopolis.homework_11.task_01_02.PropertyFileReadException;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * Задание 2.  Усовершенствовать задание 1:
 * - добавить возможность отправки личных сообщений (unicast).
 * - добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */
public class Server {
    /** порте сервера */
    private static int serverPort;
    /** команда на выход */
    public static String cmdQuit;
    /** Приветствие */
    public static String hello;
    /** список потоков для работы с пользователями */
    final public static CopyOnWriteArrayList<Worker> listWorker = new CopyOnWriteArrayList<>();
    /** список пользователей */
    final public static ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();

    /**
     * Запуск сервера
     * @param args список входных параметров. arg[0] - путь к конфигурационному файлу
     * @throws PropertyFileReadException
     */
    public static void main(String[] args) throws PropertyFileReadException {
        System.out.println("Start server");
        String propertyPath;
        if (args == null || args.length == 0) {
            //new IllegalArgumentException("Не задан путь и имя файла Server.properties");
            propertyPath = Paths.get(".").toAbsolutePath().toString().substring(0, Paths.get(".").toAbsolutePath().toString().length() - 2);
        } else {
//            String s = "D:\\java\\innopolis\\STC-31\\homework\\src\\main\\java\\store\\sokolov\\innopolis\\homework_11\\task_01\\server\\";
//            propertyPath = Paths.get(s).toAbsolutePath().toString();
            propertyPath = Paths.get(args[0]).toAbsolutePath().toString();
        }
        readProperty(propertyPath + "\\Server.properties");
        waitConnections();
        System.out.println("Stop server");
    }

    /** Чтение конфигурационного файла
     * @param propertyFile - путь и имя конфигурационного файла
     * @throws PropertyFileReadException
     */
    private static void readProperty(String propertyFile) throws PropertyFileReadException {
        try (FileInputStream fis = new FileInputStream(propertyFile)) {
            Properties property = new Properties();
            property.load(fis);
            serverPort = Integer.parseInt(property.getProperty("server_port"));
            cmdQuit = property.getProperty("cmd_quit");
            hello = property.getProperty("hello");
        } catch (IOException e) {
            throw new PropertyFileReadException("Ошибка чтения файла свойств '" + propertyFile + "'", e);
        }
    }

    /***
     * Стратуем сервер и ожидаем подключения клиентов.
     * Как только клиент подключиется запускаем для него отдельный поток обработки
     */
    private static void waitConnections() {
        // открываем серверный сокет и ожидаем подключений
        try (ServerSocket serverSocket = new ServerSocket(serverPort, 0, InetAddress.getByName("localhost"))) {
            while (true) {
                Socket socket = serverSocket.accept();
                Worker worker = new Worker(socket);
                listWorker.add(worker);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
