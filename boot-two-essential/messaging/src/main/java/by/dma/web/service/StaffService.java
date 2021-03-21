package by.dma.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.dma.web.data.StaffRepository;
import by.dma.web.models.StaffMember;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<StaffMember> getAllStaff(){
        return staffRepository.findAll();
    }
}
