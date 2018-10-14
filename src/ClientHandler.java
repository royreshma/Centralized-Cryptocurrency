import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

class ClientHandler extends Thread  
{ 
	Socket client;
	ObjectOutputStream serverOutputChallenge;
	ObjectInputStream serverInputResult;
	Block challenge;
	int difficulty;
	ArrayList<Client> ClientData;
	int id;
	long timestamp;
  
    // Constructor 
    public ClientHandler(Socket client, ObjectOutputStream serverOutputChallenge, ObjectInputStream serverInputResult, Block challenge, int difficulty, ArrayList<Client> ClientData, int id, long timestamp)  
    { 
        this.client = client;
        this.serverOutputChallenge = serverOutputChallenge;
        this.serverInputResult = serverInputResult;
        this.challenge = challenge;
        this.difficulty = difficulty;
        this.ClientData = ClientData;
        this.id = id;
        this.timestamp = timestamp;
    } 
  
    @Override
    public void run()  
    { 
    	int i = id;
    
        try { 
            	
           	serverOutputChallenge.writeObject(challenge);
           	serverOutputChallenge.flush();
            
            ClientResult Result = (ClientResult)serverInputResult.readObject();
            System.out.printf("\nReceived Data from Client..%d..", i);
            ClientData.get(id).nonce = Result.nonce;
            ClientData.get(id).timestamp = new Date().getTime();
            System.out.printf("\nclient %d thread exitting", i);
               
        } catch (Exception e) { 
                	
           	e.printStackTrace();          
          } 
    } 
} 