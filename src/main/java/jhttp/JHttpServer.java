package jhttp;

import jhttp.route.Route;

import java.util.*;
import java.net.*;
import java.io.*;

public class JHttpServer {
    ServerSocket servSocket;
    Socket cliSocket;
    Map<String, Route> routeMap;

    public JHttpServer() {
        this.routeMap = new HashMap<>();
    }

    public boolean exist(String path) {
        return routeMap.containsKey(path);
    }

    public void sign(String path, Route route) {
        routeMap.put(path, route);
    }

    public void run(String addr, int port) throws IOException {
        System.out.printf("Server is running at http://%s:%d\n", addr, port);
        servSocket = new ServerSocket(port, 128, InetAddress.getByName(addr));
        while (true) {
            cliSocket = servSocket.accept();
            System.out.println("find 1user!");
            new Thread(() -> {
                try {
                    this.userSession(cliSocket.getInputStream(), cliSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void userSession(InputStream istreamCli, OutputStream ostreamCli){

    }
}