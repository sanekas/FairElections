package edu.infosec.fairelections.controllers.model;

/**
 * Model for common error displaying.
 */
public class ClientError {
    public static String ALIAS = "clientError";

    private String title;
    private String message;

    public ClientError(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
