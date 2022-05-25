package mq.sub1;

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
				mq.sendQueue(strArr[1]);
			}
			else if(strArr[0].equals("RECEIVE")){
				Object obj = mq.receiveQueue();
				if(obj != null)
					System.out.println(obj);
			}
		}
	}

}
