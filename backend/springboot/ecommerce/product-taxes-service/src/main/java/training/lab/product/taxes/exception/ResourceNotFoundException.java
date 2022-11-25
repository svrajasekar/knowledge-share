package training.lab.product.taxes.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
