package com.realestate.re.service.common.utls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ParseUtls {

    public static boolean isNull(String value){
        if (value == null){
            return true;
        } else if (value.isEmpty() || value.trim().equals("")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isNull(Long value){
        return value == null;
    }

    public static boolean isNotNull(Long value){
        return !isNull(value);
    }

    public static boolean isNotNull(String value){
        return !isNull(value);
    }

    public static boolean isNull(Double value , boolean considerZero){
        if (value == null){
            return true;
        } else if (!considerZero && value == 0){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isNotNull(Double value , boolean considerZero){
        return !isNull(value , considerZero);
    }

    public static Double getDoubleFromString(String value , Double defaultValue){
        if (isNull(value) || !isNumber(trimString(value))){
            return defaultValue;
        }

        try {
            return doubleFormatter(Double.parseDouble(trimString(value)));
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static boolean isNumber(String str){

        return isNotNull(str) && (str.trim().matches("[-\\+]?\\d+(\\.\\d+)?") || str.trim().matches("[-\\+]?+(\\.\\d+)?") || /*match a -ve number that ends with (.) */ str.trim().matches("[-\\+]?\\d+(\\.)?"));  //match a number with optional '-' and decimal.;
    }

    public static String trimString(String value){
        if (value == null){
            return value;
        }

        return value.trim();
    }

    public static String replaceAllSpace(String value){
        if (value == null){
            return value;
        }

        return value.replaceAll("\\s+" , "");
    }

    public static double doubleFormatter(Double value){

        DecimalFormat df = new DecimalFormat("###.###");

        if (value == null){
            return 0;
        }

        return Double.parseDouble(df.format(value));

    }

    public static BigDecimal formatter(BigDecimal value){

        if (value == null){
            return new BigDecimal(0);
        }

        DecimalFormat df = new DecimalFormat("###.##");

        return BigDecimal.valueOf(Double.parseDouble(df.format(value)));

    }

    public static boolean isDuplicateForLong(List<Long> value) {

        if (value == null){
            return false;
        }

        if (value.size() <= 1){
            return false;
        }

        return value.stream().sequential().allMatch(new HashSet<>()::add);
    }

    public static Long parseLong(String value){
        if (isNull(value)){
            return null;
        }

        if (!isNumber(value)){
            return null;
        }

        try {
            return Long.parseLong(trimString(value));
        }catch (Exception e){
            return null;
        }
    }

    public static Integer parseInt(String value){
        if (isNull(value)){
            return null;
        }

        if (!isNumber(value)){
            return null;
        }

        try {
            return Integer.parseInt(trimString(value));
        }catch (Exception e){
            return null;
        }
    }

    public static Double parseDouble(String value){
        if (isNull(value)){
            return null;
        }

        if (!isNumber(value)){
            return null;
        }

        try {
            return doubleFormatter(Double.parseDouble(trimString(value)));
        }catch (Exception e){
            return null;
        }
    }

    public static List<Long> getLongList(Long... idList){
        return new ArrayList<>(Arrays.asList(idList));
    }

    public static List<Object> getObjectList(Object... objects){
        return new ArrayList<>(Arrays.asList(objects));
    }

    public static List<String> output(InputStream inputStream) throws IOException {
        List<String> sb = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.add(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb;
    }

    public static boolean isNull(Integer value) {
        return value == null;
    }
}
