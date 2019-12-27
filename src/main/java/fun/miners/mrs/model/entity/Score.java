package fun.miners.mrs.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 评分
 */
@Data
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private float score;

    @ManyToOne(optional = false)
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private Movie movie;
}
