package com.uam.model;

public class Request {
    private int id;
    private int userId;
    private int softwareId;
    private String accessType;
    private String reason;
    private String status;

    // Constructors
    public Request(int id, int userId, int softwareId, String accessType, String reason, String status) {
        this.id = id;
        this.userId = userId;
        this.softwareId = softwareId;
        this.accessType = accessType;
        this.reason = reason;
        this.status = status;
    }

    public Request(int userId, int softwareId, String accessType, String reason) {
        this.userId = userId;
        this.softwareId = softwareId;
        this.accessType = accessType;
        this.reason = reason;
        this.status = "Pending";
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
