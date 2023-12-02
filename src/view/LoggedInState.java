package view;

public class LoggedInState {
    private String username = "";

    private String userID = "";



    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        userID = copy.userID;

    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
