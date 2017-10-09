package com.javen.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakeDIR {

	private List<File> list=new ArrayList<File>();
	//创建文件夹
	public  String makeDirs(String filePath) {
        File file=new File(filePath);
        if (file.exists()&&file.isDirectory()) {//判断文件是否存在或者文件是文件夹
            return file.getAbsolutePath();//如果是，则返回文件的根目录
        }
        if(!file.exists()){
        	file.mkdirs();//不存在就创建
        	return file.getAbsolutePath();//并返回创建后的文件夹的根目录
        }
        if(file.exists()&&!file.isDirectory()){//文件存在，但是不是文件夹
        	file.mkdirs();//创建文件夹
        	return file.getAbsolutePath();//返回创建后的文件夹的根目录
        }
        else
        	return file.getAbsolutePath();//返回文件夹的根目录
    }
	//新建文件
	public String makeFile(String filePath){
		File file=new File(filePath);
		if(!file.exists()){//判断文件是否存在
			try {
				file.createNewFile();//不存在就创建
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file.getAbsolutePath();//返回创建的文件夹的根目录
		}
		return file.getAbsolutePath();//返回文件夹的根目录
	}
	
	   /**
     * 读取某个文件夹下的所有文件
     */
	//递归思想找出某文件夹下的所有文件
    public  List<File> readfile(String filepath) throws FileNotFoundException, IOException {
            try {
                    File file = new File(filepath);
                    if (!file.isDirectory()) {//传入的文件不是文件夹
                    		list.add(file);
                            System.out.println("文件");
                            System.out.println("path=" + file.getPath());
                            System.out.println("absolutepath=" + file.getAbsolutePath());
                            System.out.println("name=" + file.getName());

                    } else if (file.isDirectory()) {//传入的文件是文件夹
                            System.out.println("文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {//遍历改文件夹下的文件
                                    File readfile = new File(filepath + "\\" + filelist[i]);
                                    if (!readfile.isDirectory()) {//若不是文件夹
                                    		  list.add(readfile);
//                                            String path=readfile.getPath();
//                                            String absolutepath=readfile.getAbsolutePath();
//                                            String name=readfile.getName();
                                    } else if (readfile.isDirectory()) {//如果是文件夹，调用该方法
                                            readfile(filepath + "\\" + filelist[i]);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            System.out.println("list的大小为："+list.size());
            return list;
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     */
    
    
    public boolean deletefile(String delpath)
                    throws FileNotFoundException, IOException {
            try {
                    File file = new File(delpath);
                    if (!file.isDirectory()) {
                            System.out.println("1");
                            file.delete();
                    } else if (file.isDirectory()) {
                            System.out.println("2");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File delfile = new File(delpath + "\\" + filelist[i]);
                                    if (!delfile.isDirectory()) {
                                            System.out.println("path=" + delfile.getPath());
                                            System.out.println("absolutepath="
                                                            + delfile.getAbsolutePath());
                                            System.out.println("name=" + delfile.getName());
                                            delfile.delete();
                                            System.out.println("删除文件成功");
                                    } else if (delfile.isDirectory()) {
                                            deletefile(delpath + "\\" + filelist[i]);
                                    }
                            }
                            file.delete();

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("deletefile()   Exception:" + e.getMessage());
            }
            return true;
    }
    
//    public static void main(String[] args) {
//    	MakeDIR m=new MakeDIR();
//            try {
//                    List<File> list=new ArrayList<>();
//                    list=m.readfile("F:/AcceptanceFile");
//                    // deletefile("D:/file");
//                    System.out.println("fsdfsdfs"+list.size());
//            } catch (FileNotFoundException ex) {
//            } catch (IOException ex) {
//            }
//    }
}
