package uoc.ds.pr.model;

import java.time.LocalDate;

public class Player {
    public static int idNumber = 0;
    private String id;
    private String name;
    private String surname;

    private LocalDate dateOfBirth;

    public Player(String id, String name, String surname, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        idNumber++;
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
}
