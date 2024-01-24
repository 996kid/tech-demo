package com.eastwood;

// 查询elasticSearch数据的代码模板
// index = "history" 为索引名称
// 字段有 id name
public class Test {

    public static void main(String[] args) {
        String index = "history";
        String type = "history";
        String id = "1";
        String name = "name";
        String value = "value";
        String json = "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"value\":\"" + value + "\"}";
        System.out.println(json);
         ElasticSearchUtil.add(index, type, id, json);
        // ElasticSearchUtil.delete(index, type, id);
        // ElasticSearchUtil.update(index, type, id, json);
        // ElasticSearchUtil.get(index, type, id);
        // ElasticSearchUtil.search(index, type, name, value);
    }
}





