package jhttp.route;

import jhttp.JHttpRequest;

public interface Route {
    public byte[] handle(JHttpRequest request);
}
