package com.wind.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
        User user = new User("weiming", 28);
        a.getAndIncrement(user);
        System.out.println(a.get(user));

    }

    public static class User {
        private String name;
        public volatile int old;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }
    }

}
