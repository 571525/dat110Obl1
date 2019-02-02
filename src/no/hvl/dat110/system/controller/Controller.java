package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller {

	private static int N = 5;

	public static void main(String[] args) {

		Display display;
		Sensor sensor;

		RPCClient displayclient, sensorclient;

		System.out.println("Controller starting ...");

		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();

		displayclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST, Common.SENSORPORT);

		// TODO
		// create display and sensor object
		// create RPC clients for display device and sensor device 
		// register RPC methods in the RPC layer

		// Display and sensor object
		display = new Display();
		sensor = new Sensor();

		// create/connect rpc clients
		displayclient.connect();
		sensorclient.connect();

		// register methods i rpc layer
		displayclient.register(display);
		sensorclient.register(sensor);

		// register stop methods in the RPC middleware
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);

		// TODO:
		// loop while reading from sensor and write to display via RPC
		int i = 0;
		while (i < N) {
			int temp = sensor.read();
			display.write(temp + "");

			try { // For å vente litt med neste måling.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			i++;
		}

		stopdisplay.stop();
		stopsensor.stop();

		displayclient.disconnect();
		sensorclient.disconnect();

		System.out.println("Controller stopping ...");

	}
}
