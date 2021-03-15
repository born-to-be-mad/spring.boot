package by.dma.explore.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import by.dma.explore.domain.TourRating;

/**
 * Tour Rating Repository Interface
 */
@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating, Integer> {

  /**
   * Lookup all the TourRatings for a tour.
   *
   * @param tourId is the tour Identifier
   * @return a List of any found TourRatings
   */
  List<TourRating> findByTourId(Integer tourId);

  /**
   * Lookup a page of TourRatings for a tour.
   *
   * @param tourId   tourId is the tour Identifier
   * @param pageable details for the desired page
   * @return a Page of any found TourRatings
   */
  Page<TourRating> findByTourId(Integer tourId, Pageable pageable);

  /**
   * Lookup a TourRating by the TourId and Customer Id
   *
   * @param tourId
   * @param customerId
   * @return TourRating if found, null otherwise.
   */
  Optional<TourRating> findByTourIdAndCustomerId(Integer tourId,
                                                 Integer customerId);
}
