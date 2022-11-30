package uoc.ds.pr;

import edu.uoc.ds.adt.nonlinear.Dictionary;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.DictionaryOrderedVector;

import java.time.LocalDate;

public class SportEvents4ClubImpl implements SportEvents4Club {
    private Player[] players = new Player[15];
    int totalPlayers = 0;
    private OrganizingEntity[] org = new OrganizingEntity[8];
    int totalOrgEntity = 0;
    private QueueArrayImpl<File> files = new QueueArrayImpl<>();
    int totalNumFiles = 0;
    private Dictionary sportsEvent = new DictionaryOrderedVector<Integer, SportEvent>(MAX_NUM_SPORT_EVENTS, SportEvent.CMP_V);
    //private LinkedList<Rating> ratingLinkedList = new LinkedList<>();
    @Override
    public void addPlayer(String id, String name, String surname, LocalDate dateOfBirth) {
        int i = 0;
        boolean found = false;
        while (i < totalPlayers && !found) {
            if (players[i].getId().equals(id)) {
                players[i] = new Player(id, name, surname, dateOfBirth);
                found = true; // I think it is not the right way
            }
            i++;
        }
        if (!found) {
            Player player = new Player(id, name, surname, dateOfBirth);
            players[totalPlayers] = player;
            totalPlayers++;
        }
    }

    @Override
    public void addOrganizingEntity(int id, String name, String description) {
        int i = 0;
        boolean found = false;
        while (i < totalOrgEntity && !found) {
            if (org[i].getId() == (id)) {
                org[i] = new OrganizingEntity(id, name, description);
                found = true; // I think it is not the right way
            }
            i++;
        }
        if (!found) {
            OrganizingEntity newOrg = new OrganizingEntity(id, name, description);
            org[totalOrgEntity] = newOrg;
            totalOrgEntity++;
        }
    }

    @Override
    public void addFile(String id, String eventId, int orgId, String description, Type type, byte resources, int max, LocalDate startDate, LocalDate endDate) throws OrganizingEntityNotFoundException {
        int i = 0;
        boolean found = false;
        while (i < totalOrgEntity && !found) {
            if (org[i].getId() == orgId) {
                org[i].addSportEvent(eventId, orgId, description, type, max, startDate, endDate);
                found = true;
            }
            i++;
        }
        if (found) {
            File file = new File(id, eventId, orgId, description, type, resources, max, startDate, endDate);
            files.add(file);
            totalNumFiles++;
            SportEvent sportEvent = new SportEvent(eventId, orgId, description, type, max, startDate, endDate);
            sportsEvent.put(max, sportEvent);
        } else
            throw new OrganizingEntityNotFoundException();
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
        while (player < totalPlayers && !found) {
            if (players[player].getId().equals(playerId)) {
                players[player].addSportEvent(getSportEvent(eventId));
                SportEvent sportEvent = getSportEvent(eventId);
                if (sportEvent == null)
                    throw new SportEventNotFoundException();
                if (sportEvent.getMax() <= sportEvent.getCurrentSizePlayers()) {
                    sportEvent.addSubstituteToEvent(playerId);
                    sportEvent.addPlayerToEvent(playerId);
                    throw new LimitExceededException();
                }

                sportEvent.addPlayerToEvent(playerId);
                found = true;
            }
            player++;
        }
        if (!found)
            throw new PlayerNotFoundException();

    }

    @Override
    public double getRejectedFiles() {
        return (double) numRejectedFiles() / numFiles();
    }

    @Override
    public Iterator<SportEvent> getSportEventsByOrganizingEntity(int organizationId) throws NoSportEventsException {
        if (sportsEvent.isEmpty())
            throw new NoSportEventsException();
        int i = 0;
        boolean found = false;
        while (i < totalOrgEntity && !found) {
            if (org[i].getId() == organizationId) {
                found = true;
            }
            i++;
        }
        if (found) {
            Iterator<SportEvent> sportEventIterator = org[i - 1].getSportEvent().values();
            return sportEventIterator;
        } else
            throw new NoSportEventsException();
    }

    @Override
    public Iterator<SportEvent> getAllEvents() throws NoSportEventsException {
        if (sportsEvent.isEmpty())
            throw new NoSportEventsException();
        Iterator<SportEvent> it= sportsEvent.values();
        Dictionary activeSportEvents = new DictionaryOrderedVector<Integer, SportEvent>(MAX_NUM_SPORT_EVENTS, SportEvent.CMP_V);
        while (it.hasNext()) {
            SportEvent se = it.next();
            if (numPlayersBySportEvent(se.getEventId()) > 0) {
                activeSportEvents.put(se.getMax(), se);
            }
        }
        return activeSportEvents.values();
    }

