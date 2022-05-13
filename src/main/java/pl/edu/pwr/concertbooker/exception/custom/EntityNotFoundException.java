package pl.edu.pwr.concertbooker.exception.custom;

public class EntityNotFoundException extends Exception {
    private long entityId;

    public EntityNotFoundException(long entityId) {
        super("Entity with id " + entityId + " not found");
        this.entityId = entityId;
    }
}
