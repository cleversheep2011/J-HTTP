package flock;

import java.util.Map;
import java.util.SimpleTimeZone;

public class JHttpRequest {
    Map<String, String> argMap, cookieMap, sessionMap;
    String method, path;
    public JHttpRequest(String header) {
        String[] splitHeader=header.split("\r\n");

        for (int i = 1; i < splitHeader.length; i++) {
            String[] line=splitHeader[i].split(": ");
            argMap.put(line[0],line[1]);
        }
    }
}
