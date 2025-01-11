package jhttp;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        JHttpServer server = new JHttpServer();
        server.sign("/", () -> "Hello World!".getBytes());
        try {
            server.run("127.0.0.1", 80);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}