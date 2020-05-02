package com.realestate.re.service.common.abstracts;

import com.realestate.re.service.common.utls.ParseUtls;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by dhiraj on 6/4/17.
 */

public class GlobalValidation {

    protected String checkEmail(String value, int minLength, int maxLength, String target, boolean notNull) {
        String error = checkString(value, minLength, maxLength, target, notNull);

        if (isNotNull(error)) {
            return error;
        } else if (isNotNull(value)) {
            return checkEmail(value);
        }

        return "";
    }

    protected String checkEmail(String value) {

        try {

            InternetAddress emailAddr = new InternetAddress(value);

            emailAddr.validate();

            return "";

        } catch (AddressException ex) {

            return "invalid email";

        }

    }

    protected boolean isNull(String value) {
        return ParseUtls.isNull(value);
    }

    protected boolean isNotNull(String value) {
        return ParseUtls.isNotNull(value);
    }

    protected String checkBigDacimal(BigDecimal value, int min, int maxFraction, String target, boolean notNull) {

        if (notNull && value == null) {

            return "Cannot Be Null";
        }

        if (value != null) {
            Double value1 = value.doubleValue();

            if (value1 < min) {

                return "Must Be Greater Than " + min;
            }

            return checkFraction(value1, maxFraction);
        }
        return "";
    }


    protected String checkDouble(Double value, int min, int maxFraction, String target, boolean notNull) {

        if (notNull && value == null) {

            return "Cannot Be Null";
        }

        if (value != null) {

            if (value < min) {

                return "Must Be Greater Than " + min;
            }

            return checkFraction(value, maxFraction);
        }
        return "";
    }
    
    protected String checkNotGreaterThan(String value, int length) {

       if(value!=null){
    	   if(value.length()>length){
    		   return "Must Be Less Than " + length +" Character";
    	   }
       }
        return "";
    }

    protected String checkInteger(Integer value, int min, int max, String target, boolean notNull) {

        if (notNull && value == null) {

            return "Cannot Be null";
        }

        if (value != null) {

            if (value < min) {

                return "Must Be Greater Than " + min;
            }

            if (value > max) {

                return "Must Be Less Than " + max;
            }
        }

        return "";

    }

    protected String checkLong(Long value, int min, String target, boolean notNull) {

        if (notNull && value == null) {

            return target + " Cannot Be null";
        }

        if (value != null) {

            if (value < min) {

                return target + " Must Be Greater Than " + min;
            }

        }

        return "";

    }

    protected String checkLongDuplicate(List<Long> value, String target) {

        if (ParseUtls.isDuplicateForLong(value)) {
            return target + " can not be added more then one";
        }

        return "";
    }

    protected String checkString(String value, int minLength, int maxLength, String target, boolean notNull) {

        if (notNull && value == null) {

            return target + " Cannot Be Null";
        } else if (notNull && value.isEmpty()) {
            return target + " Cannot Be Null";
        }

        if (value != null && !value.isEmpty()) {

            if ("".equals(value.trim())) {

                return target + " Cannot Be Blank";
            }

            if (minLength == maxLength) {

                if (value.length() != minLength) {

                    return "Exactly " + minLength + " length required";
                }

            } else {

                if (value.length() < minLength) {

                    return target + " Must Be Greater Than " + minLength + " length";
                }

                if (value.length() > maxLength) {

                    return target + " Must Be Less Than " + maxLength + " length";
                }
            }

        }
        return "";
    }

    protected String checkDoubleGreaterThan(Double value, Double checker, String target, String checkerTarget, boolean isEqual) {

        if (value != null && checker != null) {

            if (isEqual) {

                if (value < checker | checker > value) {

                    return "Must be Greater or Equal Than " + checkerTarget;
                }
            } else {

                if (value < checker || Objects.equals(value, checker)) {

                    return "Must Be Greater Than " + checkerTarget;
                }
            }
        }

        return "";
    }

    protected String checkIntegerGreaterThan(Integer value, Integer checker, String target, String checkerTarget, boolean isEqual) {

        if (value != null && checker != null) {

            if (isEqual) {

                if (value < checker) {

                    return "Must Be Greater or Equal Than " + checkerTarget;
                }
            } else {

                if (value < checker || Objects.equals(value, checker)) {

                    return "Must Be Greater Than " + checkerTarget;
                }
            }
        }

        return "";
    }

