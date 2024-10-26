package com.DrJiheeye.Dr.Jiheeye.Controller;

import com.DrJiheeye.Dr.Jiheeye.Model.AppointmentModel;
import com.DrJiheeye.Dr.Jiheeye.Model.DoctorModel;
import com.DrJiheeye.Dr.Jiheeye.Repository.AppointmentRepository;
import com.DrJiheeye.Dr.Jiheeye.Repository.DoctorRepository;
import com.DrJiheeye.Dr.Jiheeye.Service.AppointmentService;
import com.DrJiheeye.Dr.Jiheeye.Service.DoctorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {
    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    public DashboardController(DoctorService doctorService, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String home(Model model) {
        List<DoctorModel> doctorm = doctorService.getAlldoctor();
        Long doctorCount = doctorRepository.count();
        model.addAttribute("doctor", doctorm);
        model.addAttribute("doctorCount", doctorCount);
        Long appointmentCount = appointmentRepository.count();
        model.addAttribute("appointmentCounts", appointmentCount);
        List<AppointmentModel> appointmentList = appointmentService.getAll1();
        model.addAttribute("appointments", appointmentList);
        return "index";
    }


    @GetMapping("/doctorList")
    public String doctorlists(Model model){
        List<DoctorModel> doctorm = doctorService.getAlldoctor();
        model.addAttribute("doctor",doctorm);
        return "doctorList";
    }

    @GetMapping("/doctorList/doctorNew")
    public String doctorAdd(Model model){
        model.addAttribute("doctor", new DoctorModel());
        return "doctorM";
    }
    @PostMapping("/doctorNew")
    public String NewDoctor(@ModelAttribute("student") DoctorModel doctorModel){
        doctorService.insertDoctor(doctorModel);
        return "redirect:/doctorList";
    }
    // Method to handle deleting a doctor
    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctorList";
    }

    // Method to handle editing a doctor (You will need to implement this)
    @GetMapping("/doctor/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {
        DoctorModel doctor = doctorRepository.findById(id).orElse(null);
        model.addAttribute("doctor", doctor);
        return "doctorEdit"; // Return the edit form page
    }

    @PostMapping("/doctor/edit/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute DoctorModel updatedDoctor) {
        DoctorModel doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            doctor.setName(updatedDoctor.getName());
            doctor.setPhone(updatedDoctor.getPhone());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setSpecification(updatedDoctor.getSpecification());
            doctor.setRole(updatedDoctor.getRole());
            doctorRepository.save(doctor);
        }
        return "redirect:/doctorList";
    }


}
