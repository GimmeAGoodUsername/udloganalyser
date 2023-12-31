package de.universeDawn.fightscriptanalyser.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class SrOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "titan")
    private int titan;
    @Column(name = "silicon")
    private int silicon;
    @Column(name = "helium")
    private int helium;
    @Column(name = "food")
    private int food;
    @Column(name = "water")
    private int water;
    @Column(name = "alu")
    private int alu;
    @Column(name = "baux")
    private int baux;
    @Column(name = "uran")
    private int uran;
    @Column(name = "pluto")
    private int pluto;
    @Column(name = "hydro")
    private int hydro;
    @Column(name = "credits")
    private int credits;
    @Column(name ="deliveryDate")
    private Date deliveryDate;
    @Column(name ="creationDate")
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name="planet_id",nullable = true)
    private Planet target;
    @ManyToOne
    @JoinColumn(name="srUserOrder_id",nullable = true)
    private SrUser orderedBy;
    @ManyToOne
    @JoinColumn(name="srUserTarget_id",nullable = true)
    private SrUser deliveryBoy;
    @Column(name = "status")
    private boolean status = false;

}
