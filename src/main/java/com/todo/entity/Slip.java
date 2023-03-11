package com.todo.entity;

import com.todo.context.ExecutionContext;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Slip")
@Table(name = "slip")
public class Slip extends ParentEntity {
    @Id
    private String id;

    @Column
    private String status;

    @Column
    private Long entryTimestamp;

    @Column
    private Long exitTimestamp;

    @Column
    private Integer totalRevenue;

    @Column
    private String parkingArea;

    @Column
    private String vehicleType;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEntryTimestamp() {
        return entryTimestamp;
    }

    public void setEntryTimestamp(Long entryTimestamp) {
        this.entryTimestamp = entryTimestamp;
    }

    public Long getExitTimestamp() {
        return exitTimestamp;
    }

    public void setExitTimestamp(Long exitTimestamp) {
        this.exitTimestamp = exitTimestamp;
    }

    public Integer getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Integer totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @PrePersist
    public void prePersist() {
        this.setCreatedBy((Objects.isNull(ExecutionContext.get()) || Objects.isNull(ExecutionContext.get().getUsercontext().getId())) ? "SYSTEM" : ExecutionContext.get().getUsercontext().getId());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy((Objects.isNull(ExecutionContext.get()) || Objects.isNull(ExecutionContext.get().getUsercontext().getId())) ? "SYSTEM" : ExecutionContext.get().getUsercontext().getId());
    }
}
