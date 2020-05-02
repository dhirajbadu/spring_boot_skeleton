package com.realestate.re.service.common.utls;


import com.realestate.re.service.common.constants.ParameterConstants;

/**
 * Created by dhiraj on 8/28/17.
 */
public class FilePath {

    public static String getOSPath() {

        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.indexOf("win") >= 0) {
            return ParameterConstants.WINDOWS_PATH;
        } else if (OS.indexOf("mac") >= 0) {
            return ParameterConstants.MAC_PATH;
        } else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
            return ParameterConstants.UNIX_PATH;
        }

        return null;
    }

}
