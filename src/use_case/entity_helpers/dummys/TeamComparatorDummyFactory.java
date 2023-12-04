package use_case.entity_helpers.dummys;

import use_case.entity_helpers.TeamComparatorFactory;

public class TeamComparatorDummyFactory implements TeamComparatorFactory {
    public TeamComparatorDummy create() {
        return new TeamComparatorDummy(new TeamEvaluatorDummy(new PlayerEvaluatorDummy()));
    }
}
