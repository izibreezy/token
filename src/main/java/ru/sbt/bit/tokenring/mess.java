package ru.sbt.bit.tokenring;


import java.util.Date;

public class mess {
    private int ttl;
    private Date date;

    public mess(int ttl) {
        this.ttl = ttl;
        this.date = new Date();
    }

    public synchronized void decTtl() {
        this.ttl--;
    }

    public boolean isDead() {
        return ttl <= 0;
    }

    public Date getDate() {
        return date;
    }
}
