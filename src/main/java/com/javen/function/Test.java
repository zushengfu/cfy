package com.javen.function;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	
	public static void main(String[] args) {
		List<Integer> list=new ArrayList();
		for(int i=0;i<100;i++){
			list.add(i);
		}
		System.out.println("");
		System.out.println("");
		for(int d=0;d<list.size();d++){
			if(d==0)
				System.out.print("          "+" "+"                   0"+"\r");
			if(d==1)
				System.out.print("         "+"0"+"      "+"0"+"           00000"+"  0000"+"            0  0"+"\r");
			if(d==2)
				System.out.print("        "+"0"+" "+"0"+" "+"0"+" "+"0"+" "+"0"+" "+"0"+"             0"+"  0"+"  0"+"           0   0    "+"\r");
			if(d==3)
				System.out.print("       "+"0"+"0"+"       "+"0"+"            0"+"00"+"   0"+"000"+"          0 0 000 0"+"\r");
			if(d==4)
				System.out.print("      "+"0"+" "+"0"+"   0   "+"0"+"          0"+"  0 0  "+"0"+"000"+"         0"+"   0 0 0 "+"\r");
			if(d==5)
				System.out.print("     "+" "+"  "+"0"+"       "+"0"+"           "+"  0"+"    0"+"  0"+"               0     "+"\r");
			if(d==6)
				System.out.print("     "+"   "+"0"+"      0"+"0"+"           "+"  0"+"  000000000"+"       00000000000 "+"\r");
				}
	}
}