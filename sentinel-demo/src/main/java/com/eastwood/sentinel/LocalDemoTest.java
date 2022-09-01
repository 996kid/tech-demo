package com.eastwood.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LocalDemoTest {

    public static void main(String[] args) throws InterruptedException {
        initFlowRulesQps();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
                try (Entry entry = SphU.entry("HelloWorld")) {
                    // 被保护的逻辑
                    System.out.println("hello world");
                } catch (BlockException ex) {
                    // 处理被流控的逻辑
                    System.out.println("blocked!");
                }
            }).start();
        }
    }

    /**
     * 基于qps的流控
     */
    private static void initFlowRulesQps(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(10);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 基于并发线程数的流控
     */
    private static void initFlowRulesThread(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_THREAD);
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}

