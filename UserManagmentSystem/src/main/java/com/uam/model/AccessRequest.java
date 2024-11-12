package com.uam.model;

public class AccessRequest {
    private int id;
    private User user;         // User requesting access (Employee)
    private Software software; // Software that the user is requesting access to
    private String accessType; // Type of access (e.g., Read, Write, Admin)
    private String reason;     // Reason for requesting access
    private String status;     // Status of the request (Pending, Approved, Rejected)

    // Constructor
    public AccessRequest(int id, User user, Software software, String accessType, String reason, String status) {
        this.id = id;
        this.user = user;
        this.software = software;
        this.accessType = accessType;
        this.reason = reason;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
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

    // Override toString for easy display of AccessRequest details
    @Override
    public String toString() {
        return "AccessRequest [id=" + id + ", user=" + user.getUsername() + ", software=" + software.getName() + 
               ", accessType=" + accessType + ", reason=" + reason + ", status=" + status + "]";
    }
}
