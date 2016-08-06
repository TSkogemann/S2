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
public class Producer implements Runnable {

    FibonacciCalculator calc = new FibonacciCalculator();
    private S1 s1;
    private S2 s2;

    public Producer(S1 s1, S2 s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        while (true) {
            Long number = s1.que.poll();
            if (number != null) {
                long calculatedNumber = calc.fib(number);
                try {
                    s2.que.put(calculatedNumber);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                break;
            }
        }
    }
}
