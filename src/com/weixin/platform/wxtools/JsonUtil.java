package com.weixin.platform.wxtools;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();
    public static SimpleDateFormat dateFm = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static CustomSerializerFactory serializerFactory = new CustomSerializerFactory();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(
                JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(
                org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.setSerializationConfig(mapper.getSerializationConfig()
                .withDateFormat(dateFm));
        mapper.setSerializerFactory(serializerFactory);
    }

    /**
     * 构造Collection类型.
     */
    public static JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }
    /**
     * 构造Map类型.
     */
    public static JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }
    /**
     * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构造类型, 然后调用本函数.
     */
    public static <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
//            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass)
            throws IOException {
        return mapper.readValue(jsonAsString, pojoClass);
    }

    @SuppressWarnings("unchecked")
    public static <T0, T1> Map<T0, T1> fromJsonToMap(String jsonAsString)
            throws IOException {
        return mapper.readValue(jsonAsString, Map.class);
    }

    public static String toJson(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    public static String buildResponeJson(Object object) {

        try {
            return toJson(object);
        } catch (IOException e) {
            return "";
        }


    }

}
