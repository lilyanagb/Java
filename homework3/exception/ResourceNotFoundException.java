package homework3.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(){
        super("Resource not found!");
    }
}
