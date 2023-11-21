package use_case.clear_users;

public class ClearOutputData {
    private final boolean useCaseFailed;

    private final String[] usersCleared;

    public ClearOutputData(String[] usersCleared, boolean useCaseFailed) {

        this.useCaseFailed = useCaseFailed;
        this.usersCleared = usersCleared;
    }

    public String[] getUsersCleared() {
        return usersCleared;
    }
}
