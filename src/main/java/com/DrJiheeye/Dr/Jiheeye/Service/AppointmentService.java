package com.DrJiheeye.Dr.Jiheeye.Service;

import com.DrJiheeye.Dr.Jiheeye.Model.AppointmentModel;
import com.DrJiheeye.Dr.Jiheeye.Model.DoctorModel;
import com.DrJiheeye.Dr.Jiheeye.Repository.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Page<AppointmentModel> getAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    public List<AppointmentModel> getAll1() {
        return appointmentRepository.findAll();
    }

    // Insert a new doctor
    public AppointmentModel insertAppointment(AppointmentModel appointmentModel) {
        return appointmentRepository.save(appointmentModel); // Save and return the inserted doctor
    }

    // Update an existing doctor
    public void updateAppointment(AppointmentModel appointment) {
        if (appointmentRepository.existsById(appointment.getId())) {
            appointmentRepository.save(appointment); // This should update the existing record
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }


    // Delete a doctor by ID
    public void deleteDoctor(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Find a doctor by ID
    public AppointmentModel findDoctor(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }
}
