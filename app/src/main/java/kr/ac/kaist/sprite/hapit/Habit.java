package kr.ac.kaist.sprite.hapit;

/**
 * Created by pika on 2015-12-21.
 */
public class Habit {
    private String name;
    private String title;

    public Habit (String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
//
//    public boolean isToday() {
//        return true;
//    }

}