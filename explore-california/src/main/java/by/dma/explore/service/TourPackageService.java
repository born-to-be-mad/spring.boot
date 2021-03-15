package by.dma.explore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dma.explore.domain.TourPackage;
import by.dma.explore.repo.TourPackageRepository;

/**
 * Tour Package Service
 *

 */
@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        return !tourPackageRepository.existsById(code) ?
                tourPackageRepository.save(new TourPackage(code, name)) :
                null;

    }
    public Iterable<TourPackage> lookup(){
        return tourPackageRepository.findAll();
    }
    public long total() {
        return tourPackageRepository.count();
    }
}

