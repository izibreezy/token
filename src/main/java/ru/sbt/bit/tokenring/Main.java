package ru.sbt.bit.tokenring;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 8;
        SimpleTokenRing simpleTokenRing = new SimpleTokenRing(n);
        simpleTokenRing.startTokenRing();
        for (int i = 0; i < 2000000; i++) {
            simpleTokenRing.sendMessage(new mess(n * 2));
        }
        Thread.sleep(20000);
        simpleTokenRing.stopTokenRing();
        simpleTokenRing.plotThoughput();
    }
}
