package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayImpl implements RPCImpl {

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] request) {
		
		byte rpcid;
		byte[] reply;
		
		// TODO: 
		// implement unmarshalling, call, and marshall for write RPC method
		// look at how this is done int he SensorImpl for the read method
		
		RPCUtils.unmarshallVoid(request);
		
		String message = new String(request);  //from bytes to String
		
		write(message);  //Kanskje noe sånt?
		     
		rpcid = request[0];
		
		reply = RPCUtils.marshallString(rpcid, message);
		
		
		return reply;
	}
}
