package ru.asu.nnservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asu.nnservice.model.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Service {
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    private static final int PORT = 8999;

    public static void main(String[] args) {
        ArrayList<ClientThread> clients = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Сервер запущен.");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Клиент \"{}:{}\" подключился.",
                        clientSocket.getInetAddress(), clientSocket.getPort());
                ClientThread clientThread = new ClientThread(clientSocket);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException ex) {
            logger.error("Ошибка сокета сервера:\n\t{}", ex.getMessage());
        }
        logger.info("Сервер прекратил работу.");
    }
}