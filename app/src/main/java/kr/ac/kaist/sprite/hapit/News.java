package kr.ac.kaist.sprite.hapit;

/**
 * Created by pika on 2015-12-20.
 */
public class News {
    private String userID;
    private String name;
    private String content;
    private int action;

    public News (String userID, String name, String content, int action) {
        this.userID=userID;
        this.name=name;
        this.content=content;
        this.action=action;
    }

    public String getID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getAction() {
        return action;
    }
}