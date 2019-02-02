package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {

		// TODO
		// implement marshalling, call and unmarshalling for write RPC method
		RPCClient display = this.rmiclient;
		byte[] str = RPCUtils.marshallString(RPCID, message);
		byte[] reply = display.call(str);
		String replystr = RPCUtils.unmarshallString(reply);
		System.out.println("Controller: " + replystr);
	}
}
