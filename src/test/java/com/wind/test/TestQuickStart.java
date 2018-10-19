package com.wind.test;

import com.wind.dao.PersonDao;
import com.wind.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class TestQuickStart {
    private static ApplicationContext ctx;

    static {
        // 通过静态代码块的方式,让程序加载spring的配置文件
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    /**
     * INSERT INTO `springdata`.`jpa_persons` (`id`, `birth`, `email`, `name`) VALUES ('1', '2010-01-01 10:11:29', 'test0@126.com', 'test0');
     * INSERT INTO `springdata`.`jpa_persons` (`id`, `birth`, `email`, `name`) VALUES ('2', '2010-01-02 10:15:29', 'test1@126.com', 'test1');
     * INSERT INTO `springdata`.`jpa_persons` (`id`, `birth`, `email`, `name`) VALUES ('3', '2010-02-11 10:15:29', 'test2@126.com', 'test2');
     * INSERT INTO `springdata`.`jpa_persons` (`id`, `birth`, `email`, `name`) VALUES ('4', '2010-03-22 10:00:10', 'test3@126.com', 'test3');
     * INSERT INTO `springdata`.`jpa_persons` (`id`, `birth`, `email`, `name`) VALUES ('5', '2010-04-01 10:15:29', 'test4@126.com', 'test4');
     * INSERT INTO `springdata`.`jpa_persons` (`id`, `birth`, `email`, `name`) VALUES ('6', '2010-06-08 11:19:25', 'test5@126.com', 'test5');
     */
    @Test
    public void testGetById() {
        PersonDao personDao = ctx.getBean(PersonDao.class);
        Person person = personDao.getById(1);
        System.out.println("查询结果： name=" + person.getName() + ",id=" + person.getId());
    }

    @Test
    public void testGetByIdAndName() {
        PersonDao personDao = ctx.getBean(PersonDao.class);
        Person person = personDao.findByIdAndName(2, "test1");
        System.out.println(person);
    }

    @Test
    public void testFindByEmailLike() {
        PersonDao personDao = ctx.getBean(PersonDao.class);
        List<Person> list = personDao.findByEmailLike("test%");
        for (Person person : list) {
            System.out.println(person.getEmail());
        }
    }

    @Test
    public void testFindByIdIsLessThanOrBirthLessThan() {
        PersonDao personDao = ctx.getBean(PersonDao.class);
        List<Person> list = personDao.findByIdIsLessThanOrBirthLessThan(3, new Date());
        for (Person person : list) {
            System.out.println("查询结果： name=" + person.getName()
                    + ",id=" + person.getId() + ",birth=" + person.getBirth());
        }
    }

    @Test
    public void testFindByIdIsLessThanAndBirthLessThan() {
        PersonDao personDao = ctx.getBean(PersonDao.class);
        List<Person> list = personDao.findByIdIsLessThanAndBirthLessThan(3, new Date());
        for (Person person : list) {
            System.out.println("查询结果： name=" + person.getName()
                    + ",id=" + person.getId() + ",birth=" + person.getBirth());
        }
    }


    /**
     * 测试findByAddressId方法
     */
    @Test
    public void testFindByAddressId() {
        PersonDao personDao = ctx.getBean(PersonDao.class);
        // 查出地址id为1的person集合
        List<Person> list = personDao.findByAddressId(1);
        for (Person person : list) {
            System.out.println(person.getName()
                    + "---addressId="
                    + person.getAddress().getId());
        }
    }
}
