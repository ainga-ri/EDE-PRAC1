package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import uoc.ds.pr.SportEvents4Club;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Iterator;

public class SportEvent implements Comparable<SportEvent> {

    public static final Comparator<SportEvent> CMP_V = (SportEvent a1, SportEvent a2)-> CharSequence.compare(a1.getEventId(), a2.getEventId());
    private String eventId;
    private int orgId;
    private String description;
    private SportEvents4Club.Type type;
    private int max;
    LocalDate startDate;
    LocalDate endDate;

    private LinkedList<String> players = new LinkedList<>();
    private LinkedList<String> substitutes = new LinkedList<>();
    private LinkedList<Rating> ratingLinkedList = new LinkedList<>();

    public SportEvent(String eventId, int orgId, String description, SportEvents4Club.Type type, int max, LocalDate startDate, LocalDate endDate) {
        this.eventId = eventId;
        this.orgId = orgId;
        this.description = description;
        this.type = type;
        this.max = max;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getMax() {
        return max;
    }
    public String getEventId() {
        return eventId;
    }

    public double rating() {
        double totalRating = 0;
        while (ratingLinkedList.values().hasNext()) {
            totalRating += ratingLinkedList.values().next().rating();
        }
        return totalRating / ratingLinkedList.size();
    }

    @Override
    public int compareTo(SportEvent o) {
        return 0;
    }
    public void addPlayerToEvent(String playerId) {
        this.players.insertEnd(playerId);
    }
    public void addSubstituteToEvent(String playerId) {
        this.substitutes.insertEnd(playerId);
    }
    public void addRating(Rating rate) {
        this.ratingLinkedList.insertEnd(rate);
    }
    public int getCurrentSizePlayers() {
        return players.size();
    }
    public int getCurrentSizeSubstitutes() {
        return substitutes.size();
    }
}
