package kr.ac.kaist.sprite.hapit;

/**
 * Created by pika on 2015-12-21.
 */
public class User {
    private String userID;
    private String name;
    private String message;

    public User (String userID, String name, String message) {
        this.message = message;
        this.name = name;
        this.userID = userID;
    }

    public String getID () {
        return userID;
    }

    public String getName() {
        return name;
    }
    public String getMessage() {
        return message;
    }
}