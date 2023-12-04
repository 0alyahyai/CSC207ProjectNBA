package view;

public class LoggedInState {
    private String username = "";

    private String userID = "";

    private boolean loggedIn = true;



    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        userID = copy.userID;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public boolean isLoggedIn() {
        return loggedIn;
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
