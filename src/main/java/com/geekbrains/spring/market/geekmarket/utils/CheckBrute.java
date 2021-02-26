package com.geekbrains.spring.market.geekmarket.utils;

import java.security.Principal;

public class CheckBrute extends Check {
    private int counter;
    private int request;
    private long time;

    public CheckBrute(int counter) {
        this.counter = counter;
        this.time = System.currentTimeMillis();
    }

    @Override
    public boolean check(Principal principal, String pass) {
        long currentTime = System.currentTimeMillis();
        if (currentTime > time + 60000) {
            request = 0;
            time = currentTime;
        }

        request++;

        if (request > counter) {
            return false;
        }

        return checkNext(principal, pass);
    }
}
