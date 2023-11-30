package use_case.compareTeam.interface_adapter;

import use_case.compareTeam.CompareInputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class CompareController {

    final CompareInputBoundary compareInputBoundary; //TODO lets create InputBoundary


    public CompareController(CompareInputBoundary compareInputBoundary) {
        this.compareInputBoundary = compareInputBoundary;
    }


}
