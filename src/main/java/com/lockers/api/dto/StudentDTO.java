package com.lockers.api.dto;

import com.lockers.api.models.StudentModel;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO {

    private String grr;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean isDeleted;
    private List<LockerDTO> lockers;

    public String getGrr() {
        return grr;
    }

    public void setGrr(String grr) {
        this.grr = grr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<LockerDTO> getLockers() {
        return lockers;
    }

    public void setLockers(List<LockerDTO> lockers) {
        this.lockers = lockers;
    }

    public static StudentDTO fromEntity(StudentModel student) {
        StudentDTO dto = new StudentDTO();
        dto.setGrr(student.getGrr());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());
        dto.setDeleted(student.isDeleted());
        if (student.getLockers() != null) {
            dto.setLockers(student.getLockers().stream()
                    .map(LockerDTO::fromEntity)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static StudentModel toEntity(StudentDTO dto) {
        StudentModel student = new StudentModel();
        student.setGrr(dto.getGrr());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDeleted(dto.isDeleted());
        return student;
    }
}
