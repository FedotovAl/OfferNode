package com.example.offer.controllers;

import com.example.offer.entities.Characteristic;
import com.example.offer.services.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class CharacteristicController {

    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/characteristics")
    public ResponseEntity<List<Characteristic>> getAllCharacteristicsId(){
        return new ResponseEntity<>(characteristicService.getAllCharacteristics(), HttpStatus.OK);
    }

    @GetMapping("/characteristics/{characteristicsId}")
    public ResponseEntity<Characteristic> getCharacteristicByID(@PathVariable("characteristicsId") int id){
        if (characteristicService.getCharacteristicByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(characteristicService.getCharacteristicByID(id), HttpStatus.OK);
    }

    @PostMapping("/characteristics")
    public ResponseEntity<Characteristic> addCharacteristic(@RequestBody Characteristic characteristic){
        return new ResponseEntity<>(characteristicService.addNewCharacteristic(characteristic), HttpStatus.CREATED);
    }

    @PutMapping("/characteristics/{characteristicsId}")
    public ResponseEntity<Characteristic> updateCharacteristic(@PathVariable("characteristicsId") int id,
                                   @RequestBody Characteristic characteristic){
        if (characteristicService.getCharacteristicByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(characteristicService.updateCharacteristic(id, characteristic), HttpStatus.OK);
    }

    @DeleteMapping("/characteristics/{characteristicsId}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("characteristicsId") int id){
        if (characteristicService.getCharacteristicByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        characteristicService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
