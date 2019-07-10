package by.dma.crudwebapp.business;

import by.dma.crudwebapp.CardRepository;
import by.dma.crudwebapp.dto.CardRequestDTO;
import by.dma.crudwebapp.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Long createCard(CardRequestDTO request) {
        Card card = new Card();
        card.setAuthor(request.getAuthor());
        card.setContent(request.getContent());
        card.setDefinition(request.getDefinition());
        card.setCreationDate(request.getCreationDate());
        card.setHashTag(request.getHashTag());

        Card savedCard = cardRepository.save(card);
        return savedCard.getId();
    }
}
