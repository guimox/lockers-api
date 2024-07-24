package com.lockers.api.controller;

import com.lockers.api.models.StudentModel;
import com.lockers.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentModel> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{grr}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable String grr) {
        Optional<StudentModel> studentOptional = studentService.findById(grr);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel student) {
        StudentModel createdStudent = studentService.save(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{grr}")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable String grr, @RequestBody StudentModel student) {
        Optional<StudentModel> studentOptional = studentService.findById(grr);
        if (studentOptional.isPresent()) {
            student.setGrr(grr);
            StudentModel updatedStudent = studentService.update(student);
            return ResponseEntity.ok(updatedStudent);
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
