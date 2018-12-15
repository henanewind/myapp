package com.lock.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    public void methodA() {
        String methodName = "methodA";
        priintMethod(methodName);
    }

    private void priintMethod(String methodName) {
        try {
            lock.lock();
            System.out.println(methodName + " begin ThreadName=" + Thread.currentThread().getName()
                + " time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(methodName + " end ThreadName=" + Thread.currentThread().getName()
                + " time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        String methodName = "methodB";
        priintMethod(methodName);
    }

}
