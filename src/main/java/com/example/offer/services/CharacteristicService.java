package com.example.offer.services;

import com.example.offer.entities.Characteristic;
import com.example.offer.repositories.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CharacteristicService {

    @Autowired
    CharacteristicRepository characteristicRepository;

    public List<Characteristic> getAllCharacteristics(){
        return characteristicRepository.getAll();
    }

    public Characteristic getCharacteristicByID(int id){
        return characteristicRepository.getByID(id);
    }

    public Characteristic addNewCharacteristic(Characteristic characteristic){
        if (characteristicRepository.isEmptyByName(characteristic.getName())) {
            return characteristicRepository.add(characteristic);
        } else {
            return null;
        }
    }

    public Characteristic updateCharacteristic(int id, Characteristic characteristic){
        if (characteristicRepository.isEmptyByName(characteristic.getName())) {
            return characteristicRepository.update(id, characteristic);
        } else {
            return null;
        }
    }

    public void remove(int id){
        characteristicRepository.remove(id);
    }
}
