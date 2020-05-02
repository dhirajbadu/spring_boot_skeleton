package com.realestate.re.service.common.utls;

import java.util.ArrayList;
import java.util.List;

public class MacAddressUtls {

    public static boolean isValidMacAddress(String[] deviceMacAddress, String[] resultMacAddress) {
        if (deviceMacAddress == null) {
            LoggerUtil.logException(MacAddressUtls.class, new Exception("either invalid mac address command or anything else"));
            return false;
        }

        if (resultMacAddress == null) {
            LoggerUtil.logException(MacAddressUtls.class, new Exception("please provide valid mac address"));
            return false;
        }

        if (deviceMacAddress.length != resultMacAddress.length) {
            LoggerUtil.logException(MacAddressUtls.class, new Exception("length of device and result of mac address does not matched"));
            return false;
        }

        for (int i = 0; i < resultMacAddress.length; i++) {
            if (!resultMacAddress[i].equals(deviceMacAddress[i])) {
                return false;
            }
        }

        return true;
    }

    public static String getMacAddress() {

        if (isLenux()){
            return getMacAddressOfLenux();
        }else if (isWindow()){
            return getMacAddressOfWindows();
        }else if (isMac()){
            return getMacAddressOfMac();
        }

        return "";
    }

    public static String getMacAddressOfLenux() {
        List<String> mac = ShellUtls.run(getCommandToGetMacAddr());

        if (mac == null) {
            return "";
        }

        for (String str : mac) {
            str = str.replaceAll("\n", "").replaceAll("\r", "");
            if (ParseUtls.isNotNull(str) && str.length() == 17) {
                return str;
            }
        }

        return "";
    }

    public static String getMacAddressOfWindows() {
        List<String> mac = ShellUtls.run(getCommandToGetMacAddr());

        if (mac == null) {
            return "";
        }

        for (String str : mac) {
            //write your necessary filter logic to get mac only
            if (ParseUtls.isNotNull(str) && str.length() == 17) {
                return str;
            }
        }

        return "";
    }

    public static String getMacAddressOfMac() {
        List<String> mac = ShellUtls.run(getCommandToGetMacAddr());

        if (mac == null) {
            return "";
        }

        for (String str : mac) {
            //write your necessary filter logic to get mac only
            if (ParseUtls.isNotNull(str) && str.length() == 17) {
                return str;
            }
        }

        return "";
    }

    public static List<String> getCommandToGetMacAddr() {

        List<String> command = new ArrayList<>();

        if (isLenux()) {
            String prefix = "/bin/bash";
            String c = "-c";
            String terminalCommand = "ifconfig -a | grep -ioE '([a-z0-9]{2}:){5}..'";
            command.add(prefix);
            command.add(c);
            command.add(terminalCommand);
        } else if (isWindow()) {

            String prefix = "cmd.exe";
            String c = "/C";
            String terminalCommand = "ipconfig /all";// write the command to filter out mac address only
            command.add(prefix);
            command.add(c);
            command.add(terminalCommand);

        } else if (isMac()) {
            //write command for windows like lenux
        }

        return command;
    }

    public static boolean isLenux() {
        String OS = System.getProperty("os.name").toLowerCase();

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isWindow() {
        String OS = System.getProperty("os.name").toLowerCase();

        return OS.indexOf("win") >= 0;
    }

    public static boolean isMac() {
        String OS = System.getProperty("os.name").toLowerCase();

        return OS.indexOf("mac") >= 0;
    }
}
