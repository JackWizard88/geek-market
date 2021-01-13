package com.geekbrains.spring.market.geekmarket.utils;

import java.security.Principal;

public abstract class Check {
    private Check next;

    public Check link(Check next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Principal principal, String pass);

    protected boolean checkNext(Principal principal, String pass) {
        if (next == null) {
            return true;
        }

        return next.check(principal, pass);
    }

}
