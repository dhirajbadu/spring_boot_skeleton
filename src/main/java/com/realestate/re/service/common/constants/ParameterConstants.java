package com.realestate.re.service.common.constants;

import java.io.File;

public class ParameterConstants {

    public final static String WINDOWS_PATH = System.getProperty( "user.home")+ File.separator+"Documents";
    public final static String MAC_PATH = "/Users";
    public final static String UNIX_PATH = System.getProperty( "user.home");

    public final static String EXCEPTION_MAIL_TEMPLATE_PATH = "/mail/exceptionmailtemplate.tag";
    public final static String OFFICIAL_MAIL_TEMPLATE_PATH = "/mail/officialmailtemplate.tag";


    public final static String AGENT_ROLE_TITLE = "Agent_Role";

}
