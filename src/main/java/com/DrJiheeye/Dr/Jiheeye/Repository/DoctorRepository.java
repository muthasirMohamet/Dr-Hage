package com.DrJiheeye.Dr.Jiheeye.Repository;

import com.DrJiheeye.Dr.Jiheeye.Model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel,Long> {
}
