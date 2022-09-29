package util;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ArrayConverter implements AttributeConverter<String[], String>{

	@Override
	public String convertToDatabaseColumn(String[] attribute) {
		/*List<String> x = Arrays.stream(attribute).map(e -> String.format("%s,", e)).toList();
		x.get(x.size()-1).split(",")[0];*/
		
		return String.join("," , attribute).replaceAll("\\s", "");
	}

	@Override
	public String[] convertToEntityAttribute(String dbData) {
		return dbData.split(",");
	}
	

}
