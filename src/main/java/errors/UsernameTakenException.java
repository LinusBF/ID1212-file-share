package errors;

public class UsernameTakenException extends Exception {
    UsernameTakenException(){
        super("This username is already taken");
    }
}
