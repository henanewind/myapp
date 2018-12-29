package com.hbm;

import com.hbm.domain.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @author wind
 * @date 2018/12/29
 */
public class TestBook {
    @Test
    public void testLoad() {
        Configuration configuration = new Configuration();
        configuration.configure().addClass(Book.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Book book = session.load(Book.class, 1);
        System.out.println(book);
        session.close();
    }
}
