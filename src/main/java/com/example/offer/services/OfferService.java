package com.example.offer.services;

import com.example.offer.entities.Categories;
import com.example.offer.entities.Characteristic;
import com.example.offer.entities.Offer;
import com.example.offer.repositories.CategoriesRepository;
import com.example.offer.repositories.CharacteristicRepository;
import com.example.offer.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public List<Offer> getAllOffers(){
        return offerRepository.getAll();
    }

    public Offer getOfferByID(int id){
        return offerRepository.getByID(id);
    }

    //TODO
    public Offer addNewOffer(Offer offer){
        Categories categories = categoriesRepository.getByName(offer.getCategory().getName());
        if (categories != null){
            offer.setCategory(categories);
        }

        Set<Characteristic> chSet = new HashSet<>();
        for (Characteristic ch : offer.getCharacteristicSet()){
            Characteristic characteristic = characteristicRepository.getByName(ch.getName());
            if (characteristic != null){
                chSet.add(characteristic);
            } else {
                chSet.add(ch);
            }
        }
        offer.setCharacteristicSet(chSet);

        return offerRepository.add(offer);
    }

    public Offer updateOffer(int id, Offer offer){
        Categories categories = categoriesRepository.getByName(offer.getCategory().getName());
        Offer originalOffer = offerRepository.getByID(id);
        originalOffer.getCategory().getOffersSetCategor().remove(originalOffer);
        categoriesRepository.remove(originalOffer.getCategory().getId());
        originalOffer.setCategory(null);
        if (categories != null) {
            offer.setCategory(categories);
        }

        Set<Characteristic> chSet = new HashSet<>();
        for (Characteristic ch : offer.getCharacteristicSet()){
            Characteristic characteristic = characteristicRepository.getByName(ch.getName());
            if (characteristic != null){
                chSet.add(characteristic);
            } else {
                chSet.add(ch);
            }
        }
        offer.setCharacteristicSet(chSet);

        return offerRepository.update(id, offer);
    }

    public void remove(int id){
        Offer offer = offerRepository.getByID(id);
        offerRepository.remove(id);
        categoriesRepository.remove(offer.getCategory().getId());
    }
}
