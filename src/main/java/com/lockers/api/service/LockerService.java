package com.lockers.api.service;

import com.lockers.api.models.LockerModel;
import com.lockers.api.misc.LockerId;
import com.lockers.api.models.StudentModel;
import com.lockers.api.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LockerService {

    private LockerRepository lockerRepository;

    @Autowired
    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public List<LockerModel> getAllLockers() {
        return lockerRepository.findAll();
    }

    public Optional<LockerModel> getLockerById(LockerId id) {
        return lockerRepository.findById(id);
    }

    public LockerModel createLocker(LockerModel locker) {
        return lockerRepository.save(locker);
    }

    public LockerModel updateLocker(LockerModel locker) {
        return lockerRepository.save(locker);
    }

    public void deleteLocker(LockerId id) {
        lockerRepository.deleteById(id);
    }

    public LockerModel assignStudentToLocker(LockerId id, StudentModel student) {
        LockerModel locker = lockerRepository.findById(id).orElseThrow(() -> new RuntimeException("Locker not found"));
        locker.setStudent(student);
        return lockerRepository.save(locker);
    }

    public LockerModel removeStudentFromLocker(LockerId id) {
        LockerModel locker = lockerRepository.findById(id).orElseThrow(() -> new RuntimeException("Locker not found"));
        locker.setStudent(null);
        return lockerRepository.save(locker);
    }
}
