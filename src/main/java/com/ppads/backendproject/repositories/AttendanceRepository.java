package com.ppads.backendproject.repositories;

import com.ppads.backendproject.models.Attendance;
import com.ppads.backendproject.models.pk.AttendancePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendancePK> {
}
