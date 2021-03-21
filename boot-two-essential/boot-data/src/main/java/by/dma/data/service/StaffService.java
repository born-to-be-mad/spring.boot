package by.dma.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.dma.data.data.StaffRepository;
import by.dma.data.models.StaffMember;

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
