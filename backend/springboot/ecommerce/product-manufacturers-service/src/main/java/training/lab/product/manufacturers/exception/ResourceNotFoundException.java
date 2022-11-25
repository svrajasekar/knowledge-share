package training.lab.product.manufacturers.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
