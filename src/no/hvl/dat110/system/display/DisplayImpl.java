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
		
		String message = write();	//Skjønner fortsatt ikke helt hvordan vi skal "calle" write
									//Er ingen String vi kan bruke
		
		rpcid = request[0];
		
		reply = RPCUtils.marshallString(rpcid, str);
		
		
		return reply;
	}
}
