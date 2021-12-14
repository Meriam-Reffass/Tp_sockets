package reffass.meriam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class sockets_main extends Thread {
	int numCLient;
	 public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(234);
			while (true) 
	      {
	        Socket socket=serverSocket.accept();
	        System.out.println("connection etablie");
	       
	        numCLient++;
	        new echange(socket,numCLient).start();
	      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	 class echange extends Thread {
		 private Socket socket;
		 private int numc;
		 public echange(Socket socket,int num) {
			 super();
			 this.socket=socket;
			 this.numc=num;
		 }
		 public void run() {
			 InputStream is;
			try {
				is = socket.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				OutputStream outputStream = socket.getOutputStream();
		        PrintWriter printWriter = new PrintWriter(outputStream, true); 
				System.out.println("connection du clien numero:"+numc);
				printWriter.println("bienvenue vous etes le client numero"+numc);
				while(true) {
					String s = bufferedReader.readLine();
					if(s!=null) {
				    System.out.println(s);}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			 
		 }
		 
	 }
	 public static void main(String args[]) {
		 new sockets_main().start();
	 }
	}
