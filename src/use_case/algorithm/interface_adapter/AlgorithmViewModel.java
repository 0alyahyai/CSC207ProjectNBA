package use_case.algorithm.interface_adapter;

import use_case.compareTeam.interface_adapter.CompareState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AlgorithmViewModel extends ViewModel {

    public final String viewName = "algorithm-view";

    private AlgorithmState state;


    public AlgorithmViewModel() {
        super("algorithm-view");

    }

    public void setState(AlgorithmState state) {

        this.state = state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) { //
        support.addPropertyChangeListener(listener);
    }


    public AlgorithmState getState() {
        return state;
    }
}
