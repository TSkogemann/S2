/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2;

/**
 *
 * @author Thomas Skogemann
 */
public class SP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        FibonacciCalculator cal = new FibonacciCalculator();
        S1 s1 = new S1();
        S2 s2 = new S2();

        Runner runFib = new Runner(4, s1, s2);
        runFib.start();
    }
}
