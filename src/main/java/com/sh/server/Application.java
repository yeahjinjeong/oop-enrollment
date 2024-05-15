package com.sh.server;

import com.sh.server.controller.Portal;

public class Application {
    public static void main(String[] args) {
        Portal portal = new Portal();
        portal.run();
    }
}