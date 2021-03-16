package by.dma.demo.web.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import by.dma.demo.model.Mentee;
import by.dma.demo.persist.MenteeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenteeService {

    private final MenteeRepository menteeRepository;

    @Cacheable("mentees")
    public Mentee getMenteeById(Long id) {
        return menteeRepository.findById(id).orElse(null);
    }
}
