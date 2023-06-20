package de.universeDawn.fightscriptanalyser.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class SrUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "race")
    private Race race;
    @Column(name = "role")
    private Role role;
    @OneToMany(mappedBy = "srUser")
    private List<Planet> planets;
    @OneToMany(mappedBy = "orderedBy", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SrOrder> order;
    @OneToMany(mappedBy = "deliveryBoy", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SrOrder> delivery;

}
