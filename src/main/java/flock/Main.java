package flock;



import java.io.*;

public class Main {
    public static void main(String[] args) {
        JHttpServer server = new JHttpServer();
        server.sign("/", (req) -> "This is a test page!".getBytes());
        try {
            server.run("127.0.0.1", 81);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}