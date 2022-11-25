package uoc.ds.pr.model;

public class OrganizingEntity {
    public static int totalId = 0;

    private int id;
    private String name;
    private String description;

    public OrganizingEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        totalId++;
    }

    public String getName() {
        return name;
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
}
