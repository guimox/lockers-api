package com.lockers.api.controller;

import com.lockers.api.dto.LockerDTO;
import com.lockers.api.dto.StudentDTO;
import com.lockers.api.misc.LockerId;
import com.lockers.api.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private final LockerService lockerService;

    @Autowired
    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping
    public List<LockerDTO> getAllLockers() {
        return lockerService.getAllLockers().stream()
                .map(LockerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{number}/{sector}")
    public ResponseEntity<LockerDTO> getLockerById(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(locker -> ResponseEntity.ok(LockerDTO.fromEntity(locker)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LockerDTO createLocker(@RequestBody LockerDTO lockerDTO) {
        return LockerDTO.fromEntity(lockerService.createLocker(LockerDTO.toEntity(lockerDTO)));
    }

    @PutMapping("/{number}/{sector}")
    public ResponseEntity<LockerDTO> updateLocker(@PathVariable int number, @PathVariable String sector, @RequestBody LockerDTO lockerDetailsDTO) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(locker -> {
                    locker.setStudent(StudentDTO.toEntity(lockerDetailsDTO.getStudent()));
                    locker.setUpdatedAt(lockerDetailsDTO.getUpdatedAt());
                    return ResponseEntity.ok(LockerDTO.fromEntity(lockerService.updateLocker(locker)));
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
        return ResponseEntity.ok(LockerDTO.fromEntity(lockerService.assignStudentToLocker(id, StudentDTO.toEntity(studentDTO))));
    }

    @PostMapping("/{number}/{sector}/remove-student")
    public ResponseEntity<LockerDTO> removeStudentFromLocker(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return ResponseEntity.ok(LockerDTO.fromEntity(lockerService.removeStudentFromLocker(id)));
    }
}
