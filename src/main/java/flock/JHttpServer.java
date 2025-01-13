package flock;

import flock.logger.*;
import flock.route.*;

import java.util.*;
import java.net.*;
import java.io.*;

public class JHttpServer {
    Logger logger = new Logger();
    ServerSocket servSocket;
    Socket cliSocket;
    Map<String, Route> routeMap;

    public JHttpServer() {
        routeMap = new HashMap<>();
    }

    public boolean exist(String path) {
        return routeMap.containsKey(path);
    }

    public void sign(String path, Route route) {
        if (exist(path)) {
            routeMap.put(path, route);
        }
    }

    public void run(String addr, int port) throws IOException {
        System.out.printf("Server is running at http://%s:%d\n", addr, port);
        servSocket = new ServerSocket(port, 128, InetAddress.getByName(addr));
        while (true) {
            cliSocket = servSocket.accept();
            new Thread(() -> userSession(cliSocket)).start();
        }
    }

    private void userSession(Socket cliSocket) {
        DataInputStream istreamCli;
        DataOutputStream ostreamCli;
        JHttpRequest request;
        try {
            istreamCli = new DataInputStream(cliSocket.getInputStream());
            ostreamCli = new DataOutputStream(cliSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            ostreamCli.write("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n".getBytes());
//            request = new JHttpRequest(new String(istreamCli.readAllBytes()));
//            ostreamCli.write(routeMap.get(request.path).handle(request));
//            ostreamCli.write("HelloWorld".getBytes());
            ostreamCli.flush();
            cliSocket.close();
            logger.log(LogType.INFO, "HTTP/1.1 200 OK!");
        } catch (NullPointerException nE) {
            try {
                ostreamCli.write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
            } catch (IOException ignored) {
            }
            logger.log(LogType.INFO, "HTTP/1.1 404 Not Found!");
        } catch (Exception e) {
            try {
                ostreamCli.write("HTTP/1.1 500 Internal Server Error\r\n\r\n".getBytes());
            } catch (IOException ignored) {
            }
            logger.log(LogType.ERROR, "HTTP/1.1 500 Internal Server Error!");
        }
    }
}