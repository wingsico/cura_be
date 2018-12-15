package org.jiangzuoqinglang.cura.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Group {
    private Integer id;
    private Integer cura_number;
}
