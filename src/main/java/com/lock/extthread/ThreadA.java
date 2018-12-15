package com.lock.extthread;

import com.lock.service.MyService;

public class ThreadA extends Thread {
    private MyService myService;

    public ThreadA(MyService myService) {
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.methodA();
    }
}
