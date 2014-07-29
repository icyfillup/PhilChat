package com.icyfillup.philchat.sever;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Sever implements Runnable
{
	private DatagramSocket	socket;
	private int				port;
	private boolean			running	= false;
	private Thread			run, manage, send, receive;
	
	public Sever(int port)
	{
		this.port = port;
		try
		{
			socket = new DatagramSocket(port);
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		run = new Thread(this, "sever");
	}
	
	public void run()
	{
		running = true;
		manageClients();
		receive();
	}
	
	private void manageClients()
	{
		manage = new Thread("Manage")
		{
			public void run()
			{
				while (running)
				{	
					
				}
			}
		};
		manage.start();
	}
	
	private void receive()
	{
		receive = new Thread("Receive")
		{
			public void run()
			{
				while (running)
				{	
					
				}
			}
		};
		receive.start();
	}
	
}
