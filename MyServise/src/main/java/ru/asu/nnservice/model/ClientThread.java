package ru.asu.nnservice.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ClientThread.class);

    private static int nextId = 0;
    private final int id;

    private final Socket socket;

    public ClientThread(Socket socket) {
        super("ClientHandler #" + nextId);
        id = nextId++;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                OutputStreamWriter outputStream = new OutputStreamWriter(socket.getOutputStream());
                PrintWriter printWriter = new PrintWriter(outputStream, true);
                printWriter.println("Hello world");
            } catch (Exception ex) {
                logger.warn("Ошибка отправки клиенту сообщения:\n\t{}", ex.getMessage());
                break;
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                logger.warn("Поток прерван.");
                break;
            }

        }
    }
}
