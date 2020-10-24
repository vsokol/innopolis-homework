package store.sokolov.innopolis.homework_11.task_01_02.client;

import java.io.*;
import java.net.Socket;

public class SendMessage extends Thread {
    /**
     * сокет для взаимодействия с сервером
     */
    private final Socket socket;
    /**
     * писатель для отправки сообщений в сокета взаимодействия с сервером
     */
    private final BufferedWriter writer;

    /**
     * Конструктор для создания объета взаимодействия с пользователем, только отправка сообщений
     * Инициализирует сокет и выходной стримы.
     * @param socket сокет для взаимодействия с пользователем
     * @throws IOException
     */
    public SendMessage(Socket socket) throws IOException {
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Читаем текст с консоли и отправляем его на сервер
     */
    @Override
    public void run() {
        try(BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String message = console.readLine();
                sendMessage(message);
                if (Client.cmdQuit.equals(message.toLowerCase())) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправляем сообщение на сервер
     * @param message сообщение
     * @throws IOException
     */
    protected void sendMessage(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    /**
     * Отправляем имя пользователя на сервер
     * @param userName имя пользователя
     * @throws IOException
     */
    protected void sendUserName(String userName) throws IOException {
        sendMessage(userName);
    }
}
