package fun.miners.mrs.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /**
     * 值
     */
    @Column
    private String value;

    /**
     * 父分类
     */
    @Column
    private String parent;
}
