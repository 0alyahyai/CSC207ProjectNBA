package use_case.algorithm;

import entity.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface AlgorithmDataAccessInterface {

    public ArrayList<Team> getteams(String teamname) throws IOException;

}
