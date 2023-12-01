package use_case.algorithm;

import entity.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface AlgorithmDataAccessInterface {

    public ArrayList<Team> getteams(String teamname) throws IOException;
    public ArrayList<Double> getData(Team team);
}
