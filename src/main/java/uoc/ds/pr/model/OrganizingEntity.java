package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;

public class OrganizingEntity {
    private int id;
    private String name;
    private String description;

    private LinkedList<String> sportEvent = new LinkedList<>();

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

    public void addSportEvent(String sportEvent) {
        this.sportEvent.insertEnd(sportEvent);
    }
}
