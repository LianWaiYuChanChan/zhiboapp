package com.zhibo.features.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhibo.infra.ResponseObject;

import javax.persistence.*;

import javax.persistence.GenerationType;

/**
 * Created by jichao on 2016/10/23.
 */

@Entity
@Table(name="account")
@JsonInclude(JsonInclude.Include.NON_NULL) //TODO: make it as global config.
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

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="name")
    private String name;

    @Id
    @SequenceGenerator(name="account_id_seq",
            sequenceName="account_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="account_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;
}
