package com.lockers.api.misc;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LockerId implements Serializable {
    private int number;
    private String sector;

    public LockerId() {
    }

    public LockerId(int number, String sector) {
        this.number = number;
        this.sector = sector;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LockerId lockerId = (LockerId) o;
        return number == lockerId.number && Objects.equals(sector, lockerId.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, sector);
    }
}
