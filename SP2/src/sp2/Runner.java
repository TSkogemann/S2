/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Skogemann
 */
public class Runner {

    private final S1 ss1;
    private final S2 ss2;
    List<Thread> producerList = new ArrayList<>();
    int producerCount;

    public Runner(int producerCount, S1 s1, S2 s2) {
        this.ss1 = s1;
        this.ss2 = s2;
        this.producerCount = producerCount;
    }

    public void start() throws InterruptedException {
        for (int i = 0; i < producerCount; i++) {
            Thread temp = new Thread(new Producer(ss1, ss2));
            producerList.add(temp);
            temp.start();
        }

        Consumer c1 = new Consumer(ss2);
        Thread consumer = new Thread(c1);
        consumer.start();

        for (Thread thread : producerList) {
            thread.join();
        }
        c1.setShutdown(true);
        consumer.interrupt();
    }
}
