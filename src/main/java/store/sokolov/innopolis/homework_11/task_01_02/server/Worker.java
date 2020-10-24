package store.sokolov.innopolis.homework_11.task_01_02.server;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;

/**
 * Класс для обмена сообщениями с пользователями.
 * Предназначен для работы во многопоточном режиме
 */
public class Worker extends Thread {
    /** сокет для обмена сообщениями с пользователем */
    private final Socket socket;
    /** читатель для чтения сообщений из сокета взаимодействия с пользователем */
    private final BufferedReader reader;
    /** писатель для записи сообщений в сокета взаимодействия с пользователем */
    private final BufferedWriter writer;
    /** имя пользователя */
    private String userName;

    /**
     * Конструктор для создания объета взаимодействия с пользователем.
     * Инициализирует сокет, входный и выходной стримы
     * @param socket сокет для взаимодействия с пользователем
     * @throws IOException при возникновении ошибки {@link IOException}
     */
    public Worker(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Взаимодействие с пользователем
     */
    @Override
    public void run() {
        try {
            setUserName("@" + readUserName());
            Server.users.putIfAbsent(getUserName().toLowerCase(), getUserName());
            System.out.println("Подключился пользователь - " + getUserName());
            // поприветствуем пользователя
            sendSingleMessage(Server.hello + getUserName() + "!");

            // читаем из потока сокета пока не встретится сообщение stop
            while (true) {
                String message = reader.readLine();
                if (message == null || Server.cmdQuit.equals(message.toLowerCase())) {
                    sendSingleMessage(Server.cmdQuit);
                    Server.users.remove(getUserName().toLowerCase());
                    System.out.println("Пользователь покинул чат - " + getUserName());
                    break;
                }
                System.out.println(getUserName() + ": " + message);
                sendMessages(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Рассылка сообщений как общих, так и приватных
     * @param message сообщение
     * @throws IOException
     */
    protected void sendMessages(String message) throws IOException {
        String userPrivate = getUserIfMessageIsPrivate(message);
        // рассылаем сообщения всем пользователям
        Iterator iterator = Server.listWorker.iterator();
        while (iterator.hasNext()) {
            Worker worker = (Worker) iterator.next();
            if (userPrivate != null) {
                // личное сообщение
                if (userPrivate.equals(worker.userName.toLowerCase())) {
                    worker.sendSingleMessage(getUserName() + ": " + message);
                }
            } else {
                // сообщение всем пользователем
                worker.sendSingleMessage(getUserName() + ": " + message);
            }
        }
    }

    /**
     * Возвращает имя пользователя, если сообщение личное, иначе null
     * @return имя пользователя, если сообщение личное, иначе null
     */
    protected String getUserIfMessageIsPrivate(String message) {
        if (message == null || message.isEmpty()) {
            return null;
        }
        if (!"@".equals(message.substring(0,1))) {
            return null;
        }
        if (message.indexOf(" ") == -1) {
            return null;
        }
        String user = message.substring(0, message.indexOf(" ")).toLowerCase();
        if (!Server.users.containsKey(user)) {
            return null;
        }
        return user;
    }

    /**
     * Отправка одиночного сообщения
     * @param message - сообщениу
     * @throws IOException
     */
    protected void sendSingleMessage(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    /**
     * Читаем имя пользователя
     * @return имя пользователя
     * @throws IOException
     */
    private String readUserName() throws IOException {
        return reader.readLine();
    }

    /**
     * Возвращает имя пользователя
     * @return имя пользователя
     */
    private String getUserName() {
        return userName;
    }

    /**
     * Устанавливает имя пользователя
     * @param userName имя пользователя
     */
    private void setUserName(String userName) {
        this.userName = userName;
    }
}
