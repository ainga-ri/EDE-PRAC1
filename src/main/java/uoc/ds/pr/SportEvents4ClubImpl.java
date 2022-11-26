package uoc.ds.pr;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.File;
import uoc.ds.pr.model.OrganizingEntity;
import uoc.ds.pr.model.Player;
import uoc.ds.pr.model.SportEvent;
import uoc.ds.pr.util.DictionaryOrderedVector;

import java.time.LocalDate;

public class SportEvents4ClubImpl implements SportEvents4Club {
    Player[] players = new Player[15];
    OrganizingEntity[] org = new OrganizingEntity[5];
    QueueArrayImpl<File> files = new QueueArrayImpl<>();
    DictionaryOrderedVector<String, String> eventsDictionaryOrderedVector = new DictionaryOrderedVector<>(11);
    LinkedList<Rating> ratingLinkedList = new LinkedList<>();
    @Override
    public void addPlayer(String id, String name, String surname, LocalDate dateOfBirth) {

        Player player = new Player(id, name, surname, dateOfBirth);
        players[Player.idNumber - 1] = player;
    }

    @Override
    public void addOrganizingEntity(int id, String name, String description) {
        OrganizingEntity newOrg = new OrganizingEntity(id, name, description);
        org[OrganizingEntity.totalId - 1] = newOrg;
    }

    @Override
    public void addFile(String id, String eventId, int orgId, String description, Type type, byte resources, int max, LocalDate startDate, LocalDate endDate) throws OrganizingEntityNotFoundException {
        File file = new File( id,  eventId,  orgId,  description,  type,  resources,  max,  startDate, endDate);
        files.add(file);
    }

    @Override
    public File updateFile(Status status, LocalDate date, String description) throws NoFilesException {
        if (files.size() == 0)
            throw new NoFilesException();
        File file = files.poll();
        file.setStatus(status);
        file.setEndDate(date);
        file.setDescription(description);
        files.add(file);
        return file;
    }

    @Override
    public void signUpEvent(String playerId, String eventId) throws PlayerNotFoundException, SportEventNotFoundException, LimitExceededException {
        int player = 0;
        boolean found = false;
        while (player < players.length && !found) {
            if (players[player].getId().equals(playerId))
                found = true;
            player++;
        }
        if (!found)
            throw new PlayerNotFoundException();
        eventsDictionaryOrderedVector.put(playerId, eventId);
    }

    @Override
    public double getRejectedFiles() {
        return 0;
    }

    @Override
    public Iterator<SportEvent> getSportEventsByOrganizingEntity(int organizationId) throws NoSportEventsException {
        if (eventsDictionaryOrderedVector.isEmpty())
            throw new NoSportEventsException();
        return null;
    }

    @Override
    public Iterator<SportEvent> getAllEvents() throws NoSportEventsException {
        if (eventsDictionaryOrderedVector.isEmpty())
            throw new NoSportEventsException();
        return null;
    }

    @Override
    public Iterator<SportEvent> getEventsByPlayer(String playerId) throws NoSportEventsException {
        return null;
    }

    @Override
    public void addRating(String playerId, String eventId, Rating rating, String message) throws SportEventNotFoundException, PlayerNotFoundException, PlayerNotInSportEventException {

    }

    @Override
    public Iterator<uoc.ds.pr.model.Rating> getRatingsByEvent(String eventId) throws SportEventNotFoundException, NoRatingsException {
        if (ratingLinkedList.isEmpty())
            throw new NoRatingsException();
        return null;
    }

    @Override
    public Player mostActivePlayer() throws PlayerNotFoundException {
        if (players[0] != null)
            // some logic
            return players[0];
        else
            throw new PlayerNotFoundException();
    }

    @Override
    public SportEvent bestSportEvent() throws SportEventNotFoundException {
        return null;
    }

    @Override
    public int numPlayers() {
        return Player.idNumber;
    }

    @Override
    public int numOrganizingEntities() {
        return OrganizingEntity.totalId;
    }

    @Override
    public int numFiles() {
        return File.numFiles;
    }

    @Override
    public int numRejectedFiles() {
        return 0;
    }

    @Override
    public int numPendingFiles() {
        int totalPendingFiles = 0;
        Iterator<File> fileIterator = files.values();
        while (fileIterator.hasNext()) {
            if (fileIterator.next().getStatus() == Status.PENDING)
                totalPendingFiles++;
        }
        return totalPendingFiles;
    }

    @Override
    public int numSportEvents() {
        return 0;
    }

    @Override
    public int numSportEventsByPlayer(String playerId) {
        int totalEvents = 0;
        Iterator<String> eventIterator = eventsDictionaryOrderedVector.keys();
        while (eventIterator.hasNext()) {
            if (eventIterator.next().equals(playerId))
                totalEvents++;
        }
        return totalEvents;
    }

    @Override
    public int numPlayersBySportEvent(String sportEventId) {
        int totalPlayers = 0;
        Iterator<String> eventIterator = eventsDictionaryOrderedVector.values();
        while (eventIterator.hasNext()) {
            if (eventIterator.next().equals(sportEventId))
                totalPlayers++;
        }
        return totalPlayers;
    }

    @Override
    public int numSportEventsByOrganizingEntity(int orgId) {
        return 0;
    }

    @Override
    public int numSubstitutesBySportEvent(String sportEventId) {
        return 0;
    }

    @Override
    public Player getPlayer(String playerId) {
        if (players.length > 0) {
            int i = 0;
            boolean found = false;
            while (i < players.length && !found) {
                if (players[i].getId().equals(playerId))
                    found = true;
                i++;
            }
            return (players[i - 1]);
        } else
            return null;
    }

    @Override
    public SportEvent getSportEvent(String eventId) {
        return null;
    }

    @Override
    public OrganizingEntity getOrganizingEntity(int id) {
        return null;
    }

    @Override
    public File currentFile() {
        return null;
    }
}
