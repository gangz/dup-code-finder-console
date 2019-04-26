
package cn.emergentdesign.dcs.output.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDumper {

	public boolean output(String filename, Object object) {
		toJson(object, filename);
		return true;
	}

	private void toJson(Object object, String jsonFileName) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFileName), object);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
