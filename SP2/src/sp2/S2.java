/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Thomas Skogemann
 */
public class S2 {
    
    BlockingQueue<Long> que = new ArrayBlockingQueue<>(100);
    long[] res = new long[100];
}
