package use_case.login;

public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;

    private final String userID;

    public LoginOutputData(String username, boolean useCaseFailed, String userID) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

}
