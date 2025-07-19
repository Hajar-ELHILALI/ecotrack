package EcoTrack.server.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException() {
        super("Not found");
    }
}

