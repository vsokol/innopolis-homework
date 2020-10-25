package store.sokolov.innopolis.homework_11.task_01_02.server;

import store.sokolov.innopolis.homework_11.task_01_02.PropertyFileReadException;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Properties;
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
    /** список потоков для работы с пользователями */
    final public static CopyOnWriteArrayList<Worker> listWorker = new CopyOnWriteArrayList<>();
    /** список пользователей */
    final public static ConcurrentSkipListSet<String> users = new ConcurrentSkipListSet<>();
    public static ServerConfig config;

    /**
     * Запуск сервера
     * @param args список входных параметров. arg[0] - путь к конфигурационному файлу
     * @throws PropertyFileReadException
     */
    public static void main(String[] args) throws PropertyFileReadException {
        System.out.println("Start server");
        String propertyPath;
        if (args == null || args.length == 0) {
            //new IllegalArgumentException("Не задан путь и имя файла server.properties");
            propertyPath = "server.properties";
        } else {
            propertyPath = args[0];
        }
        config = new ServerConfig(propertyPath);
        waitConnections();
        System.out.println("Stop server");
    }

    /***
     * Стратуем сервер и ожидаем подключения клиентов.
     * Как только клиент подключиется запускаем для него отдельный поток обработки
     */
    private static void waitConnections() {
        // открываем серверный сокет и ожидаем подключений
        try (ServerSocket serverSocket = new ServerSocket(config.serverPort, 0, InetAddress.getByName("localhost"))) {
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
