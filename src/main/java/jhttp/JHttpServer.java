package jhttp;

import jhttp.logger.*;
import jhttp.route.*;

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
            logger.log(LogType.INFO, "find 1 user");
            new Thread(() -> {
                try {
                    userSession(cliSocket.getInputStream(), cliSocket.getOutputStream());
                    cliSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void userSession(InputStream istreamCli, OutputStream ostreamCli) throws IOException {
        try {
            ostreamCli.write("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\nHelloWorlds!".getBytes());
            ostreamCli.flush();
            logger.log(LogType.INFO,"HTTP/1.0 200 OK!");
        }catch (Exception e){
            e.printStackTrace();
            logger.log(LogType.ERROR,"HTTP/1.0 500 Internal Server Error!");
        }
    }
}