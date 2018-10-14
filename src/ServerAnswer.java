import java.io.*;

public class ServerAnswer implements Serializable{
	
	public int status;
	public Boolean nextChallenge;
	
	public ServerAnswer(int status, Boolean nextChallenge) {
		
		this.status = status;
		this.nextChallenge = nextChallenge;
	}

}
