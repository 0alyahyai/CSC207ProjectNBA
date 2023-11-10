package use_case.signup;

public class SignupOutputData {

    //Swapped from Creation time to Id, but only have a placeholder function for creating id, so all ids are 1 for now

    private final String username;
    private int userId;

    private boolean useCaseFailed;

    public SignupOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.userId = 1;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public int getCreationTime() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

}
