package com.DrJiheeye.Dr.Jiheeye.Controller;

import com.DrJiheeye.Dr.Jiheeye.Model.AppointmentModel;
import com.DrJiheeye.Dr.Jiheeye.Model.DoctorModel;
import com.DrJiheeye.Dr.Jiheeye.Repository.AppointmentRepository;
import com.DrJiheeye.Dr.Jiheeye.Service.AppointmentService;
import com.DrJiheeye.Dr.Jiheeye.Service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
    }

    @GetMapping("/appointmentList")
    public String showAllAppointments(Model model) {
        List<AppointmentModel> appointmentList = appointmentService.getAll1();
        model.addAttribute("appointments", appointmentList);
        return "appointmentList"; // Ensure this matches your Thymeleaf template name
    }

    @GetMapping("appointmentList/appointmentForm")
    public String showCreateAppointmentForm(Model model) {
        List<DoctorModel> doctorList = doctorService.getAlldoctor(); // Fetch list of doctors
        model.addAttribute("doctors", doctorList); // Add doctors list to the model
        model.addAttribute("appointment", new AppointmentModel()); // Add an empty AppointmentModel to the model
        return "appointmentForm"; // Ensure this matches your Thymeleaf template name
    }


    @PostMapping("/appointmentNew")
    public String createAppointment(@ModelAttribute("appointment") AppointmentModel appointmentModel) {
        appointmentService.insertAppointment(appointmentModel);
        return "redirect:/appointmentList";
    }

    @GetMapping("/appointmentList/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointmentList";
    }

    @GetMapping("/appointmentList/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        AppointmentModel appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            // Handle the case where the appointment is not found
            return "redirect:/appointmentList";
        }
        model.addAttribute("appointment", appointment);
        return "appointmentEdit"; // Ensure this matches your Thymeleaf template name
    }

    @PostMapping("/appointmentList/update")
    public String updateAppointment(@ModelAttribute AppointmentModel appointment) {
        appointmentService.updateAppointment(appointment); // Ensure this method updates the appointment
        return "redirect:/appointmentList";
    }

}
