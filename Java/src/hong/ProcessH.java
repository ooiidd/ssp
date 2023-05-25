package hong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessH {
	/**
	 * 
	 * @param processName 프로세스 이름 (경로 포함 가능)
	 * @param param 가변 매개변수
	 * @return 외부 프로그램에서 출력되는 값 String형
	 * @throws Exception
	 */
    public static String run(String processName,String...param) throws Exception{
        String ret = "";
        List<String> command = new ArrayList<>();
        command.add(processName);
        for(String str : param)
            command.add(str);
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(command);


        Process proc = pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line=br.readLine())!=null){
            ret+=line+"\n";
        }
        proc.waitFor();
        
        //실행파일에서 던져주는 return값
        int result = proc.exitValue();
        return ret;
    }
    
    /**
     * 
     * @param processName 프로세스 이름 (경로 포함 가능)
     * @param param 가변 매개변수
     * @return 외부 프로그램에서 던져준 return값
     * @throws Exception
     */
    public static int getReturnValue(String processName,String...param) throws Exception{
        List<String> command = new ArrayList<>();
        command.add(processName);
        for(String str : param)
            command.add(str);
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(command);


        Process proc = pb.start();

        //프로세스 끝날때 까지 기다려줌
        proc.waitFor();
        
        //실행파일에서 던져주는 return값
        int result = proc.exitValue();
        return result;
    }

    /*
    * String cmd = "java -classpath %classpath% com.lgcns.test.RunManager";
		System.out.println(getCmd("cd"));
		String classPath = getCmd("cd")+"\\bin";
		String libPath = getCmd("cd")+"\\lib";
		cmd = "java -classpath "+classPath+";"+libPath+" com.lgcns.test.RunManager2 ";
		cmd+="12";
		System.out.println(getCmd(cmd));
    * */
    public static String getCmd(String str) throws IOException {
        Process p = Runtime.getRuntime().exec("cmd.exe /c " + str);

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String l = null;
        StringBuffer sb = new StringBuffer();

        while ((l = r.readLine()) != null) {
            sb.append(l);
//		    sb.append("\n");
        }
        return sb.toString();
    }
}