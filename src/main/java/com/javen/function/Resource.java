package com.javen.function;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 资源处理类
 * 
 * @author xuzengqiang
 * @since 2014-09-26
 */
public class Resource {

	// 释放资源InputStream
	public static void destoryResource(InputStream input) {
		try {
			if (input != null) {
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 释放资源OutputStream
	public static void destoryResource(FileOutputStream fos) {
		try {
			if (fos != null) {
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}