import java.util.*;

public class StudentServerStrategy implements ServerStrategy{
    List<String> file;
	boolean[] acks;
	boolean slowStart = true;
	boolean congestionAvoidance = false;
	boolean fastRetransmit = false;
	int cwnd = 1;
	int ssthresh = 8;
	int firstUnACKed = 0;
	int checkForDuplicateAck = 0;
	int counter = 1;
	

    public StudentServerStrategy(){
        reset();
    }

    public void setFile(List<String> file){
        this.file = file;
		acks = new boolean[file.size()];
    }

    public void reset() {
		
		
    }

    public List<Message> sendRcv(List<Message> clientMsgs){
		for (Message m: clientMsgs) {
			acks[m.num-1] = true;
			System.out.println(m.num+","+m.msg);
			
			//Checks for triple duplicate ACK
			if (checkForDuplicateAck == m.num) {
				++counter;
			}
			else {
				counter = 1;
			}
			if (counter == 3) {
				fastRetransmit = true;
				counter = 1;
			}
			checkForDuplicateAck = m.num;
		}
		
		List<Message> msgs = new ArrayList<Message>();
		
		//Sends the number of packets depending on the congestion window
		for (int i = 0; i < cwnd; ++i) {
			if(firstUnACKed < acks.length && acks[firstUnACKed] == false) {
				msgs.add(new Message(firstUnACKed,file.get(firstUnACKed)));
				acks[firstUnACKed] = true;
			}
			firstUnACKed++;
		}
		
		//Determines slowStart or congestion avoidance depending on threshold and congestion window
		if (cwnd >= ssthresh) {
			slowStart = false;
			congestionAvoidance = true;
		}
		else {
			slowStart = true;
			congestionAvoidance = false;
		}
		
		//Determines if timeout occured and how to handle it
		if (fastRetransmit) {
			System.out.println("Triple Duplicate ACK detected");
			msgs.add(new Message(checkForDuplicateAck,file.get(checkForDuplicateAck)));
			slowStart = false;
			congestionAvoidance = true;
			ssthresh = cwnd/2;
			cwnd /= 2;
			fastRetransmit = false;
		}
		
		//No timeout which means update cwnd
		else {
			if (slowStart) {
				cwnd *= 2;
			}
			else {
				cwnd += 1;
			}
		}
		
		return msgs;
    }
    
}
