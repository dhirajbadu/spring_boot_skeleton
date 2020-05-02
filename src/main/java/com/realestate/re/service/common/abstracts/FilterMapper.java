package com.realestate.re.service.common.abstracts;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.*;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.DateParseUtil;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.ParseUtls;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;


public class FilterMapper{

    private Class cls;

    public void setClass(Class cls) {
        this.cls = cls;
    }


    public FilterMapper build(HttpServletRequest request , String... fieldsToFill){
        FilterMapper result = null;
        try {

            Map<String , String> values = generateMap(request , fieldsToFill);

            Object obj =  cls.newInstance();

            if (obj instanceof FilterMapper){
                result = (FilterMapper) obj;
            }else {
                throw new Exception("provided class " + cls.toString() + " is not a instanceof FilterMapper \n please extends FilterMapper");
            }

            //Field[] fields = cls.getDeclaredFields();

            List<Field> fields = new ArrayList<>(Arrays.asList(cls.getDeclaredFields()));


            Class superClass = cls.getSuperclass();

            if (superClass != null){
                fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
            }

            for (Field field : fields){
                if (!field.isAccessible()){
                    field.setAccessible(true);
                }

                if ("max".equals(field.getName())){
                    field.set(result, ParseUtls.parseInt(request.getParameter("length")));
                }

                if ("offset".equals(field.getName())){
                    field.set(result, ParseUtls.parseInt(request.getParameter("start")));
                }

                if ("direction".equals(field.getName())){
                    field.set(result, request.getParameter("order[0][dir]"));
                }

                if ("property".equals(field.getName())){
                    field.set(result, request.getParameter("order[0][column]"));
                }

                if ("query".equals(field.getName())){
                    field.set(result, request.getParameter("search[value]"));
                }

                if (values != null) {
                    if (!"NA".equals(values.getOrDefault(field.getName(), "NA"))) {
                        if (field.getType().equals(String.class)) {
                            field.set(result, values.get(field.getName()));
                        } else if (field.getType().equals(Long.class)) {
                            field.set(result, ParseUtls.parseLong(values.get(field.getName())));
                        } else if (field.getType().equals(Integer.class)) {
                            field.set(result, ParseUtls.parseInt(values.get(field.getName())));
                        } else if (field.getType().equals(Double.class)) {
                            field.set(result, ParseUtls.parseDouble(values.get(field.getName())));
                        } else if (field.getType().equals(Status.class)) {
                            field.set(result, Status.valueOf(values.get(field.getName())));
                        } else if (field.getType().equals(Date.class)) {
                            field.set(result, DateParseUtil.parseDate(values.get(field.getName())));
                        }
                    }
                }

            }

        }catch (Exception e){

            LoggerUtil.logException(this.cls , e);
        }

        return result;
    }

    private Map<String , String> generateMap(HttpServletRequest request , String... fields){
        Map<String , String> values = new HashMap<String, String>();

        if (fields == null){
            return null;
        }

        if (fields.length == 0){
            return null;
        }

        for (int i = 0 ; i < fields.length ; i++){
            values.put(fields[i] , request.getParameter("columns["+i+"][search][value]"));
        }

        return values;
    }

 private JSONObject readJsonFromRequest(HttpServletRequest request){
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            LoggerUtil.logException(this.cls , e);
        }

        try {
            return HTTP.toJSONObject(jb.toString());
        } catch (JSONException e) {
            LoggerUtil.logException(this.cls , e);
        }

        return null;
    }

}
