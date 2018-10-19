package com.wind.dao;

import com.wind.entity.Person;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/*
 * 1.Repository是一个空接口，即是一个标记接口
 * 2.若我们定义的接口继承了Repository，则该接口会被IOC容器识别为一个Repository Bean
 * 注入到IOC容器中，进而可以在该接口中定义满足一定规则的接口
 * 3.实际上也可以通过一个注解@RepositoryDefination 注解来替代Repository接口
 */
//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
public interface PersonDao extends Repository<Person, Integer> {
    // 通过id查找实体
    Person getById(Integer id);

    /**
     * 简单条件查询
     *
     * @param id
     * @param name
     * @return
     */
    Person findByIdAndName(Integer id, String name);


    // where id < ? or birth < ?
    List<Person> findByIdIsLessThanOrBirthLessThan(Integer id, Date birth);

    // where email like ?
    List<Person> findByEmailLike(String email);

    // 也支持count查询
    long countByEmailLike(String email);

    List<Person> findByIdIsLessThanAndBirthLessThan(int i, Date date);


    // 级联查询，查询address的id等于条件值
    List<Person> findByAddressId(Integer addressId);
}
