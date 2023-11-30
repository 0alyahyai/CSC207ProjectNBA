package use_case.compareTeam.interface_adapter;

import use_case.login.interface_adapter.LoginState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CompareViewModel extends ViewModel {
    public final String TITLE_LABEL = "Compare View";

    public static final String ALGORITHMONE_BUTTON_LABEL = "ALGORITHM ONE";
    public static final String ALGORITHMTWO_BUTTON_LABEL = "ALGORITHM TWO";
    public static final String ALGORITHMTHREE_BUTTON_LABEL = "ALGORITHM THREE";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CompareState state = new CompareState();

    public CompareViewModel() {
        super("Compare");
    }

    public void setState(CompareState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CompareState getState() {
        return state;
    }

}
