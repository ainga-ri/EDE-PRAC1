package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.SportEvents4Club;

import java.time.LocalDate;

public class OrganizingEntity {
    private int id;
    private String name;
    private String description;

    private LinkedList<SportEvent> sportEvent = new LinkedList<>();

    public OrganizingEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addSportEvent(String eventId, int orgId, String description, SportEvents4Club.Type type, int max, LocalDate startDate, LocalDate endDate) {
        SportEvent sportEvent = new SportEvent(eventId, orgId, description, type, max, startDate, endDate);
        this.sportEvent.insertEnd(sportEvent);
    }
    public int getNumSportEvents() {
        return sportEvent.size();
    }
    public LinkedList<SportEvent> getSportEvent() {
        return sportEvent;
    }
}
