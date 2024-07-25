package com.lockers.api.dto;

import com.lockers.api.models.LockerModel;
import com.lockers.api.misc.LockerId;

import java.time.Instant;

public class LockerDTO {

    private LockerId id;
    private Instant createdAt;
    private Instant updatedAt;
    private StudentDTO student;

    // Getters and Setters
    public LockerId getId() {
        return id;
    }

    public void setId(LockerId id) {
        this.id = id;
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

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public static LockerDTO fromEntity(LockerModel locker) {
        LockerDTO dto = new LockerDTO();
        dto.setId(locker.getId());
        dto.setCreatedAt(locker.getCreatedAt());
        dto.setUpdatedAt(locker.getUpdatedAt());
        if (locker.getStudent() != null) {
            dto.setStudent(StudentDTO.fromEntity(locker.getStudent()));
        }
        return dto;
    }

    public static LockerModel toEntity(LockerDTO dto) {
        LockerModel locker = new LockerModel();
        locker.setId(dto.getId());
        locker.setCreatedAt(dto.getCreatedAt());
        locker.setUpdatedAt(dto.getUpdatedAt());
        if (dto.getStudent() != null) {
            locker.setStudent(StudentDTO.toEntity(dto.getStudent()));
        }
        return locker;
    }
}
