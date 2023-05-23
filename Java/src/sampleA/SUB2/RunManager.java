package sampleA.SUB2;

import mq.FileReaderH;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RunManager {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] split = line.split(" ");
		System.out.println(getProxy(split[0],split[1]));
	}
	public static String getProxy(String str,String str2) throws IOException {
		List<String> strings = FileReaderH.readAll("src/sampleA/SUB2/"+str + ".txt");
//		System.out.println(str+" "+str2);
		for(String cur : strings){
			if(cur.startsWith(str2)) {
				if (cur.contains("Service-")) {
					return FileReaderH.readAll("src/sampleA/SUB2/"+cur.split("#")[1].split("\\.")[0]+".txt").get(0);
				} else {
					return getProxy(cur.split("#")[1].split("\\.")[0],str2);
				}
			}
		}
		return "";
	}

}
