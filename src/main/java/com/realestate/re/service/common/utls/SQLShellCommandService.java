package com.realestate.re.service.common.utls;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLShellCommandService {

    private String username;
    private String password;

    public SQLShellCommandService(String username , String password){
        this.username = username;
        this.password = password;
    }

    public List<String> showDatabases(){
        List<String> cmd = new ArrayList<>();

        cmd.add("bash");
        cmd.add("-c");
        cmd.add("mysql -u"+username+" -p"+password+" -e 'show databases;'");

        return refactorOutPut(ShellUtls.run(cmd) , "Database" , "");
    }

    //mysql -u dhiraj -p -e 'use usermanagement; show tables;'

    public List<String> showTables(String database){
        List<String> cmd = new ArrayList<>();

        cmd.add("bash");
        cmd.add("-c");
        cmd.add("mysql -u"+username+" -p"+password+" -e 'use "+database+"; show tables;'");

        return refactorOutPut(ShellUtls.run(cmd) , "Tables_in_" , database);
    }

    public List<String> refactorOutPut(List<String> stringList , String prefix , String suffix){
        List<String> tableNameList = new ArrayList<>();

        for (String table : stringList){
            table = table.replaceAll("\n" , "").replaceAll("\r" , "").trim();
            if (!table.equalsIgnoreCase(prefix + suffix)){
                tableNameList.add(table);
            }
        }

        return tableNameList;
    }

    public List<String> exportDatabases(String database , String tables , String dumpLocation){
        List<String> cmd = new ArrayList<>();

        cmd.add("bash");
        cmd.add("-c");
        cmd.add("mysqldump -u"+username+" -p"+password+" "+database+" "+tables+" --result-file="+dumpLocation);
        cmd.add("fwef");

        return ShellUtls.run(cmd);
    }

    public List<String> importDatabase(String database , String filePath){
        //mysql -u dhiraj -p usermanagement < /home/dhiraj/client/binod_bhai.sql
        List<String> cmd = new ArrayList<>();
        cmd.add("bash");
        cmd.add("-c");
        cmd.add("mysql -u"+username+" -p"+password+" "+database+" < " + filePath);
        cmd.add("fwef");

        return ShellUtls.run(cmd);
    }

    public static void main(String[] args) {
        List<String> cmd = new ArrayList<>();

        String url = URI.create("https://play.google.com/store/search?q=free vpn&c=apps&hl=en".replaceAll("\\s+" ,"+")).toString();
        cmd.add("bash");
        cmd.add("-c");
        cmd.add("cd /home/dhiraj/client/;python test.py " + url);

        for (String str : ShellUtls.run(cmd)){
            System.out.println(str);
        }


    }
}
