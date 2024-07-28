package com.lockers.api.mapper;

import com.lockers.api.dto.StudentDTO;
import com.lockers.api.models.StudentModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final LockerMapper lockerMapper;

    public StudentMapper(LockerMapper lockerMapper) {
        this.lockerMapper = lockerMapper;
    }

    public StudentDTO toDto(StudentModel studentModel) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setGrr(studentModel.getGrr());
        studentDTO.setFirstName(studentModel.getFirstName());
        studentDTO.setLastName(studentModel.getLastName());
        studentDTO.setEmail(studentModel.getEmail());
        studentDTO.setPassword(studentModel.getPassword());
        studentDTO.setCreatedAt(studentModel.getCreatedAt());
        studentDTO.setUpdatedAt(studentModel.getUpdatedAt());
        studentDTO.setDeleted(studentModel.isDeleted());
//        studentDTO.setLockers(studentModel.getLockers().stream()
//                .map(lockerMapper::toDTO)
//                .collect(Collectors.toList()));
        return studentDTO;
    }

    public StudentModel toEntity(StudentDTO studentDTO) {
        StudentModel studentModel = new StudentModel();
        studentModel.setGrr(studentDTO.getGrr());
        studentModel.setFirstName(studentDTO.getFirstName());
        studentModel.setLastName(studentDTO.getLastName());
        studentModel.setEmail(studentDTO.getEmail());
        studentModel.setPassword(studentDTO.getPassword());
        studentModel.setDeleted(studentDTO.isDeleted());
//        studentModel.setLockers(studentDTO.getLockers().stream()
//                .map(lockerMapper::toEntity)
//                .collect(Collectors.toList()));
        return studentModel;
    }
}
