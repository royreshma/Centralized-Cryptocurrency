import java.net.*;
import java.util.ArrayList;
import java.io.*;  

class MyClient{  
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	
	public static void main(String args[]){  
		
			try {
				
				Socket s=new Socket("localhost",3333); 
			    ObjectInputStream clientInputChallenge = new ObjectInputStream(s.getInputStream());
			    ObjectOutputStream clientOutputResult = new ObjectOutputStream(s.getOutputStream());
				
				blockchain = (ArrayList<Block>)clientInputChallenge.readObject();
				System.out.printf("Client..... Received Blockchain:");
				
				System.out.println(blockchain);
				
			    Block Challenge = (Block)clientInputChallenge.readObject();
			    
			    System.out.println("\nClient received Block...");
			    System.out.println("Client mining Block...");
			    int Nonce = Challenge.mineBlock(Challenge.difficulty);
			    
			    ClientResult Result = new ClientResult(Nonce);
			    
			    System.out.println("Block mined Sending information to server..."); 
			    
			    clientOutputResult.writeObject(Result);
			    	    
			    ServerAnswer Reply = (ServerAnswer)clientInputChallenge.readObject();
				
				if(Reply.status == 1) {
					
					System.out.println("\nYou have been awarded..");
					
				}else if(Reply.status == 2) {
					
					System.out.println("\nYour answer is INCORRECT..");
					
				}else if(Reply.status == 3){
					
					System.out.println("\nYou were little late for submission...");
					
				}else{
					
					System.out.println("\nYour answer is not submitted to the server... \nYou exceeded the provided time...");
				}
				
				if(Reply.nextChallenge) {
					
					System.out.println("Please reconnect after 30 seconds for next challenge..");
					
				}else {
					
					System.out.println("There are no more challenges \nThank You for staying with us...");
				}
				
				clientInputChallenge.close();
				clientOutputResult.close();
				System.out.println("Client exitting..");	
				s.close();
			        
			}
			catch(Exception e) {
				
				if(e.toString().equals("java.io.EOFException")) {
					
					System.out.println("Connection Refused\nOnly one connection per IP!!!");
					
				}else {
					
					e.printStackTrace();
				}
			}	
		
		}
	}	