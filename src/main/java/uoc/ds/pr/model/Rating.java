package uoc.ds.pr.model;

import uoc.ds.pr.SportEvents4Club;

public class Rating {
    private String playerId;
    private String eventId;
    private SportEvents4Club.Rating rating;
    private String message;

    public Rating(String playerId, String eventId, SportEvents4Club.Rating rating, String message) {
        this.playerId = playerId;
        this.eventId = eventId;
        this.rating = rating;
        this.message = message;
    }
    public SportEvents4Club.Rating rating() {
        return this.rating;
    }

    public Player getPlayer() {
        return null;
    }
}