    @Override
    public Iterator<SportEvent> getEventsByPlayer(String playerId) throws NoSportEventsException {
        int i = 0;
        while (i < totalPlayers) {
            if (players[i].getId().equals(playerId)) {
                if (players[i].getSportEvent().size() > 0)
                    return players[i].getSportEvent().values();
                else
                    throw new NoSportEventsException();
            }
            i++;
        }
        return null;
    }

    @Override
    public void addRating(String playerId, String eventId, Rating rating, String message) throws SportEventNotFoundException, PlayerNotFoundException, PlayerNotInSportEventException {
        if (getSportEvent(eventId) == null)
            throw new SportEventNotFoundException();
        if (getPlayer(playerId) == null)
            throw new PlayerNotFoundException();
        try {
             getEventsByPlayer(playerId);
        } catch (NoSportEventsException e) {
            throw new PlayerNotInSportEventException();
        }
        uoc.ds.pr.model.Rating rate = new uoc.ds.pr.model.Rating(getPlayer(playerId), eventId, rating, message);
        SportEvent eve = getSportEvent(eventId);
        eve.addRating(rate);
    }

    @Override
    public Iterator<uoc.ds.pr.model.Rating> getRatingsByEvent(String eventId) throws SportEventNotFoundException, NoRatingsException {
        if (getSportEvent(eventId).getRatingLinkedList().isEmpty())
            throw new NoRatingsException();
        return getSportEvent(eventId).getRatingLinkedList().values();
    }

    @Override
    public Player mostActivePlayer() throws PlayerNotFoundException {
        if (players[0] != null) {
            Player mostActivePlayer = players[0];
            for (int i = 0; i < totalPlayers; i++) {
                if (players[i].getSportEvent().size() > mostActivePlayer.getSportEvent().size())
                    mostActivePlayer = players[i];
            }
            return mostActivePlayer;
        }
        else
            throw new PlayerNotFoundException();
    }

    @Override
    public SportEvent bestSportEvent() throws SportEventNotFoundException {
        if (sportsEvent.isEmpty())
            throw new SportEventNotFoundException();
        Iterator<SportEvent> es =  sportsEvent.values();
        SportEvent bestEvent = es.next();
        double bestSportEventRating = bestEvent.rating();
        while (es.hasNext()) {
            SportEvent possBestEvent = es.next();
            double nextRating = possBestEvent.rating();
            if (bestSportEventRating < nextRating) {
                bestEvent = possBestEvent;
                bestSportEventRating = nextRating;
            }
        }
        return bestEvent;
    }

    @Override
    public int numPlayers() {
        return totalPlayers;
    }

    @Override
    public int numOrganizingEntities() {
        return totalOrgEntity;
    }

    @Override
    public int numFiles() {
        return totalNumFiles;
    }

    @Override
    public int numRejectedFiles() {
        int totalPendingFiles = 0;
        Iterator<File> fileIterator = files.values();
        while (fileIterator.hasNext()) {
            if (fileIterator.next().getStatus() == Status.DISABLED)
                totalPendingFiles++;
        }
        return totalPendingFiles;
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
        // TODO
        //  FIND ANOTHER EXAMPLE TO UNDERSTAND WHAT THIS MEANS
        return 4;
    }

    @Override
    public int numSportEventsByPlayer(String playerId) {
        int i = 0;
        while (i < totalPlayers) {
            if (players[i].getId().equals(playerId))
                return players[i].numEvents();
            i++;
        }
        return 0;
    }

    @Override
    public int numPlayersBySportEvent(String sportEventId) {
        SportEvent sportEvent = getSportEvent(sportEventId);
        return sportEvent.getCurrentSizePlayers();
    }

    @Override
    public int numSportEventsByOrganizingEntity(int orgId) {
        for (int i = 0; i < totalOrgEntity; i++) {
            if (org[i].getId() == orgId)
                return org[i].getNumSportEvents();
        }
        return 0;
    }

    @Override
    public int numSubstitutesBySportEvent(String sportEventId) {
        SportEvent sportEvent = getSportEvent(sportEventId);
        return sportEvent.getCurrentSizeSubstitutes();
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
        SportEvent se;
        Iterator<SportEvent> sportEventIterator = sportsEvent.values();
        while (sportEventIterator.hasNext()) {
            se = sportEventIterator.next();
            if (se.getEventId().equals(eventId)) {
                return se;
            }
        }
        return null;
    }

    @Override
    public OrganizingEntity getOrganizingEntity(int id) {
        int i = 0;
        while (i < totalOrgEntity) {
            if (org[i].getId() == (id)) {
                return org[i];
            }
            i++;
        }
        return null;
    }

    @Override
    public File currentFile() {
        return files.peek();
    }
}
