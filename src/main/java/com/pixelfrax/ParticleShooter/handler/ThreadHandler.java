package com.pixelfrax.ParticleShooter.handler;

import java.util.ArrayList;
import java.util.List;

public class ThreadHandler {

    private static List<Thread> allThreads = new ArrayList<Thread>();

    /**
     * adds a Thread
     * @param t
     */
    public static void addThread(Thread t) {
        if(!allThreads.contains(t)) {
            allThreads.add(t);
        }
    }

    /**
     * removes a Thread
     * @param t
     */
    public static void removeThread(Thread t) {
        if (allThreads.contains(t)) {
            allThreads.remove(t);
        }
    }

    public static boolean isAllowToRun(Thread t) {
        return allThreads.contains(t);
    }

    public static void stop() {
        allThreads = new ArrayList<Thread>();
    }

}
