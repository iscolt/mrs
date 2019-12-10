package fun.miners.mrs.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private Movie movie;
}
