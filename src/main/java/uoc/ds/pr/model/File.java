package uoc.ds.pr.model;

import uoc.ds.pr.SportEvents4Club;
import uoc.ds.pr.SportEvents4ClubImpl;

import java.time.LocalDate;

public class File {
    String id;
    String eventId;
    int orgId;
    String description;
    SportEvents4Club.Type type;
    SportEvents4Club.Status status;
    byte resources;
    int max;
    LocalDate startDate;
    LocalDate endDate;

    public File(String id, String eventId, int orgId, String description, SportEvents4Club.Type type, byte resources, int max, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.eventId = eventId;
        this.orgId = orgId;
        this.description = description;
        this.type = type;
        this.status = SportEvents4Club.Status.PENDING;
        this.resources = resources;
        this.max = max;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getFileId() {
        return this.id;
    }

    public SportEvents4Club.Status getStatus() {
        return this.status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportEvents4Club.Type getType() {
        return type;
    }

    public void setType(SportEvents4Club.Type type) {
        this.type = type;
    }

    public void setStatus(SportEvents4Club.Status status) {
        this.status = status;
    }

    public byte getResources() {
        return resources;
    }

    public void setResources(byte resources) {
        this.resources = resources;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
