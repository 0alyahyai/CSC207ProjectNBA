package entity.dummys;

public class TeamComparatorDummyFactory implements entity.TeamComparatorFactory{
    public TeamComparatorDummy create() {
        return new TeamComparatorDummy(new TeamEvaluatorDummy(new PlayerEvaluatorDummy()));
    }
}
