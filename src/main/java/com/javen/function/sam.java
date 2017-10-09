package com.javen.function;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class sam {
	public static void main(String[] args) {
		String qiege="20170816-111-1-3333333333-0001.png";
		System.out.println(qiege.substring(0,qiege.indexOf("-")));
		System.out.println(qiege.substring(0,qiege.indexOf("-",qiege.indexOf("-")+1)));
		System.out.println(qiege.substring(0,qiege.indexOf("-",qiege.indexOf("-",qiege.indexOf("-")+1)+1)));
	}
	
	
}