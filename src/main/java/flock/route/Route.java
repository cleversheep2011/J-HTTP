package flock.route;

import flock.JHttpRequest;

public interface Route {
    public byte[] handle(JHttpRequest request);
}
