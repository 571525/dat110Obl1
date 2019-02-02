package no.hvl.dat110.rpc;

import java.util.Arrays;

import no.hvl.dat110.messaging.MessageConfig;

public class RPCUtils {

	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array

		byte[] strbyte = str.getBytes();
		encoded = new byte[strbyte.length + 1];
		encoded[0] = rpcid;
		for (int i = 0; i < strbyte.length; i++)
			encoded[i + 1] = strbyte[i];

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded = "";

		// TODO: unmarshall String contained in data into decoded

		for (int i = 1; i < data.length; i++)
			decoded += (char) data[i];

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// TODO: marshall RPC identifier in case of void type

		encoded = new byte[1];
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
		if (data != null) {
			for (int i = 1; i < data.length; i++) {
				data[i - 1] = data[i];
			}
		}
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array

		encoded = new byte[5];
		encoded[0] = rpcid;
		encoded[1] = (byte) ((x >> 24) & 0xff);
		encoded[2] = (byte) ((x >> 16) & 0xff);
		encoded[3] = (byte) ((x >> 8) & 0xff);
		encoded[4] = (byte) ((x >> 0) & 0xff);

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded = 0;

		// TODO: unmarshall integer contained in data

		decoded = (data[1] << 24) & 0xff000000 | (data[2] << 16) & 0x00ff0000 | (data[3] << 8) & 0x0000ff00
				| (data[4] << 0) & 0x000000ff;

		return decoded;

	}
}
