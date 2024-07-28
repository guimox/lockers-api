package com.lockers.api.controller;

import com.lockers.api.dto.LockerDTO;
import com.lockers.api.dto.StudentDTO;
import com.lockers.api.mapper.LockerMapper;
import com.lockers.api.mapper.StudentMapper;
import com.lockers.api.models.LockerModel;
import com.lockers.api.models.StudentModel;
import com.lockers.api.service.LockerService;
import com.lockers.api.misc.LockerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private final LockerService lockerService;
    private final LockerMapper lockerMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public LockerController(LockerService lockerService, LockerMapper lockerMapper, StudentMapper studentMapper) {
        this.lockerService = lockerService;
        this.lockerMapper = lockerMapper;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public List<LockerDTO> getAllLockers() {
        return lockerService.getAllLockers().stream()
                .map(lockerMapper::toDTO)
                .toList();
    }

    @GetMapping("/{number}/{sector}")
    public ResponseEntity<LockerDTO> getLockerById(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(locker -> ResponseEntity.ok(lockerMapper.toDTO(locker)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LockerDTO> createLocker(@RequestBody LockerDTO lockerDTO) {
        LockerModel lockerModel = lockerMapper.toEntity(lockerDTO);
        LockerModel createdLocker = lockerService.createLocker(lockerModel);
        return ResponseEntity.ok(lockerMapper.toDTO(createdLocker));
    }

    @PutMapping("/{number}/{sector}")
    public ResponseEntity<LockerDTO> updateLocker(@PathVariable int number, @PathVariable String sector, @RequestBody LockerDTO lockerDTO) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(locker -> {
                    LockerModel updatedLocker = lockerMapper.toEntity(lockerDTO);
                    updatedLocker.setId(id);  // Ensure the ID is set correctly
                    return ResponseEntity.ok(lockerMapper.toDTO(lockerService.updateLocker(updatedLocker)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{number}/{sector}")
    public ResponseEntity<Void> deleteLocker(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(locker -> {
                    lockerService.deleteLocker(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{number}/{sector}/assign-student")
    public ResponseEntity<LockerDTO> assignStudentToLocker(@PathVariable int number, @PathVariable String sector, @RequestBody StudentDTO studentDTO) {
        LockerId id = new LockerId(number, sector);
        StudentModel studentModel = studentMapper.toEntity(studentDTO);
        return ResponseEntity.ok(lockerMapper.toDTO(lockerService.assignStudentToLocker(id, studentModel)));
    }

    @PostMapping("/{number}/{sector}/remove-student")
    public ResponseEntity<LockerDTO> removeStudentFromLocker(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return ResponseEntity.ok(lockerMapper.toDTO(lockerService.removeStudentFromLocker(id)));
    }
}
