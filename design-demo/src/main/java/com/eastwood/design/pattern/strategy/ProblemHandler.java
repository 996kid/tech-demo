package com.eastwood.design.pattern.strategy;

/**
 * @ClassName: ProblemHandler
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 9:58
 */
public class ProblemHandler {

    private final Strategy strategy;

    public ProblemHandler(Strategy strategy) {
        this.strategy = strategy;
    }

    public void handle() {
        strategy.algorithmInterface();
    }

    public static void main(String[] args) {
        String type = "STRATEGYB";
        ProblemHandler problemHandler = new ProblemHandler(StrategyFactoryWithEnum.getByType(type));
        problemHandler.handle();
    }
}
