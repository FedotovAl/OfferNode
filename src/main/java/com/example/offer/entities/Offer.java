package com.example.offer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "paidtypeid")
    private Integer paidTypeID;

    //Category
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid")
    private Categories category;

    //Characteristic
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "characteristics_offers",
            joinColumns = @JoinColumn(name = "offerid"),
            inverseJoinColumns = @JoinColumn(name = "characteristicid")
    )
    private Set<Characteristic> characteristicSet = new HashSet<>();
}
