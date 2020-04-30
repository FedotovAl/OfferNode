package com.example.offer.repositories;

import com.example.offer.entities.Characteristic;
import com.example.offer.entities.Offer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class OfferRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Offer> getAll(){
        return entityManager
                .createQuery("from Offer", Offer.class)
                .getResultList();
    }

    public Offer getByID(int id){
        return entityManager.find(Offer.class, id);
    }

    public Offer add(Offer offer){
        entityManager.persist(offer);
        return offer;
    }

    public Offer update(int id, Offer offer){
        Offer originalOffer = entityManager.find(Offer.class, id);
        if (originalOffer != null){
            originalOffer.setName(offer.getName());
            originalOffer.setPrice(offer.getPrice());
            originalOffer.setPaidTypeID(offer.getPaidTypeID());
            originalOffer.setCategory(offer.getCategory());
            originalOffer.setCharacteristicSet(offer.getCharacteristicSet());

            entityManager.merge(originalOffer);
        }
        return originalOffer;
    }

    public void remove(int id){
        Offer originalOffer = entityManager.find(Offer.class, id);
        if (originalOffer != null){
            entityManager.remove(originalOffer);
            for (Characteristic c : originalOffer.getCharacteristicSet()){
                c.getOffersSetCharacter().remove(originalOffer);
            }
        }
    }
}
