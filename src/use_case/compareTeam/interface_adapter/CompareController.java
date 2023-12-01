package use_case.compareTeam.interface_adapter;

import use_case.compareTeam.CompareInputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class CompareController {

    // Here in the controller, we will run the intercator. We create a method call excecute. It will have as a parameter
    // the input data. In this case, there is no input data. We will press the button use_case.use_case.use_case.algorithm one to show a list of
    // all the teams. So, in this case, the controller just executes de use case intercator without giving input data.
    final CompareInputBoundary compareInputBoundary; //This will be implemented by the Use case interactor
                                                    // In fact, this is an abtsraction of it


    public CompareController(CompareInputBoundary compareInputBoundary) {
        this.compareInputBoundary = compareInputBoundary;
    }

    public void execute(){
        compareInputBoundary.execute();
    }

}
