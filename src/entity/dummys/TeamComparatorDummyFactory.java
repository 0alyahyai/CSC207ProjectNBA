package entity.dummys;

import use_case.entity_utilities.TeamComparatorFactory;

public class TeamComparatorDummyFactory implements TeamComparatorFactory {
    public TeamComparatorDummy create() {
        return new TeamComparatorDummy(new TeamEvaluatorDummy(new PlayerEvaluatorDummy()));
    }
}
