package com.example.offer.controllers;

import com.example.offer.entities.Offer;
import com.example.offer.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {

    @Autowired
    OfferService offerService;

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getAllOffers(){
        return new ResponseEntity<>(offerService.getAllOffers(), HttpStatus.OK);
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<Offer> getOfferByID(@PathVariable("offerId") int id){
        if (offerService.getOfferByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offerService.getOfferByID(id), HttpStatus.OK);
    }

    @PostMapping("/offers")
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer){
        return new ResponseEntity<>(offerService.addNewOffer(offer), HttpStatus.CREATED);
    }

    @PutMapping("/offers/{offerId}")
    public ResponseEntity<Offer> updateOffer(@PathVariable("offerId") int id,
                                   @RequestBody Offer offer){
        if (offerService.getOfferByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offerService.updateOffer(id, offer), HttpStatus.OK);
    }

    @DeleteMapping("/offers/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable("offerId") int id){
        if (offerService.getOfferByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
