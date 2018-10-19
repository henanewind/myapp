package com.wind.entity;

import javax.persistence.*;

/**
 * INSERT INTO `springdata`.`jpa_address` (`id`, `city`, `province`) VALUES ('1', '郑州', '河南');
 INSERT INTO `springdata`.`jpa_address` (`id`, `city`, `province`) VALUES ('2', '开封', '河南');
 INSERT INTO `springdata`.`jpa_address` (`id`, `city`, `province`) VALUES ('3', '漯河', '河南');
 */
@Entity
@Table(name = "jpa_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String city;
    @Column
    private String province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
