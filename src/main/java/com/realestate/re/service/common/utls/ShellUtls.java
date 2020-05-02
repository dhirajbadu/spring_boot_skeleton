package com.realestate.re.service.common.utls;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ShellUtls {

    public static List<String> run(List<String> command){
        try {
            ProcessBuilder coolBuilder = new ProcessBuilder(command);
            Process process = coolBuilder.start();
            int errCode = process.waitFor();
            if (errCode != 0) {
                System.out.println("erro + " + errCode);
            }

            for (String s : output(process.getErrorStream())){
                LoggerUtil.logWarn(ShellUtls.class , s);
            }

            return output(process.getInputStream());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> output(InputStream inputStream) throws IOException {
        return ParseUtls.output(inputStream);
    }
}
