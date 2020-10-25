package store.sokolov.innopolis.homework_11.task_01_02.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
    private final PrintStream writer;
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
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        writer = new PrintStream(socket.getOutputStream(),true, "UTF-8");
    }

    /**
     * Взаимодействие с пользователем
     */
    @Override
    public void run() {
        try {
            setUserName("@" + readUserName());
            String msgEnter = Server.config.messageEnter + " - " + getUserName();
            System.out.println(msgEnter);
            // известим всех, что подключился новый пользователь
            sendMessage(msgEnter, true);
            Server.users.add(getUserName().toLowerCase());
            writeCountUser();
            // поприветствуем пользователя
            writer.println(Server.config.hello + " " + getUserName() + "!");

            // читаем из потока сокета пока не встретится сообщение stop
            while (true) {
                String message = reader.readLine();
                if (message == null || Server.config.cmdQuit.equals(message.toLowerCase())) {
                    // пользователь покинул чат
                    writer.println(Server.config.cmdQuit);
                    userExit();
                    break;
                }
                System.out.println(getUserName() + ": " + message);
                sendMessage(message);
            }
        } catch (SocketException socketException) {
            try {
                userExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Рассылка сообщений (не информационных) как общих, так и приватных
     * @param message сообщение
     * @throws IOException
     */
    protected void sendMessage(String message) throws IOException {
        sendMessage(message, false);
    }

    /**
     * Рассылка всех видом сообщений (общие, личные, информационные)
     * @param message сообщение
     * @param isInfoMessage true, если сообщение информционное, иначе false
     */
    protected void sendMessage(String message, boolean isInfoMessage) {
        String userPrivate = null;
        if (!isInfoMessage) {
            userPrivate = getUserIfMessageIsPrivate(message);
        }
        // рассылаем сообщения всем пользователям
        for (Worker worker : Server.listWorker) {
            if (userPrivate != null) {
                // личное сообщение
                if (userPrivate.equals(worker.userName.toLowerCase())) {
                    worker.writer.println(getUserName() + Server.config.markOnPrivate + ": " + message);
                }
            } else {
                // сообщение всем пользователем, за исключением самого себя
                if (!worker.getUserName().toLowerCase().equals(getUserName().toLowerCase())) {
                    worker.writer.println((isInfoMessage ? "" : getUserName() + ": ") + message);
                }
            }
        }
    }

    /**
     * Набор действий связанных с выходом пользователя из чата
     * @throws IOException
     */
    protected void userExit() throws IOException {
        Server.users.remove(getUserName().toLowerCase());
        String msgExit = Server.config.messageExit + " - " + getUserName();
        System.out.println(msgExit);
        writeCountUser();
        // известим всех, что пользователь покинул чат
        sendMessage(msgExit, true);
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
        if (!Server.users.contains(user)) {
            return null;
        }
        return user;
    }

    protected void writeCountUser() {
        System.out.println("количество пользователей = " + Server.users.size());
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
