import com.ceshiren.entity.OrderLine;
import com.ceshiren.entity.OrderList;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CsvUnitTest {
    @Test
    void csvReadWithHeader() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        //带着header读取
        CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        csvMapper.findAndRegisterModules();
        //MappingIterator<Object> objectMappingIterator = csvMapper.readerFor(OrderLine.class)
        MappingIterator<Object> mappingIterator = csvMapper.readerFor(OrderList.class)
                .with(orderLineSchema)
                .readValues(new File("src/test/resources/csv/orderLines.csv"));
        System.out.println(mappingIterator.readAll());
    }

    @Test
    void csvReadWithHeaderMap() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        csvMapper.findAndRegisterModules();
        //.setSkipFirstDataRow(true) 第一行header不解析
        CsvSchema csvSchema = CsvSchema.builder().setSkipFirstDataRow(true)
                .addColumn("item")
                .addColumn("quantity")
                .addColumn("unitPrice")
                .addColumn("orderDate")
                .build();
        MappingIterator<Object> mappingIterator = csvMapper.readerForMapOf(String.class)
                .with(csvSchema)
                .readValues(new File("src/test/resources/csv/orderLines.csv"));
        System.out.println(mappingIterator.readAll());
    }

    @Test
    void csvReadOutHeaader() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.findAndRegisterModules();
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("item")
                .addColumn("quantity")
                .addColumn("unitPrice")
                .addColumn("orderDate")
                .build();
        MappingIterator<Object> mappingIterator = csvMapper.readerFor(OrderLine.class)
                .with(csvSchema)
                .readValues(new File("src/test/resources/csv/orderList.csv"));
        System.out.println(mappingIterator.readAll());
    }

    @Test
    void csvReadOutHeaderMap() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
        csvMapper.findAndRegisterModules();
        CsvSchema schema = CsvSchema.builder()
                .addColumn("item")
                .addColumn("quantity")
                .addColumn("unitPrice")
                .addColumn("orderDate")
                .build();
        MappingIterator<Object> objectMappingIterator = csvMapper.readerForMapOf(String.class)
                .with(schema)
                .readValues(new File("src/test/resources/csv/orderlist.csv"));
        System.out.println(objectMappingIterator.readAll());
    }

}
