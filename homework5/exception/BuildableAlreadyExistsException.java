package homework5.exception;

public class BuildableAlreadyExistsException extends Exception {
    public BuildableAlreadyExistsException(){
        super("Buildable already exists!");
    }
}
