package pl.edu.pwr.concertbooker.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {
    private long entityId;


    public EntityNotFoundException(long entityId) {
        super("Entity with id " + entityId + " not found");

        this.entityId = entityId;
    }
}
