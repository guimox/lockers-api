package com.lockers.api.repository;

import com.lockers.api.misc.LockerId;
import com.lockers.api.models.LockerModel;
import com.lockers.api.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentModel, String> {

}
