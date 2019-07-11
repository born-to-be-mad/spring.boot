package by.dma.crudwebapp.controller.business;

import by.dma.crudwebapp.controller.CardRepository;
import by.dma.crudwebapp.controller.dto.CardRequestDTO;
import by.dma.crudwebapp.controller.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
