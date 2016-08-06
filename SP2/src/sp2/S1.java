/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Thomas Skogemann
 */
public class S1 {
    
    BlockingQueue<Long> que = new ArrayBlockingQueue<>(100);
    Long[] s1numbers = new Long[]{4L,5L,8L,12L,21L,22L,34L,35L,36L,37L,42L};

    public S1() {
        que.addAll(Arrays.asList(s1numbers));
    }
    
}
