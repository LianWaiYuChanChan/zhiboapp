package com.zhibo.features.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jichao on 2016/10/23.
 */

@Entity
@Table(name="_account")
public class Account {

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="_phoneNumber")
    private String phoneNumber;

    @Column(name="_name")
    private String name;

    @Id
    @Column(name="_id")
    private String id;
}
