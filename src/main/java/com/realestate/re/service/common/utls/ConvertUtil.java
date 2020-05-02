package com.realestate.re.service.common.utls;

import com.realestate.re.service.common.abstracts.OffsetBasedPageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtil {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ConvertUtil.class);


	public static  final Pageable createPageRequest(Integer page , Integer size , String properties , Sort.Direction direction) {

		if (page == null){
			page = 0;
		}

		if (page < 0){
			page = 0;
		}

		if (size == null){
			size = 10;
		}

		if (size < 1){
			size = 10;
		}

		if (size > 501){
			size = 500;
		}

		if (properties == null){
			properties = "id";
		}

		if (direction == null){
			direction = Sort.Direction.DESC;
		}

		return new PageRequest(page, size, new Sort(direction, properties));
	}

	public static  final Pageable createPageRequest(Integer page , Integer size , String properties , String direction) {

		Sort.Direction dir = Sort.Direction.ASC;

		if (direction == null){
			direction = "desc";
		}

		if ("desc".equalsIgnoreCase(direction)){
			dir = Sort.Direction.DESC;
		}

		return createPageRequest(page , size , properties , dir);
	}

	public static final String getValidProperty(String property , String...properties){
		String result = "id";

		if (property == null){
			return result;
		}

		property = property.trim();

		if ("".equals(property) || property.isEmpty()){
			return result;
		}

		int count = 0;
		for (String v : properties){
			if (property.equals(String.valueOf(count))){
				return v;
			}
			count ++;
		}

		return result;
	}

	public static  final Pageable createOffsetPageRequest(Integer max, Integer offset , String direction , String property) {

		if (max == null)
			max = 10;

		if (max < 1)
			max = 10;

		if (offset == null)
			offset = 0;

		if (offset < 0)
			offset = 0;

		if (max - offset > 501 || max - offset < 0){
			max = 10;
			offset = 0;
		}

		if (direction == null)
			direction = "desc";

		Sort.Direction dir = Sort.Direction.ASC;

		if ("desc".equalsIgnoreCase(direction)){
			dir = Sort.Direction.DESC;
		}

		return  new OffsetBasedPageRequest(offset , max , dir , property);

	}

	public static final String[] getField(Class clss , String ...exclude){

		Field[] fields = clss.getDeclaredFields();

		if (fields.length < exclude.length){
			return new String[]{"id"};
		}

		String[] fieldArry = new String[fields.length + 1 - exclude.length];

		fieldArry[0] = "id";

		int count = 1;
		for (Field field : fields){
			boolean include = true;
			for (String ex : exclude){
				if (ex.equals(field.getName())){
					include =  false;
					break;
				}
			}

			if (include) {
				fieldArry[count] = field.getName();
				count++;
			}
		}

		return fieldArry;
	}

	public static BigDecimal convertDoubleToDecimal(double value) {
		return new BigDecimal(value);
	}

	public static double convertDecimalToDouble(BigDecimal value) {
		return value.doubleValue();
	}


	public static String getDate(Date date){
		SimpleDateFormat dateFormatYear = new SimpleDateFormat("MMM dd, yyyy");

		return dateFormatYear.format(new Date());
	}


	public static double formatter(double value){

		DecimalFormat df = new DecimalFormat("###.###");

		return Double.parseDouble(df.format(value));

	}

	public static BigDecimal formatter(BigDecimal value){

		DecimalFormat df = new DecimalFormat("###.##");

		return BigDecimal.valueOf(Double.parseDouble(df.format(value)));

	}

	public static String getRandomCode(int length) {

		String charString = Long.toString(System.currentTimeMillis());
		String[] arrayString = charString.split("");
		StringBuilder randomCode = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randomIndex = (int) (Math.random() * charString.length());
			randomCode.append(arrayString[randomIndex]);
		}
		/*
		 * long uniqueId = (long) (Math.random() * 10000L); String orderNo =
		 * Long.toString(uniqueId);
		 */

		System.out.println("generated code >>>> " + randomCode);
		return randomCode.toString();
	}
}