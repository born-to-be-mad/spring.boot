package by.dma.crudwebapp.controller.business;

import by.dma.crudwebapp.controller.CardNotFoundException;
import by.dma.crudwebapp.controller.CardRepository;
import by.dma.crudwebapp.controller.dto.CardRequestDTO;
import by.dma.crudwebapp.controller.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Long createCard(CardRequestDTO request) {
        Card card = new Card();
        card.setAuthor(request.getAuthor());
        card.setContent(request.getContent());
        card.setDefinition(request.getDefinition());
        card.setHashTag(request.getHashTag());
        card.setCreationDate(new Date());

        Card savedCard = cardRepository.save(card);
        return savedCard.getId();
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(long cardId) {
        Optional<Card> requestedCard = cardRepository.findById(cardId);
        if (requestedCard.isEmpty()) {
            throw new CardNotFoundException(String.format("Card with id %s not found", cardId));
        }
        return requestedCard.get();
    }
}