package com.DrJiheeye.Dr.Jiheeye.Repository;

import com.DrJiheeye.Dr.Jiheeye.Model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel,Long> {
}
