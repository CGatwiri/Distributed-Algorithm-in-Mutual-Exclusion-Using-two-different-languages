package argawalaalgorithm;
import java.io.*;
import java.net.*;
/**
 * P15/1714/2017
 * Gatwiri Christine
 */
public class node1 
{
    /*This is assumed to be the main node,which is requesting to enter the critical section
    Other nodes will receive its message and consider whether to allow it or not*/
    
    /*This project is run by first running all other node and then this one lastly, since it acts 
    as the client while others act as the servers*/
    public static void main(String[] args) throws Exception 
    {
        try
        {
            /*This is a list of all the ports of the other processes. This node loops throgh all of them, 
            sending the message and receiving feeddback*/
            int processIDs[] = {9002,9003,9004,9005};
            //create the socket
            for (int i = 0; i < processIDs.length; i++)
            {
                Socket socketClient= new Socket("localhost",processIDs[i]);  

                System.out.println("\nConnected to Process "+processIDs[i]);
                int Process_ID = 101;
                /*states == 0--> not in the critical section and not interested
                         == 1--> not in the critical section but interested
                         == 2--> in the critical section
                */
                int state = 0;
                int timestamp = 8;    
                String Cs = "add()";
                int PortNum = 9001;
                String idstring = Integer.toString(Process_ID);
                String timestampstring = Integer.toString(timestamp);

                //input stream to receive response from the server side
                BufferedReader server_response = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

                //output stream to send information to the server side
                BufferedWriter client_output= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                DataOutputStream outToServer = new DataOutputStream(socketClient.getOutputStream()); 
                outToServer.writeBytes("Hey there");
                outToServer.writeBytes("Critical Section: " + Cs + "\n");
                outToServer.writeBytes("ProcessID: " + Process_ID + "\n");
                outToServer.writeBytes("Time stamp: " + timestampstring + "\n");
                client_output.write(Cs);
                client_output.write(Process_ID);
                client_output.write(timestampstring);
                client_output.flush();

                String resultMsg;
                while((resultMsg = server_response.readLine()) != null)
                {
                    System.out.println("" + resultMsg);  
                }
                /*
                outToServer.writeBytes(timestampstring);
                client_output.write(timestampstring);
                client_output.flush();
                */
                try
                {
                    //close the input and output stream
                    server_response.close();
                    client_output.close();
                    //close the socket
                    socketClient.close();
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

