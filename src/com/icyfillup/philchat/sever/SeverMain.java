package com.icyfillup.philchat.sever;

public class SeverMain
{
	private int		port;
	private Sever	sever;
	
	public SeverMain(int port)
	{
		this.port = port;
		sever = new Sever(port);
	}
	
	public static void main(String[] args)
	{
		int port;
		if (args.length != 1)
		{
			System.out.println("Usage: java -jar PhilChatSever.jar [port]");
			return;
		}
		port = Integer.parseInt(args[0]);
		new SeverMain(port);
	}
}
