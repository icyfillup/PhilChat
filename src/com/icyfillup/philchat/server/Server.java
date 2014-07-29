package com.icyfillup.philchat.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable
{
	private List<ServerClient>	clients	= new ArrayList<ServerClient>();
	
	private DatagramSocket		socket;
	private int					port;
	private boolean				running	= false;
	private Thread				run, manage, send, receive;
	
	public Server(int port)
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
		run = new Thread(this, "Server");
		run.start();
	}
	
	public void run()
	{
		running = true;
		System.out.println("Server started on port " + port);
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
					byte[] data = new byte[1024];
					DatagramPacket packet = new DatagramPacket(data, data.length);
					try
					{
						socket.receive(packet);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					process(packet);
					String string = new String(packet.getData());
					clients.add(new ServerClient("phil", packet.getAddress(), packet.getPort(), 50));
					System.out.println(clients.get(0).address.toString() + ": " + clients.get(0).port);
				}
			}
		};
		receive.start();
	}
	
	private void sendToAll(String message)
	{
		for(int i = 0; i < clients.size(); i++)
		{
			ServerClient client = clients.get(i);
			send(message.getBytes(), clients.get(i).address, clients.get(i).port);
		}
	}
	
	private void send(final byte[] data, final InetAddress address, final int port)
	{
		send = new Thread("Send")
		{
			public void run()
			{
				DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
				try
				{
					socket.send(packet);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		};
		send.start();
	}
	
	private void process(DatagramPacket packet)
	{
		String string = new String(packet.getData());
		if (string.startsWith("/c/"))
		{
//			UUID id = UUID.randomUUID();
			int id = UniqueIdentifier.getIdentifier();
			System.out.println("id: " + id);
			clients.add(new ServerClient(string.substring(3, string.length()), packet.getAddress(), packet.getPort(), id));
			System.out.println("Sever.process_1. " + string.substring(3, string.length()));
		}
		else if(string.startsWith("/m/"))
		{
			sendToAll(string);
		}
		else
		{
			System.out.println("Sever.process_2. " + string);
		}
	}
	
	
	
	
	
	
	
	
	
	
}
