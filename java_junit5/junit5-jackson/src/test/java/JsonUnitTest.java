import com.ceshiren.entity.Order;
import com.ceshiren.entity.OrderLine;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JsonUnitTest {
    @Test
    void hashMapTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());

        TypeReference<List<HashMap<String, Object>>> typeReference =
                new TypeReference<List<HashMap<String, Object>>>() {
                };
        List<HashMap<String, Object>> list = objectMapper.readValue(new File("src/test/resources/json/orderlist.json"), typeReference);
        System.out.println(list);
    }

    @Test
    void orderLinesTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        TypeReference<List<OrderLine>> typeReference = new TypeReference<List<OrderLine>>() {
        };
        //日期相关解析需要该语句
        objectMapper.findAndRegisterModules();
        List<OrderLine> list =
                objectMapper.readValue(
                        new File("src/test/resources/json/orderlist.json"),
                        typeReference);
        System.out.println(list);
    }

    @Test
    void orderMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> map = objectMapper.readValue(
                new File("src/test/resources/json/order.json"),
                typeReference);
        System.out.println(map);
    }

    @Test
    void orderTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        TypeReference<Order> typeReference =
                new TypeReference<Order>() {};
        //对应日期解析正常
        mapper.findAndRegisterModules();
        Order order =
                mapper.readValue(
                        new File("src/test/resources/json/order.json"),
                        typeReference);

        System.out.println(order);
    }

}
