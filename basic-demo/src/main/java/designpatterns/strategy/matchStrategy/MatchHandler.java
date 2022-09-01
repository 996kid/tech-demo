package designpatterns.strategy.matchStrategy;

/**
 * @author 996kid@gmail.com
 * @Description MatchHandler
 * @Date 2022/6/28 9:27
 */
public class MatchHandler {

    private MatchStrategy matchStrategy;

    public MatchHandler(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    public String execute(String groupId) {
        return matchStrategy.match(groupId);
    }

    public void changeStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

}
