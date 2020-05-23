package com.example.offer.communication_between_microservices.controller;

import com.example.offer.communication_between_microservices.dto.IdDto;
import com.example.offer.entities.Offer;
import com.example.offer.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/offserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComController {

    @Autowired
    OfferService offerService;

    //3.1 пункт offerservice
    @GetMapping("/offersforuser/{userId}")
    public ResponseEntity<List<Offer>> getOffersForUserID(
            @PathVariable("userId") int id,
            @RequestHeader("Authorization") String token
    ){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<IdDto[]> responce = restTemplate.exchange(
                "http://127.0.0.1:8081/custserv/customers/" + id + "/paidtypes",
                HttpMethod.GET,
                entity,
                IdDto[].class);
        List<IdDto> idDtoList = Arrays.asList(responce.getBody());
        return new ResponseEntity<>(offerService.getOffersForCustomer(idDtoList), HttpStatus.OK);
    }

    //3.2
    @PostMapping("/ordercreate")
    public ResponseEntity orderCreate(
            @RequestBody Offer offer,
            @RequestHeader("Authorization") String token
    ){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(offer, headers);

        ResponseEntity response = restTemplate.exchange(
                "http://127.0.0.1:8082/ordserv/ordercreate",
                HttpMethod.POST,
                entity,
                Object.class
        );

        if (response.getStatusCodeValue() != 201){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
