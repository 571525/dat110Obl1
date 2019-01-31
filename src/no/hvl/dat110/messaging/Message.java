package no.hvl.dat110.messaging;

import java.util.Arrays;
import java.util.stream.*;

public class Message {

	private byte[] payload;

	/**
	 * Checks if the payload is longer than 127 bytes, if it is it cuts all bytes
	 * from 128 and forward. 
	 * 
	 * @param payload
	 */
	public Message(byte[] payload) {	
		if (payload.length >= MessageConfig.SEGMENTSIZE) {
			this.payload = new byte[127];
			for (int i = 0; i < this.payload.length; i++)
				this.payload[i] = payload[i];
		} else {
			this.payload = payload;
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload;
	}

	public byte[] encapsulate() {

		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];

		// TODO
		// encapulate/encode the payload of the message
		
		byte length = (byte) payload.length;
		for(int i = payload.length; i > 0; i--)
			encoded[i]=payload[i-1];
		encoded[0] = length;
		
		return encoded;

	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate data in received and put in payload
		int length = received[0];		
		if (length > 127) return;
		
		payload = new byte[length];
		for(int i = 0; i < length; i++) 
			payload[i] = received[i+1];
	}
}
