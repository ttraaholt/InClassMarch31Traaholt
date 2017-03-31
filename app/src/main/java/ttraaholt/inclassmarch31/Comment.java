package ttraaholt.inclassmarch31;

/**
 * Comment class that contains the data to be saved in the database and show in the user interface.
 */

public class Comment {
    //id constant that will be used as id.
    private long id;
    //comment string that will be used and returned
    private String comment;
    //getId method that returns the id
    public long getId() {
        return id;
    }
    //setId method that sets the id.
    public void setId(long id) {
        this.id = id;
    }
    //getComment method that returns the comment.
    public String getComment() {
        return comment;
    }
    //setComment that takes the String comment and sets it.
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    //toString method that also returns the comment.
    @Override
    public String toString() {
        return comment;
    }
}

