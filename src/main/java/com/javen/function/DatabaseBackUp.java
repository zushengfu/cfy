package com.javen.function;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

public class DatabaseBackUp{
	//数据全部备份
	public void backup(Date date) {
        try {
            Runtime rt = Runtime.getRuntime();
            System.out.println("rt开始运行！！！！");
            // 调用 调用mysql的安装目录的命令
            Process child = rt.exec("cmd.exe /c "+"d:\\mySQL5.7\\bin\\mysqldump -h127.0.0.1 -uROOT -pROOT cfy_db");
            // 设置导出编码为utf-8。这里必须是(utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            System.out.println("进来了乜有啊？");
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            DateChange dateChange=new DateChange();
			MakeDIR makeDIR=new MakeDIR();
            outStr = sb.toString();
            //得到日期的年份
            String dateYear=dateChange.dateToStrWithY(date);
            //得到日期的年月日
            String dateName =dateChange.DateToStr(date);
            //以年做文件夹名创建文件夹
            String dirAdress=makeDIR.makeDirs("d:\\backupDatabase\\"+dateYear);
            //以年月日作文件名创建文件
            String fileAdress=makeDIR.makeFile("d:\\backupDatabase\\"+dateYear+"\\"+dateName+".sql");
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(fileAdress);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            System.out.println("....成功备份...");
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	/**根据卷内或归档，条件储存
	 * @param condition 要备份的表名（file_info/file_records）
	 * @param timeStart 选定的开始备份时间
	 * @param timeOver 选定的结束备份时间
	 * @param date 备份的时间
	 * @param request
	 * @return
	 */
	public void conditionBackup(Date date,String condition,String timeStart,String timeOver) {
        try {
            Runtime rt = Runtime.getRuntime();
            System.out.println("rt开始运行！！！！");
            // 调用 调用mysql的安装目录的命令
            Process child = rt.exec("cmd.exe /c "+"d:\\mySQL5.7\\bin\\mysqldump -h127.0.0.1 -uROOT -pROOT cfy_db "+condition+" -w"+" \"upload_time"+" between"+" "+"'"+timeStart+"'"+" "+"and"+" "+"'"+timeOver+"'\"");
            System.out.println("d:\\mySQL\\bin\\mysqldump -h127.0.0.1 -uroot -proot cfy_db "+condition+" -w"+" \"upload_time"+" between"+" "+"'"+timeStart+"'"+" "+"and"+" "+"'"+timeOver+"'\"");
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            System.out.println("进来了乜有啊？");
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            DateChange dateChange=new DateChange();
			MakeDIR makeDIR=new MakeDIR();
            outStr = sb.toString();
            String dateYear=dateChange.dateToStrWithY(date);
            //设置文件名
            String dateName =timeStart+"_"+timeOver;
            //按照年份来命名文件夹，若不存在，则创建
            String dirAdress=makeDIR.makeDirs("d:\\backupDatabase\\"+dateYear);
            //创建文件，
            String fileAdress=makeDIR.makeFile("d:\\backupDatabase\\"+dateYear+"\\"+dateName+".sql");
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(fileAdress);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            System.out.println("....成功备份...");
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}