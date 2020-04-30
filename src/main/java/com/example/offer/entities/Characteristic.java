package com.example.offer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "characteristics")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String description;

    @ManyToMany(mappedBy = "characteristicSet")
    @JsonIgnore
    private Set<Offer> OffersSetCharacter = new HashSet<>();

    @JsonIgnore
    public Set<Offer> getOffersSetCharacter() {
        return OffersSetCharacter;
    }
}
