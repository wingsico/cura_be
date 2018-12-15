package org.jiangzuoqinglang.cura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user")
@Data
public class User implements Serializable{

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_JDPS_content")
    @SequenceGenerator(name = "SEQ_JDPS_content", sequenceName = "SEQ_JDPS_CONTENT", initialValue = 100000, allocationSize = 1)
    private int cura_number;

    @NotNull(message = "昵称不能为空")
    @Length(max = 16, message = "昵称过长")
    private String nickname;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^(1[34578]\\d{9})$", message = "手机号格式错误")
    private String phone;

    private String head_url;

    private String native_place;

    private String resume;

    private String signature;

    private String sex;

    private int age;

    private Date birthday;

    private String commend;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Group> groupList;
}
