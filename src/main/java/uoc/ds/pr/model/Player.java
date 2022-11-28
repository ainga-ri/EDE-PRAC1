package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;

import java.time.LocalDate;

public class Player {
    private String id;
    private String name;
    private String surname;

    private LocalDate dateOfBirth;

    private LinkedList<String> sportEvent;

    public Player(String id, String name, String surname, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public int numEvents() {
        return 0;
    }
    public void addSportEvent(String sportEvent) {
        this.sportEvent.insertEnd(sportEvent);
    }
}
