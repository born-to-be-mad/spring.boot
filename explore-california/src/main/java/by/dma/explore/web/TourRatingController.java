package by.dma.explore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import by.dma.explore.domain.TourRating;
import by.dma.explore.service.TourRatingService;

/**
 * Tour Rating Controller
 */
@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
  private static final Logger LOGGER =
          LoggerFactory.getLogger(TourRatingController.class);

  private TourRatingService tourRatingService;
  private RatingAssembler assembler;


  @Autowired
  public TourRatingController(TourRatingService tourRatingService,
                              RatingAssembler assembler) {
    this.tourRatingService = tourRatingService;
    this.assembler = assembler;
  }

  protected TourRatingController() {

  }

  /**
   * Create a Tour Rating.
   *
   * @param tourId
   * @param ratingDto
   */
  @PostMapping
  @PreAuthorize("hasRole('ROLE_CSR')")
  @ResponseStatus(HttpStatus.CREATED)
  public void createTourRating(@PathVariable(value = "tourId") int tourId,
                               @RequestBody @Validated RatingDto ratingDto) {
    LOGGER.info("POST /tours/{}/ratings", tourId);
    tourRatingService.createNew(tourId,
                                ratingDto.getCustomerId(),
                                ratingDto.getScore(),
                                ratingDto.getComment());
  }

  /**
   * Create Several Tour Ratings for one tour, score and several customers.
   *
   * @param tourId
   * @param score
   * @param customers
   */
  @PostMapping("/{score}")
  @PreAuthorize("hasRole('ROLE_CSR')")
  @ResponseStatus(HttpStatus.CREATED)
  public void createManyTourRatings(@PathVariable(value = "tourId") int tourId,
                                    @PathVariable(value = "score") int score,
                                    @RequestParam("customers") Integer customers[]) {
    LOGGER.info("POST /tours/{}/ratings/{}", tourId, score);
    tourRatingService.rateMany(tourId, score, customers);
  }

  /**
   * Lookup a the Ratings for a tour.
   *
   * @param tourId
   * @param pageable
   * @return
   */
  @GetMapping
  public Page<RatingDto> getAllRatingsForTour(
          @PathVariable(value = "tourId") int tourId, Pageable pageable) {
    LOGGER.info("GET /tours/{}/ratings", tourId);
    Page<TourRating> tourRatingPage =
            tourRatingService.lookupRatings(tourId, pageable);
    List<RatingDto> ratingDtoList = tourRatingPage.getContent()
                                                  .stream()
                                                  .map(this::toDto)
                                                  .collect(Collectors.toList());
    return new PageImpl<>(ratingDtoList,
                          pageable,
                          tourRatingPage.getTotalPages());
  }

  /**
   * Calculate the average Score of a Tour.
   *
   * @param tourId
   * @return Tuple of "average" and the average value.
   */
  @GetMapping("/average")
  public AbstractMap.SimpleEntry<String, Double> getAverage(
          @PathVariable(value = "tourId") int tourId) {
    LOGGER.info("GET /tours/{}/ratings/average", tourId);
    return new AbstractMap.SimpleEntry<>(
            "average", tourRatingService.getAverageScore(tourId));
  }

  /**
   * Update score and comment of a Tour Rating
   *
   * @param tourId
   * @param ratingDto
   * @return The modified Rating DTO.
   */
  @PutMapping
  @PreAuthorize("hasRole('ROLE_CSR')")
  public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId,
                                 @RequestBody @Validated RatingDto ratingDto) {
    LOGGER.info("PUT /tours/{}/ratings", tourId);
    return toDto(tourRatingService.update(tourId,
                                          ratingDto.getCustomerId(),
                                          ratingDto.getScore(),
                                          ratingDto.getComment()));
  }

  /**
   * Update score or comment of a Tour Rating
   *
   * @param tourId
   * @param ratingDto
   * @return The modified Rating DTO.
   */
  @PatchMapping
  @PreAuthorize("hasRole('ROLE_CSR')")
  public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId,
                                   @RequestBody @Validated RatingDto ratingDto) {
    LOGGER.info("PATCH /tours/{}/ratings", tourId);
    return toDto(tourRatingService.updateSome(tourId,
                                              ratingDto.getCustomerId(),
                                              ratingDto.getScore(),
                                              ratingDto.getComment()));
  }

  /**
   * Delete a Rating of a tour made by a customer
   *
   * @param tourId
   * @param customerId
   */
  @DeleteMapping("/{customerId}")
  @PreAuthorize("hasRole('ROLE_CSR')")
  public void delete(@PathVariable(value = "tourId") int tourId,
                     @PathVariable(value = "customerId") int customerId) {
    LOGGER.info("DELETE /tours/{}/ratings/{}", tourId, customerId);
    tourRatingService.delete(tourId, customerId);
  }

  /**
   * Convert the TourRating entity to a RatingDto
   *
   * @param tourRating
   * @return RatingDto
   */
  private RatingDto toDto(TourRating tourRating) {
    return new RatingDto(tourRating.getScore(),
                         tourRating.getComment(),
                         tourRating.getCustomerId());
  }

  /**
   * Exception handler if NoSuchElementException is thrown in this Controller
   *
   * @param ex
   * @return Error message String.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public String return400(NoSuchElementException ex) {
    LOGGER.error("Unable to complete transaction", ex);
    return ex.getMessage();
  }

}
