package argawalaalgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * P15/1714/2017
 * Gatwiri Christine
 */
public class node3 
{  
   //port it will be accessed by
   private static final int PORT = 9003;
   
   public static void main(String[] args) throws Exception 
   {
       try
       {
        //server socket
        ServerSocket mysocket = new ServerSocket(PORT);
        
        while(true)
         {
             //socket object to listen and accept connections from clients
            Socket clientListener = mysocket.accept();
            System.out.println("Connection with process 1 successful.");
            int Process_ID = 102;
            /*states == 0--> not in the critical section and not interested
                     == 1--> not in the critical section but interested
                     == 2--> in the critical section
            */
            int state = 0;
            int timestamp = 9;
            String Cs = "add()";
            int PortNum = 9002;
                     
            //input stream to receive response from the server side
            BufferedReader client_response = new BufferedReader(new InputStreamReader(clientListener.getInputStream()));
            
            //output stream to send information to the client side
            BufferedWriter server_output= new BufferedWriter(new OutputStreamWriter(clientListener.getOutputStream()));
                       
            String cs = client_response.readLine();
            System.out.println("Process 1: " + cs);
            String requesting_process = client_response.readLine();
            System.out.println("Process 1: " + requesting_process);
            String time = client_response.readLine();
            System.out.println("Process 1: " + time);
                        
            if(state==0)
            {
                server_output.write("OK,not interested\n");
                //whatever is in the buffer is sent out
                server_output.flush();
            }
            else if(state == 1)
            {
                server_output.write("Process 3 is also interested,comparing timestamps... \n\n");
                server_output.flush();
                
                //String timed = client_response.readLine();
                
                int timey = 8;
                //int timey = Integer.parseInt(timed);
                //double timedouble = Double.parseDouble(timed);
                if(timey > timestamp)
                {
                    server_output.write("You have a greater timestamp,therefore I have entered the CS,you have been queued \n\n");
                }
                else
                {
                    server_output.write("I have a greater timestamp,you can enter Critical Section \n\n");
                }
                
            }
            else if(state == 2)
            {
                server_output.write("In critical state... \n");
                server_output.flush();
            }
            server_output.flush();
            try
            {
                //close the input and output stream
                client_response.close();
                server_output.close();
                clientListener.close();
                //close the socket
                mysocket.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
 
         }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      }
}

