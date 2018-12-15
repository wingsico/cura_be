package org.jiangzuoqinglang.cura.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group")
@Data
public class Group {
    @Id
    private int group_id;

    private String group_name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "cura_number")
    private User user;
}
