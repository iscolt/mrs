package fun.miners.mrs.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /**
     * 头像
     */
    @Column
    private String icon;

    /**
     * 昵称
     */
    @Column
    private String nickname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    /**
     * 0 管理, 1 普通用户
     */
    @Column
    private int role;
}
