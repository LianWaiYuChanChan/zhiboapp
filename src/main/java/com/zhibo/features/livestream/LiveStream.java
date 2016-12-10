package com.zhibo.features.livestream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhibo.features.account.Account;
import com.zhibo.infra.ResponseObject;

import javax.persistence.*;

/**
 * Created by jichao on 2016/11/14.
 */
@Entity
@Table(name="livestream")
@JsonInclude(JsonInclude.Include.NON_NULL) //TODO: make it as global config.
public class LiveStream implements ResponseObject {

    public LiveStream() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getPullUrl() {
        return pullUrl;
    }

    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }

    public LiveStreamStatusEnum getStatus() {
        return status;
    }

    public void setStatus(LiveStreamStatusEnum status) {
        this.status = status;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Account getHost() {
        return host;
    }

    public void setHost(Account host) {
        this.host = host;
    }

    @Column(name="name")
    private String name;

    @Column(name="pushurl")
    private String pushUrl;

    @Column(name="pullurl")
    private String pullUrl;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private LiveStreamStatusEnum status;

    @Column(name="ispublic")
    private Boolean isPublic = false;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account host;

    @Id
    @SequenceGenerator(name="livestream_id_seq",
            sequenceName="livestream_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="livestream_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;
}
