package com.lockers.api.controller;

import com.lockers.api.dto.StudentDTO;
import com.lockers.api.service.StudentService;
import com.lockers.api.models.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll().stream()
                .map(StudentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{grr}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String grr) {
        Optional<StudentModel> studentOptional = studentService.findById(grr);
        return studentOptional
                .map(student -> ResponseEntity.ok(StudentDTO.fromEntity(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentModel createdStudent = studentService.save(StudentDTO.toEntity(studentDTO));
        return ResponseEntity.ok(StudentDTO.fromEntity(createdStudent));
    }

    @PutMapping("/{grr}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String grr, @RequestBody StudentDTO studentDTO) {
        Optional<StudentModel> studentOptional = studentService.findById(grr);
        if (studentOptional.isPresent()) {
            StudentModel studentModel = StudentDTO.toEntity(studentDTO);
            studentModel.setGrr(grr);
            StudentModel updatedStudent = studentService.update(studentModel);
            return ResponseEntity.ok(StudentDTO.fromEntity(updatedStudent));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{grr}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String grr) {
        Optional<StudentModel> studentOptional = studentService.findById(grr);
        if (studentOptional.isPresent()) {
            studentService.deleteById(grr);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
