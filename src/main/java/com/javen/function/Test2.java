package com.javen.function;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
	
	
	public static void main(String[] args) throws InterruptedException {
		List<Integer> list=new ArrayList();
		for(int i=0;i<100;i++){
			list.add(i);
		}
		System.out.println("");
		System.out.println("");
		for(int d=0;d<list.size();d++){
			if(d==0)
				System.out.print("          "+" "+"    0              "+"\r");
			if(d==1){
				Thread.sleep(500);
				System.out.print("         "+" "+"0    0"+" "+"           "+"  "+"    0     0 "+"\r");
			}
			if(d==2){
				Thread.sleep(500);
				System.out.print("        "+"0"+" "+"0"+" "+"0"+" "+"0"+" "+"0"+" "+"0"+"             0 0 0 0 0 0"+"  "+" "+"               "+"\r");
			}
			if(d==3){
				Thread.sleep(500);
				System.out.print("       "+" "+""+"   0 0 "+" "+"            "+""+"   "+""+"    0  0       "+"\r");
			}
			if(d==4){
				Thread.sleep(500);
				System.out.print("    0 "+"0"+" "+"0"+" 0 0 "+"0"+" 0 0 0 0   "+"  0 0 0 0 0 0 0 0 0 0    "+" "+" "+"        "+"    "+"\r");
			}	
			if(d==5){
				Thread.sleep(500);
				System.out.print("     "+" "+"  "+"0"+" 0 0 0 "+"0"+" 0       0 "+"  "+"  "+" 0"+"             0              "+"\r");
			}
			if(d==6){
				Thread.sleep(500);
				System.out.print("     "+"   "+"0"+"    0  "+" "+" 0          "+"  "+"0 "+"0 0 0 0 "+"\r");
			}
			if(d==7){
				Thread.sleep(500);
				System.out.println("        "+"0"+" 0 0 0 0 0          0 0   0     0");
			}
			if(d==7){
				Thread.sleep(500);
				System.out.println("        "+"0"+"    0    0           0     0   0");
			}
			if(d==7){
				Thread.sleep(500);
				System.out.println("        "+"0"+" 0 0 0 0 0          0 0 0 0 0 0 ");
			}
			if(d==8){
				Thread.sleep(500);
				System.out.println("        "+" "+"  0 0                           0");
			}
			if(d==9){
				Thread.sleep(500);
					System.out.println("        "+" "+"0     0               0 0 0 0  0");
			}
			if(d==9){
				System.out.println("        "+"0"+"       0                   0 0 ");
			}
			}
		
	}
}