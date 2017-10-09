package com.javen.function;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.chainsaw.Main;

public class DatabaseRestore {
	
	
	public  void restore(String databaseName) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("cmd.exe /c " + "D:\\mySQL5.7\\bin\\mysql.exe -h127.0.0.1 -uROOT -pROOT cfy_db --default-character-set=utf8 ");
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream("D:\\backupDatabase\\2017\\"+databaseName), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}