package interface_adapter.clear_users;

public class ClearState {
    private String clearError = null;

    private String[] usersCleared = null;

    private String message = null;

    public ClearState(ClearState copy) {
        clearError = copy.clearError;
        usersCleared = copy.usersCleared;
        message = copy.message;
    }

    public ClearState() {}

    public String[] getUsersCleared() {
        return usersCleared;
    }

    public void setUsersCleared(String[] usersCleared) {
        this.usersCleared = usersCleared;
        this.message = convertArrayToString(usersCleared);
    }
    public void getClearError(String clearError) {
        this.clearError = clearError;
    }

    public void setClearError(String clearError) {
        this.clearError = clearError;
        this.message = clearError;
    }

    public String getMessage() {
        return message;
    }
    public String convertArrayToString(String[] usersCleared) {
        String usersClearedString = "";
        for (String user : usersCleared) {
            usersClearedString += user + "\n";
        }
        return usersClearedString;
    }
}

