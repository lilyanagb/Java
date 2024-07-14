package homework3.exception;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(){
        super("Insufficient balance!");
    }
}
