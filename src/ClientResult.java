import java.io.*;


public class ClientResult implements Serializable {
	
	public int nonce = 0;
	
	public ClientResult(int nonce) {
		
		this.nonce = nonce;
	}
}
