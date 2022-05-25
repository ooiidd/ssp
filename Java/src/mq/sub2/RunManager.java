package mq.sub2;

import java.util.Scanner;

public class RunManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		MQ mq = MQ.getInstance();
		while(scan.hasNext()){
			String line = scan.nextLine();
			String[] strArr = line.split(" ");
			if(strArr[0].equals("SEND")){
				mq.sendQueue(strArr[1],strArr[2]);
			}
			else if(strArr[0].equals("CREATE")){
				mq.createQueue(strArr[1], Integer.parseInt(strArr[2]));
			}
			else if(strArr[0].equals("RECEIVE")){
				Object obj = mq.receiveQueue(strArr[1]);
				if(obj != null)
					System.out.println(obj);
			}
		}
	}

}
