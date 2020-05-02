package com.realestate.re.service.common.utls;

import com.realestate.re.service.common.constants.ParameterConstants;

public class MailTemplateUtls {

    public static final String officialMailTemplate(String title , String header,   String body, String footer){

        //System.out.println(ParameterConstants.EXCEPTION_MAIL_TEMPLATE_PATH);

        String template = new FileHandler().readFileToString(ParameterConstants.OFFICIAL_MAIL_TEMPLATE_PATH);

        template = template.replace("{title}" , title).replace("{header}"  , header).replace("{bodycontent}" , body).replace("{footer}" , footer);

        return template;
    }
}
