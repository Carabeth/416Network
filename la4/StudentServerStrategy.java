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
		ssthresh = cwnd/2;
		if (fastRetransmit == true) {
			cwnd = cwnd/2;
			slowStart = false;
			congestionAvoidance = true;
			fastRetransmit = false;
			timeout = false;
		}
		else if (timeout == true) {
			cwnd = 1;
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
