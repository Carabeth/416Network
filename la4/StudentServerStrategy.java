import java.util.*;

public class StudentServerStrategy implements ServerStrategy{
    List<String> file;
	boolean[] acks;
	boolean slowStart = true;
	boolean congestionAvoidance = false;
	boolean timeout = false;
	boolean fastRetransmit = false;
	int cwnd = 1;
	int ssthresh = 8;
	

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
		}
		int firstUnACKed = 0;
		
		List<Message> msgs = new ArrayList<Message>();
		
		//Sends the number of packets depending on the congestion window
		while (firstUnACKed < acks.length && acks[firstUnACKed]) {
			for (int i = 0; i < cwnd; ++i) {
				if(firstUnACKed < acks.length) {
					msgs.add(new Message(firstUnACKed,file.get(firstUnACKed)));
				}
				firstUnACKed++;
			}
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
			slowStart = true;
			congestionAvoidance = true;
			ssthresh = cwnd/2;
			cwnd /= 2;
		}
		else if (timeout) {
			slowStart = true;
			congestionAvoidance = false;
			ssthresh = cwnd/2;
			cwnd = 1;
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
