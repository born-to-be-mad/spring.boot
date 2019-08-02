package by.dma.crudwebapp.controller.controller;

import by.dma.crudwebapp.controller.business.CardService;
import by.dma.crudwebapp.controller.dto.CardRequestDTO;
import by.dma.crudwebapp.controller.model.Card;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@RequestMapping("/api/cards")
@Api(tags = "Card API")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    ResponseEntity<Void> createCard(final @Valid @RequestBody CardRequestDTO request, final UriComponentsBuilder builder) {
        Long entityId = service.createCard(request);

        URI uri = builder.path("/api/cards/{id}").buildAndExpand(entityId).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieves information about card by its id")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "ok")})
    ResponseEntity<Card> getCardById(@PathVariable("id") Long cardId) {
        return ResponseEntity.ok(service.getCardById(cardId));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates card information")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "ok")})
    public ResponseEntity<Card> updateCard(@PathVariable("id") Long cardId, @Valid @RequestBody CardRequestDTO request) {
        return ResponseEntity.ok(service.updateCard(cardId, request));
    }

    @DeleteMapping("/{id}") @ApiOperation(value = "Deletes card by id")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
                            @ApiResponse(code = SC_NOT_FOUND, message = "Card is not found")
    })
    public ResponseEntity<Void> deleteCardById(@PathVariable("id") Long cardId) {
        service.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }
}
