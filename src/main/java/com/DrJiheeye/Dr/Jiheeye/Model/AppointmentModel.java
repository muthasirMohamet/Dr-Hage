package com.DrJiheeye.Dr.Jiheeye.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "AppointmentTble")
public class AppointmentModel {
    //    Personal Details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String sex;
    private String dob;
    private String phone;
    private String email;
    private String address;
    //    Appointment Details
    private String date;
    private String time;
    private String appointmentdescribtion;
    private String status;
    private String assigndoctor;
}