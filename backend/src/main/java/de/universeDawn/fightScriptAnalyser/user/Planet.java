package de.universeDawn.fightscriptanalyser.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Planet {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "planetName")
    private String planetName;
    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;
    @Column(name = "z")
    private int z;
    @ManyToOne
    @JoinColumn(name="srUser_id",nullable = true)
    @JsonIgnore
    private SrUser srUser;

    @ManyToMany(mappedBy = "target", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SrOrder> orders;
}
