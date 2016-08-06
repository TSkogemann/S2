/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas Skogemann
 */
public class Consumer implements Runnable {

    private S2 s2;
    private Long res=0L;
    volatile boolean shutdown;

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public Consumer(S2 s2) {
        this.s2 = s2;
    }

    @Override
    public void run() {
        
        while (!shutdown || !s2.que.isEmpty()){
        Long takeNumber = 0L;
            try {
                takeNumber = s2.que.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        res = res + takeNumber;
        System.out.println(res);
        }
    }

}
