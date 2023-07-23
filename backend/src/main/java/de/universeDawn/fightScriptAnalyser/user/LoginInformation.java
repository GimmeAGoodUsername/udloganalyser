package de.universeDawn.fightscriptanalyser.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LoginInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "password", updatable = false)
    @JsonIgnore
    private String password;
    @Column(name = "name")
    private String name;
    @OneToOne
    @MapsId
    @JoinColumn(name="id",updatable = false)
    private SrUser srUser;
}
