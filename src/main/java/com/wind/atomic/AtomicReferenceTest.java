package com.wind.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] agrs) {
        AtomicReference<User> a = new AtomicReference<User>();
        User user = new User("zhangsan", 12);
        User updateUser = new User("lisi", 45);

        a.set(user);
        System.out.println("-----------------before update--------------------");

        System.out.println(a.get().getName());
        System.out.println(a.get().getOld());
        a.compareAndSet(user, updateUser);
        System.out.println("-----------------after update--------------------");
        System.out.println(a.get().getName());
        System.out.println(a.get().getOld());
    }

    public static class User {
        private String name;
        private int old;

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
