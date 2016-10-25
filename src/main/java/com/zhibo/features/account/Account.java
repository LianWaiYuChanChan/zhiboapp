package com.zhibo.features.account;

import com.zhibo.infra.ResponseObject;

import javax.persistence.*;

import javax.persistence.GenerationType;

/**
 * Created by jichao on 2016/10/23.
 */

@Entity
@Table(name="_account")
public class Account implements ResponseObject {

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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="_phoneNumber")
    private String phoneNumber;

    @Column(name="_name")
    private String name;

    @Id
    @SequenceGenerator(name="_account__id_seq",
            sequenceName="_account__id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="_account__id_seq")
    @Column(name = "_id", updatable=false)
    private Long id;
}
