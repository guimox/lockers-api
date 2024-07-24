package com.lockers.api.controller;


import com.lockers.api.models.LockerModel;
import com.lockers.api.models.StudentModel;
import com.lockers.api.misc.LockerId;
import com.lockers.api.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private LockerService lockerService;

    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping
    public List<LockerModel> getAllLockers() {
        return lockerService.getAllLockers();
    }

    @GetMapping("/{number}/{sector}")
    public ResponseEntity<LockerModel> getLockerById(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LockerModel createLocker(@RequestBody LockerModel locker) {
        return lockerService.createLocker(locker);
    }

    @PutMapping("/{number}/{sector}")
    public ResponseEntity<LockerModel> updateLocker(@PathVariable int number, @PathVariable String sector, @RequestBody LockerModel lockerDetails) {
        LockerId id = new LockerId(number, sector);
        return lockerService.getLockerById(id)
                .map(locker -> {
                    locker.setStudent(lockerDetails.getStudent());
                    locker.setSector(lockerDetails.getSector());
                    locker.setNumber(lockerDetails.getNumber());
                    locker.setUpdatedAt(lockerDetails.getUpdatedAt());
                    return ResponseEntity.ok(lockerService.updateLocker(locker));
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
    public ResponseEntity<LockerModel> assignStudentToLocker(@PathVariable int number, @PathVariable String sector, @RequestBody StudentModel student) {
        LockerId id = new LockerId(number, sector);
        return ResponseEntity.ok(lockerService.assignStudentToLocker(id, student));
    }

    @PostMapping("/{number}/{sector}/remove-student")
    public ResponseEntity<LockerModel> removeStudentFromLocker(@PathVariable int number, @PathVariable String sector) {
        LockerId id = new LockerId(number, sector);
        return ResponseEntity.ok(lockerService.removeStudentFromLocker(id));
    }
}
