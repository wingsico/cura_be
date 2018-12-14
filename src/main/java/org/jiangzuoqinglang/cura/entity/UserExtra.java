package org.jiangzuoqinglang.cura.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user_extra")
@Data
public class UserExtra implements Serializable{
    @Id
    @Column(unique = true)
    private int cura_number;

    @Column
    private String head_url;

    @Column
    private String native_place;

    @Column
    private String resume;

    @Column
    private String signature;

    @Column
    private String sex;

    @Column
    private int age;

    @Column
    private Date birthday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Group> groupList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commend> commendList;
}
