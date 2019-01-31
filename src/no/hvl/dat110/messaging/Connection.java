package no.hvl.dat110.messaging;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the TCP connection
	private DataInputStream inStream; // for reading bytes from the TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		// TODO - done
		// encapsulate the data contained in the message and write to the output stream
		try {
			DataOutputStream out = new DataOutputStream(outStream);
			out.write(message.encapsulate());
			out.flush();
			out.close();
		} catch (IOException ex) {
			System.out.println("Send: " + ex.getMessage());
		}
	}

	public Message receive() {
		// SEGMENTSIZE = 128 (128 bytes)
		Message message = new Message();
		byte[] recvbuf = new byte[MessageConfig.SEGMENTSIZE];

		// TODO - done
		// read a segment from the input stream and decapsulate into message
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			
			int i = 0;
			while (in.ready()) {
				byte next = (byte) in.read();
				if (next == -1)
					break; // Simple test to see if we've reached the end of the stream
				recvbuf[i] = next;
				i++;
			}
			in.close();
			message.decapsulate(recvbuf);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}