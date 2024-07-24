package com.lockers.api.service;

import com.lockers.api.models.StudentModel;
import com.lockers.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> findAll() {
        return studentRepository.findAll();
    }

    public Optional<StudentModel> findById(String grr) {
        return studentRepository.findById(grr);
    }

    public StudentModel save(StudentModel student) {
        return studentRepository.save(student);
    }

    public StudentModel update(StudentModel student) {
        return studentRepository.save(student);
    }

    public void deleteById(String grr) {
        Optional<StudentModel> studentOptional = studentRepository.findById(grr);
        if (studentOptional.isPresent()) {
            StudentModel student = studentOptional.get();
            student.setDeleted(true);
            studentRepository.save(student);
        }
    }
}
