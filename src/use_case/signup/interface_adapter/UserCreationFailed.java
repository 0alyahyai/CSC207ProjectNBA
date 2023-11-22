package use_case.signup.interface_adapter;

public class UserCreationFailed extends RuntimeException {
    public UserCreationFailed(String error) {
        super(error);
    }
}
