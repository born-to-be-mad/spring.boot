package by.dma.explore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import by.dma.explore.service.TourRatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("API to just pull ratings")
@RestController
@RequestMapping(path = "/ratings")
public class RatingController {
    private final TourRatingService tourRatingService;

    private final RatingAssembler assembler;

    @Autowired
    public RatingController(TourRatingService tourRatingService, RatingAssembler assembler) {
        this.tourRatingService = tourRatingService;
        this.assembler = assembler;
    }

    @GetMapping
    public List<RatingDto> getAll() {
        return assembler.toResources(tourRatingService.lookupAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find ratings by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Rating not found") })
    public RatingDto getRating(@PathVariable("id") Integer id) {
        return assembler.toResource(tourRatingService.lookupRatingById(id)
                .orElseThrow(() -> new NoSuchElementException("Rating " + id + " not found"))
        );
    }


    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
