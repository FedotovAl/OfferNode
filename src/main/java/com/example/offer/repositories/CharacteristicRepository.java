package com.example.offer.repositories;

import com.example.offer.entities.Characteristic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CharacteristicRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Characteristic> getAll(){
        return entityManager
                .createQuery("from Characteristic", Characteristic.class)
                .getResultList();
    }

    public Characteristic getByID(int id){
        return entityManager.find(Characteristic.class, id);
    }

    public Characteristic add(Characteristic characteristic){
        entityManager.persist(characteristic);
        return characteristic;
    }

    public Characteristic update(int id, Characteristic characteristic){
        Characteristic originalCharacteristic = entityManager.find(Characteristic.class, id);
        if (originalCharacteristic != null){
            originalCharacteristic.setName(characteristic.getName());
            originalCharacteristic.setDescription(characteristic.getDescription());

            entityManager.merge(originalCharacteristic);
        }
        return originalCharacteristic;
    }

    public void remove(int id){
        Characteristic originalCharacteristic = entityManager.find(Characteristic.class, id);
        if (originalCharacteristic != null && originalCharacteristic.getOffersSetCharacter().isEmpty()){
            entityManager.remove(originalCharacteristic);
        }
    }

    public boolean isEmptyByName(String paramName) throws NullPointerException{
        List<Characteristic> originalCharacteristic = entityManager
                .createQuery("from Characteristic where name = '"
                                + paramName +
                                "'",
                        Characteristic.class
                )
                .getResultList();
        if (originalCharacteristic.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    //?
    public Characteristic getByName(String paramName){
        List<Characteristic> characteristicsList = entityManager
                .createQuery("from Characteristic where name = '"
                                + paramName +
                                "'",
                        Characteristic.class
                ).getResultList();
        if (characteristicsList.size() != 0){
            return characteristicsList.get(0);
        } else {
            return null;
        }
    }
}