    protected String checkDate(String dateStr, String target, boolean notNull, boolean isBefore, boolean isAfter) {

        if (notNull && dateStr == null) {

            return "Cannot Be Null";
        }

        if (dateStr != null) {

            if (dateStr.length() != 10 || !(dateStr.matches("\\d{4}-[01]\\d-[0-3]\\d"))) {

                return "invalid date";
            }

            return checkDateFormat(dateStr, isBefore, isAfter);
        }

        return "";
    }

    protected String checkDate(Date dateStr, String target, boolean notNull, boolean isBefore, boolean isAfter) {

        if (notNull && dateStr == null) {

            return "Cannot Be Null";
        }

        return "";
    }


    private String checkFraction(double value, int maxFraction) {

        String aString = Double.toString(value);

        String[] fraction = aString.split("\\.");

        if (fraction.length == 2) {

            if (fraction[1].length() > maxFraction) {

                return "Floating Value Limit Exceeded!";
            }
        } else {

            return "Invalid Number Format is eg: 10.00 , 23.93 ";
        }

        return "";
    }

    private String checkDateFormat(String dateStr, boolean isBefore, boolean isAfter) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        df.setLenient(false);

        try {

            Date date = df.parse(dateStr);

            if (isBefore) {

                return checkDateIsBefore(date, df);
            }

            if (isAfter) {

                return checkDateIsAfter(date, df);

            }

        } catch (ParseException ex) {

            return "invalid date";
        }

        return "";
    }

    private String checkDateIsBefore(Date date, SimpleDateFormat df) {

        try {

            String currentDateStr = df.format(new Date());

            Date currentDate = df.parse(currentDateStr);

            if (!date.before(currentDate)) {

                return "date must be before current date";
            }
        } catch (Exception e) {

            return "date must be before current date";
        }

        return "";
    }

    private String checkDateIsAfter(Date date, SimpleDateFormat df) {

        try {

            String currentDateStr = df.format(new Date());

            Date currentDate = df.parse(currentDateStr);

            if (!date.after(currentDate)) {

                return "date must be after current date";
            }

        } catch (Exception e) {

            return "date must be after current date";
        }

        return "";
    }

   /* protected String checkUnique(AbstractEntity entity, Long excludeUniqueForId, String field) {
        if (excludeUniqueForId != null) {
            if (entity != null && !Objects.equals(excludeUniqueForId, entity.getId()))
                return "This " + field + " is already taken";
        } else if (entity != null) {
            return "This " + field + " is already taken";
        }
        return "";
    }*/

    protected String checkImage(MultipartFile file, boolean isNotNull) {

        if (isNotNull && file == null) {
            return "please choose any image";
        }

        if (file != null) {
            if (file.getSize() == 0) {
                if (isNotNull)
                    return "Please select a file.";
                return "";
            } else if (!(Objects.equals(file.getContentType(), MimeTypeUtils.IMAGE_PNG_VALUE) | Objects.equals(file.getContentType(), MimeTypeUtils.IMAGE_JPEG_VALUE))) {
                return "invalid file";
            } else if (file.getSize() > 1024 * 1024 * 1) {//1 MB
                return "file too large";
            } else if (file.getSize() < 1024 * 10) { //10 KB
                return "file too small";
            } else if (Objects.equals(file.getContentType(), MimeTypeUtils.IMAGE_JPEG_VALUE)) {
                if (MimeTypeUtils.IMAGE_JPEG.compareTo(MimeTypeUtils.parseMimeType(file.getContentType())) != 0)
                    return "invalid file";
            } else if (Objects.equals(file.getContentType(), MimeTypeUtils.IMAGE_PNG_VALUE)) {
                if (MimeTypeUtils.IMAGE_PNG.compareTo(MimeTypeUtils.parseMimeType(file.getContentType())) != 0)
                    return "invalid file";
            }

            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

                if (image == null) {
                    return "inavlid image";
                } else if (image.getWidth() < 200) {
                    return "image width " + image.getWidth() + " is less than 200";
                } else if (image.getHeight() < 200) {
                    return "image height " + image.getHeight() + " is less than 200";
                }
            } catch (IOException e) {
                //LoggerUtil.logWarn(this.getClass(), e);
                return "invalid image";
            }

        } else {
            return "please select image";
        }

        return "";
    }

}
