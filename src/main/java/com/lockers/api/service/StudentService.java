package com.lockers.api.service;

import com.lockers.api.dto.StudentDTO;
import com.lockers.api.models.StudentModel;
import com.lockers.api.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<StudentModel> findAll() {
        return studentRepository.findAll();
    }

    public Optional<StudentModel> findById(String grr) {
        return studentRepository.findById(grr);
    }

    public StudentModel createStudent(StudentDTO studentDTO) {
        StudentModel student = new StudentModel();
        student.setGrr(studentDTO.getGrr());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        student.setDeleted(studentDTO.isDeleted());

        return studentRepository.save(student);
    }

    public StudentModel update(StudentModel student) {
        return studentRepository.save(student);
    }

    public void deleteById(String grr) {
        studentRepository.deleteById(grr);
    }
}
