package com.example.offer.communication_between_microservices;

import com.example.offer.entities.Offer;
import com.example.offer.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/offserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComController {

    @Autowired
    OfferRepository offerRepository;

    //3.1 пункт offerservice
    @GetMapping("/offersforuser/{userId}")
    public ResponseEntity<List<Offer>> getOffersForUserID(@PathVariable("userId") int id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UtilEntity[]> responce = restTemplate.getForEntity(
                "http://127.0.0.1:8081/custserv/customers/" + id + "/paidtypes",
                UtilEntity[].class);
        List<UtilEntity> utilEntities = Arrays.asList(responce.getBody());
        return new ResponseEntity<>(offerRepository.getOffersForCustomer(utilEntities), HttpStatus.OK);
    }
}
