package fr.diginamic.aqiprojectbackend.exception;

public class IdUserNotFound extends RuntimeException{
    public IdUserNotFound(){
    }
    public IdUserNotFound(String message) {
        super(message);
    }

    public IdUserNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public IdUserNotFound(Throwable cause) {
        super(cause);
    }
}
