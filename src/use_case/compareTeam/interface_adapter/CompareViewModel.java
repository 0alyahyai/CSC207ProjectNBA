package use_case.compareTeam.interface_adapter;

import entity.Team;
import use_case.login.interface_adapter.LoginState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class  CompareViewModel extends ViewModel {
    public static final String popupString = "ERROR MESSAGE";

    public final String TITLE_LABEL = "Compare";
    public int optionLenght;

    public List<String> teams;
    public String viewName;
    public boolean possible;

    private CompareState state;

    public CompareViewModel() {
        super("CompareOptions");





    }

    public void setState(CompareState state) {

        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


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
