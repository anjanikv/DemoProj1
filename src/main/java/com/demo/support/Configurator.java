package com.demo.support;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configurator {
	static Properties props = new Properties();
	String strFileName;
	String strValue;

	public Configurator(String strFileName) {
		this.strFileName = strFileName;
	}
	
	public String getProperty(String strKey) {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				strValue = props.getProperty(strKey);
				in.close();
			} else
				System.out.println("File not found!");
		} catch (Exception e) {
			System.out.println(e);
		}
		return strValue;
	}
}
