package com.lgcns.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunManager2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		for(int i=0;i<args.length;i++) {
			System.out.println(args[i]);
		}
		
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
