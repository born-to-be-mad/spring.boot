package by.dma.demo.web;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import by.dma.demo.model.Mentee;
import by.dma.demo.service.MenteeNotFoundException;
import by.dma.demo.service.MenteeService;
import lombok.RequiredArgsConstructor;

/**
 * Duties:
 * <p>
 * <li>>URL mapping
 * <li>>Deserialize input mapping
 * <li>>Validate input
 * <li>>Business logic
 * <li>>Validate output
 * <li>>Translate exceptions
 */
@RestController
@RequiredArgsConstructor
public class MenteeResource {

    private final MenteeService menteeService;

    @GetMapping("/mentees/{id}")
    Mentee getStudent(@PathVariable Long id) {
        return menteeService.getMenteeById(id);
    }

/*    @PostMapping("/mentees/{id}")
    Mentee update(@PathVariable Long id, @Validated @RequestBody Mentee mentee) {
        service.update(id, mentee);
        return service.getMenteeById(id);
    }*/

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFound(MenteeNotFoundException exception) {

    }
}
