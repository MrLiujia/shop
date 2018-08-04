package shop.mapper;

import java.util.Map;

public interface ObjectMapper {

	String writeValueAsString(Map<String, Object> bizContent);

}
