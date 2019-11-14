import java.util.*;

public class StudentServerStrategy implements ServerStrategy{
    List<String> file;
	boolean[] acks;
	boolean slowStart;
	boolean congestionAvoidance;
	boolean timeout;
	boolean fastRetransmit;
	int cwnd;
	int ssthresh;
	

    public StudentServerStrategy(){
        reset();
    }

    public void setFile(List<String> file){
        this.file = file;
    }

    public void reset(){
		if (fastRetransmit == true) {
			cwnd = cwnd/2;
			ssthresh = ssthresh/2;
			slowStart = true;
			congestionAvoidance = false;
			fastRetransmit = false;
			timeout = false;
		}
		else if (timeout == true) {
			cwnd = 1;
			ssthresh = ssthresh/2;
			slowStart = true;
			congestionAvoidance = false;
			fastRetransmit = false;
			timeout = false;
		}

    }

    public List<Message> sendRcv(List<Message> clientMsgs){
		for (Message m: clientMsgs) {
			acks[m.num-1] = true;
			System.out.println(m.num+","+m.msg);
		}
		int firstUnACKed = 0;
		
		List<Message> msgs = new ArrayList<Message>();
		
		while (firstUnACKed < acks.length && acks[firstUnACKed]) ++firstUnACKed;
		
		if(firstUnACKed < acks.length) {
			msgs.add(new Message(firstUnACKed,file.get(firstUnACKed)));
		}
		return msgs;
    }
    
}
