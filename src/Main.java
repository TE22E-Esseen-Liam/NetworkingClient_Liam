import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        int portnumber = 6060; // Uppdaterat portnummer

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your message. Type 'Bye' to exit.");

        while (true) {
            try (Socket client = new Socket(InetAddress.getLocalHost(), portnumber);
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                System.out.print("You: ");
                String msg = stdIn.readLine();

                if (msg == null || msg.equalsIgnoreCase("Bye")) {
                    System.out.println("Closing client.");
                    break;
                }

                out.println(msg);
                String response = in.readLine();
                System.out.println("Server: " + response);

            } catch (IOException e) {
                System.out.println("I/O error: " + e.getMessage());
                break;
            }
        }
    }
}