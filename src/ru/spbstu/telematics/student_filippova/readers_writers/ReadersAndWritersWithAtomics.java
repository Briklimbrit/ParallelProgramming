package ru.spbstu.telematics.student_filippova.readers_writers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadersAndWritersWithAtomics {

    static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            threads.add(generateWriter());
        }
        for (int i = 0; i < 10; i++) {
            threads.add(generateReader());
        }

        for (Thread t : threads) {
            t.start();
        }
    }

    private static Thread generateReader() {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    int readNum = num.get();
                    System.out.println(Thread.currentThread().getName() + " reader read " + readNum);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static Thread generateWriter() {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    int writeVal = num.incrementAndGet();

                    System.out.println(Thread.currentThread().getName() + " writer generated " + writeVal);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    System.out.println("Writer will sleep 5 sec ");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}