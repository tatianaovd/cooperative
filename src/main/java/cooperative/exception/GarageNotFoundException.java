package cooperative.exception;

public class GarageNotFoundException extends RuntimeException {

    public GarageNotFoundException(String message) {
        super(message);
    }
}
