package by.dma.crudwebapp.controller.controller;

import by.dma.crudwebapp.controller.business.CardService;
import by.dma.crudwebapp.controller.dto.CardRequestDTO;
import by.dma.crudwebapp.controller.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    ResponseEntity<Void> createCard(@Valid @RequestBody CardRequestDTO request, UriComponentsBuilder builder) {
        Long cardId = service.createCard(request);

        URI uri = builder.path("/api/cards/{id}").buildAndExpand(cardId).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{id}")
    ResponseEntity<Card> getCardById(@PathVariable("id") Long cardId) {
        return ResponseEntity.ok(service.getCardById(cardId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable("id") Long id, @Valid @RequestBody CardRequestDTO request) {
        return ResponseEntity.ok(service.updateCard(id, request));
    }
}
