import java.util.*;

public class StudentServerStrategy implements ServerStrategy{
    List<String> file;
    boolean[] acks;
    int cwind;
    int ssthresh;
    int last;

    public StudentServerStrategy(){
        reset();
        this.cwind = 1;
        this.ssthresh =10;
        this.last = 0;
    }

    public void setFile(List<String> file){
    	this.file = file;	
        acks = new boolean[file.size()];
    }

    public void reset(){


    }

    public List<Message> sendRcv(List<Message> clientMsgs){

    	for(Message m: clientMsgs){
            acks[m.num] = true;
            System.out.println(m.num+","+m.msg);
        }
        List<Message> msgs = new ArrayList<Message>();
        //change the while loop to increase run times
	
        int size =0;
        for (int i = 0; i < acks.length && size != cwind; i++)
        {
        	if(acks[i]!=true)
        	{
        		//System.out.print(i);
		        msgs.add(new Message(i,file.get(i))); 
		        size++;
        	}
        }
        this.cwind += 1;
        return msgs;


    }
    
}
