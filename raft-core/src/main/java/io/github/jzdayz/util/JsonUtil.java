package io.github.jzdayz.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

  private static final ObjectMapper om = new ObjectMapper();

  public static byte[] toBytes(Object obj) {
    try {
      // 默认就是utf-8
      return om.writeValueAsBytes(obj);
    } catch (JsonProcessingException e) {
      log.error("can not write json", e);
      throw new RuntimeException(e);
    }
  }

  public static <T> T toObj(byte[] bytes, Class<T> cla) {
    try {
      // 会推断字符集编码
      return om.readValue(bytes, cla);
    } catch (IOException e) {
      log.error("can not read json", e);
      throw new RuntimeException(e);
    }
  }

}
