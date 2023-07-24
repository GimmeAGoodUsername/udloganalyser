package de.universeDawn.fightscriptanalyser.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.universeDawn.fightscriptanalyser.config.BasicAuthWebSecurityConfiguration;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
public class SrUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "srUser")
    @JsonIgnore
    private LoginInformation loginInformation;
    @Column(name = "name")
    private String name;
    @Column(name = "race")
    private Race race;
    @Column(name = "role")
    private Role role;
    @OneToMany(mappedBy = "srUser")
    @JsonManagedReference
    private List<Planet> planets;
    @OneToMany(mappedBy = "orderedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SrOrder> order;
    @OneToMany(mappedBy = "deliveryBoy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SrOrder> delivery;
    @Column(name = "auth")
    private String authorities = BasicAuthWebSecurityConfiguration.NONE;


}
