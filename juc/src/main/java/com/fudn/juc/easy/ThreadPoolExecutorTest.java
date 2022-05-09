package com.fudn.juc.easy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * @author fdn
 * @since 2022-03-02 17:03
 */
public class ThreadPoolExecutorTest {
    private static ExecutorService pool;

    public static void main(String[] args) {
        //优先任务队列
//        pool = new ThreadPoolExecutor(1,
//                2,
//                1000,
//                TimeUnit.MILLISECONDS,
//                new PriorityBlockingQueue<>(),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
        pool = new ThreadPoolExecutor(1,
                2,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            pool.execute(new ThreadTask(i));
            Future<?> submit = pool.submit(new ThreadTask(i));
        }
        pool.shutdown();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThreadTask implements Runnable, Comparable<ThreadTask> {

        private int priority;


        @Override
        public int compareTo(ThreadTask o) {
            // priority 倒序；所以 PriorityBlockingQueue 是大顶堆
            return o.priority - this.priority;
        }

        @Override
        public void run() {
            try {
                //让线程阻塞，使后续任务进入缓存队列
                Thread.sleep(1000);
                System.out.println("priority:" + this.priority + ",ThreadName:" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
