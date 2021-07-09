package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author 996kid@gmail.com
 * @Description JacksonUtil
 * @Date 2021/3/12 16:18
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> jsonToMap(String json) throws JsonProcessingException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
        return objectMapper.readValue(json, javaType);
    }

    public static Map objectToMap(Object i) throws JsonProcessingException {
        return objectMapper.convertValue(i, Map.class);
    }

    public static String findChild(String json) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode jsonNode1 = jsonNode.findValue("authInfo");
        return jsonNode1.toPrettyString();
    }

    static class Abc {
        private String a = "1";

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\n" +
                "    \"extInfo\": {\n" +
                "        \"A\" : 123\n" +
                "    },\n" +
                "    \"authInfo\" : {\n" +
                "        \"B\" :456\n" +
                "    }\n" +
                "}";
//        Map map = jsonToMap(json);
//
//        System.out.println(objectToMap(map.get("authInfo")));

        System.out.println(findChild(json));

        System.out.println(objectToMap(new Abc()));
    }
}
