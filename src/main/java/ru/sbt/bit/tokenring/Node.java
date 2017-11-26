package ru.sbt.bit.tokenring;

import java.util.Iterator;
import java.util.List;

public class Node implements Runnable {
    public static volatile boolean run = true;
    private List<mess> getQueue;
    private List<mess> putQueue;
    private SimpleTokenRing simpleTokenRing;

    public Node(List<mess> getQueue, List<mess> putQueue, SimpleTokenRing simpleTokenRing) {
        this.getQueue = getQueue;
        this.putQueue = putQueue;
        this.simpleTokenRing = simpleTokenRing;
    }

    public void run() {
        while(Node.run) {
            if (!getQueue.isEmpty()) {
                mess mess;
                synchronized (getQueue) {
                    Iterator<mess> iterator = getQueue.iterator();
                    mess = iterator.next();
                    iterator.remove();
                }
                mess.decTtl();
                if (mess.isDead()) {
                    simpleTokenRing.log(mess);
                }
                else synchronized (putQueue) {
                    putQueue.add(mess);
                }
            }
        }
    }
}
