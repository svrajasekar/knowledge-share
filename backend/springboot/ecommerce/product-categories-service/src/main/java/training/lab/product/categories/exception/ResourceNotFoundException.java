package training.lab.product.categories.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
