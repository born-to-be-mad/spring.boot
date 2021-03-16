package by.dma.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import by.dma.client.dto.Mentee;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MenteeClient {

    @Autowired
    private  final WebClient webClient;

    public Mentee getMentee(Long id) {
        return webClient.get().uri("/mentees/{id}", id)
            .retrieve()
            .bodyToMono(Mentee.class)
            .block();
    }
}
