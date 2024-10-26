package com.DrJiheeye.Dr.Jiheeye.Service;

import com.DrJiheeye.Dr.Jiheeye.Model.DoctorModel;
import com.DrJiheeye.Dr.Jiheeye.Repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Fetch all doctors as a list
    public List<DoctorModel> getAlldoctor() {
        return doctorRepository.findAll();
    }

    // Fetch all doctors with pagination
    public Page<DoctorModel> getAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    // Insert a new doctor
    public DoctorModel insertDoctor(DoctorModel doctorModel) {
        return doctorRepository.save(doctorModel); // Save and return the inserted doctor
    }

    // Update an existing doctor
    public DoctorModel updateDoctor(DoctorModel doctorModel) {
        return doctorRepository.save(doctorModel); // Save and return the updated doctor
    }

    // Delete a doctor by ID
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Find a doctor by ID
    public DoctorModel findDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }
}
