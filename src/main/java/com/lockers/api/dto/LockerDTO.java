package com.lockers.api.dto;

import java.time.Instant;

public class LockerDTO {

    private int number;
    private String sector;
    private String studentGRR;

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

    public String getStudentGRR() {
        return studentGRR;
    }

    public void setStudentGRR(String studentGRR) {
        this.studentGRR = studentGRR;
    }
}
