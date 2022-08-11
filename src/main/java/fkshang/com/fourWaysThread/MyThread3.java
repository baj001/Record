package fkshang.com.fourWaysThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author baj
 * @creat 2022-06-13 21:52
 */
public class MyThread3 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println(index);
//                        log.info(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

