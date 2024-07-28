package com.lockers.api.mapper;

import com.lockers.api.dto.LockerDTO;
import com.lockers.api.misc.LockerId;
import com.lockers.api.models.LockerModel;
import org.springframework.stereotype.Component;

@Component
public class LockerMapper {

    public LockerDTO toDTO(LockerModel lockerModel) {
        LockerDTO lockerDTO = new LockerDTO();
        lockerDTO.setNumber(lockerModel.getId().getNumber());
        lockerDTO.setSector(lockerModel.getId().getSector());
        return lockerDTO;
    }

    public LockerModel toEntity(LockerDTO lockerDTO) {
        LockerModel lockerModel = new LockerModel();
        lockerModel.setId(new LockerId(lockerDTO.getNumber(), lockerDTO.getSector()));
        return lockerModel;
    }
}
