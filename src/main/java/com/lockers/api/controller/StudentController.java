package com.lockers.api.controller;

import com.lockers.api.dto.StudentDTO;
import com.lockers.api.mapper.StudentMapper;
import com.lockers.api.models.StudentModel;
import com.lockers.api.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{grr}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String grr) {
        Optional<StudentDTO> studentOptional = studentService.findById(grr).map(studentMapper::toDto);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentDTO student) {
        StudentModel studentModel = studentService.createStudent(student);
        return ResponseEntity.ok(studentModel);
    }

    @PutMapping("/{grr}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String grr, @RequestBody StudentDTO student) {
        Optional<StudentDTO> studentOptional = studentService.findById(grr).map(studentMapper::toDto);
        if (studentOptional.isPresent()) {
            student.setGrr(grr);
            StudentDTO updatedStudent = studentMapper.toDto(studentService.update(studentMapper.toEntity(student)));
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{grr}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String grr) {
        Optional<StudentDTO> studentOptional = studentService.findById(grr).map(studentMapper::toDto);
        if (studentOptional.isPresent()) {
            studentService.deleteById(grr);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
