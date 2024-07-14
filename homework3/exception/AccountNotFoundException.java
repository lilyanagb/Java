package homework3.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(){
        super("Account not found!");
    }
}
