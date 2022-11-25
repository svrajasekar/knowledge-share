package training.lab.frontend.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
