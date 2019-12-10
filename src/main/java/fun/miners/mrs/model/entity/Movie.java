package fun.miners.mrs.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /**
     * 导游
     */
    @Column
    private String director;

    /**
     * 编剧
     */
    @Column
    private String screenwriter;

    /**
     * 演员
     */
    @Column
    private String starring;

    @OneToOne(optional = false)
    @JoinColumn(name = "categoryId")
    private Category category;

    /**
     * 国家
     */
    @Column
    private String country;

    /**
     * 语言
     */
    @Column
    private String language;

    /**
     * 首映
     */
    @Column
    private Date firstTime;

    /**
     * 片长
     */
    @Column
    private String length;

    /**
     * 评分
     */
    @Column
    private float score;

    /**
     * 简介
     */
    @Column
    private String introduction;

    /**
     * 图片
     */
    @Column
    private String icon;
}
