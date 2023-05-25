package com.lgcns.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunManager {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String cmd = "java -classpath %classpath% com.lgcns.test.RunManager";
		System.out.println(getCmd("cd"));
		String classPath = getCmd("cd")+"\\bin";
		String libPath = getCmd("cd")+"\\lib";
		cmd = "java -classpath "+classPath+";"+libPath+" com.lgcns.test.RunManager2 ";
		cmd+="12";
		System.out.println(getCmd(cmd));
//		Thread.sleep(10000);
		
	}
	
	public static String getCmd(String cmd) throws Exception {
		Process p = Runtime.getRuntime().exec("cmd.exe /c " + cmd);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String l = null;
		StringBuffer sb = new StringBuffer();
//		sb.append(cmd);

		while ((l = r.readLine()) != null) {
		    sb.append(l);
//		    sb.append("\n");
		}
//		System.out.println(sb);
		return sb.toString();
	}

}
