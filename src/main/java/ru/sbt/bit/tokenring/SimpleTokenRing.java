package ru.sbt.bit.tokenring;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimpleTokenRing {
    private List<mess>[] netMessageLists;
    private Thread[] nodes;
    private myLogger myLogger;
    private Random random;

    public SimpleTokenRing(int n) {
        myLogger = new myLogger();
        random = new Random();
        netMessageLists = new List[n];
        nodes = new Thread[n];
        for (int i = 0; i < n; i++) {
            netMessageLists[i] = new LinkedList<mess>();
        }
        for (int i = 0; i < n; i++) {
            nodes[i] = new Thread(new Node(netMessageLists[i], netMessageLists[(i + 1) % n], this));
        }
    }

    public void startTokenRing() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].start();
        }
    }

    public void plotThoughput(){
        double[][] throughPutLine = myLogger.getThroughPutLine();
        new Plot().makeAPlot(throughPutLine[0], throughPutLine[1]);
    }

    public void stopTokenRing() {
        Node.run = false;
    }

    public void sendMessage(mess message) {
        int index = random.nextInt(netMessageLists.length);
        synchronized (netMessageLists[index]) {
            netMessageLists[index].add(message);
        }
    }

    public void log(mess msg) {
        myLogger.log(msg);
    }
}
