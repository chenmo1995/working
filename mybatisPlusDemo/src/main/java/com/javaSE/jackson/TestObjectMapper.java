package com.javaSE.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fdn
 * @since 2021-11-01 15:11
 */
public class TestObjectMapper {
    public static void main(String[] args) throws JsonProcessingException {

//        easyTest();
        someHighLevel();

    }

    private static void easyTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person fdn = new Person("fdn", 26, new Date(1995, 8, 17));
        String s = objectMapper.writeValueAsString(fdn);
        System.out.println(s);
        String personAsString = "{\"name\":\"fdn\",\"age\":26,\"birthday\":60769497600000}";
        Person person = objectMapper.readValue(personAsString, Person.class);
        System.out.println(person);
        JsonNode jsonNode = objectMapper.readTree(personAsString);
        String name = jsonNode.get("name").asText();
        String birthday = jsonNode.get("birthday").asText();
        System.out.println(name);
        System.out.println(birthday);
        System.out.println("***************************转集合***************************");
        String personStringList = "[{\"name\":\"fdn\",\"age\":26,\"birthday\":60769497600000}," +
                "{\"name\":\"fdn2\",\"age\":262,\"birthday\":60769497600000}]";
        List<Person> people = objectMapper.readValue(personStringList, new TypeReference<List<Person>>() {
        });
        System.out.println(people);
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map
                = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map);
    }

    public static void someHighLevel() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        // 忽略json字符串中不识别的属性
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        // 忽略无法转换的对象
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        // PrettyPrinter 格式化输出
//        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//        // NULL不参与序列化
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        ObjectMapper objectMapper = new ObjectMapper();


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        Person fdn = new Person("fdn", 26, new Date(1995, 8, 17));
        String s = objectMapper.writeValueAsString(fdn);
        System.out.println(s);




    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private int age;
    private Date birthday;
}
