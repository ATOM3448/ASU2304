package ru.asu.nnclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import com.sun.net.httpserver.HttpServer;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final String HOST = "localhost";
    private static final int PORT = 8999;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            logger.info("Клиент запущен.");
            while (true) {
                try {
                    InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStream);
                    if (bufferedReader.ready()) {
                        System.out.println(bufferedReader.readLine());
                    }
                } catch (Exception ex) {
                    logger.error("Ошибка получения сообщения от сервера\n\t{}", ex.getMessage());
                    break;
                }
            }
        } catch (IOException ex) {
            logger.error("Ошибка сокета\n\t{}", ex.getMessage());
        }
        logger.info("Клиент прекратил работу.");
    }
}
