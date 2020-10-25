package store.sokolov.innopolis.homework_11.task_01_02.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveMessage extends Thread {
    /**
     * сокет для взаимодействия с сервером
     */
    private final Socket socket;
    /**
     * читатель для чтения сообщений из сокета взаимодействия с сервером
     */
    private final BufferedReader reader;

    /**
     * Конструктор для создания объета взаимодействия с пользователем, только прием сообщений
     * Инициализирует сокет и входной стримы.
     * @param socket сокет для взаимодействия с пользователем
     * @throws IOException
     */
    public ReceiveMessage(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    /**
     * Обработка входящих сообщений
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = reader.readLine();
                if (Client.cmdQuit.equals(message.toLowerCase())) {
                    break;
                }
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
