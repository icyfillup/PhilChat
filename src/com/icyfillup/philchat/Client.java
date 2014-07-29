package com.icyfillup.philchat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame
{
	private static final long	serialVersionUID	= 1L;
	
	private DatagramSocket		socket;
	
	private String				name, address;
	private int					port;
	private InetAddress			ip;
	
	private JPanel				contentPane;
	private JTextField			txtMessage;
	private JTextArea			history;
	
	private Thread				send;
	
	public Client(String name, String address, int port)
	{
		setTitle("Phil Chat Client");
		this.name = name;
		this.address = address;
		this.port = port;
		boolean connect = openConnection(address);
		if (!connect)
		{
			System.err.println("Connection Failed");
			console("Connection Failed");
		}
		createWindow();
		console("Attempting a connection to " + address + ", " + port + ". User: " + name);
		String connection = name + " connected from " + address + ": " + port;
		send(connection.getBytes());
	}
	
	private boolean openConnection(String address)
	{
		try
		{
			socket = new DatagramSocket();
			ip = InetAddress.getByName(address);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (SocketException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String receive()
	{
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try
		{
			socket.receive(packet);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String message = new String(packet.getData());
		return message;
	}
	
	private void send(final byte[] data)
	{
		send = new Thread("Send")
		{
			public void run()
			{
				DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
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
	
	private void createWindow()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 28, 815, 30, 7 };
		gbl_contentPane.rowHeights = new int[] { 35, 475, 40 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
		history = new JTextArea();
		history.setEditable(false);
		JScrollPane scroll = new JScrollPane(history);
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
		scrollConstraints.fill = GridBagConstraints.BOTH;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.insets = new Insets(0, 5, 0, 0);
		contentPane.add(scroll, scrollConstraints);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					send(txtMessage.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSent = new JButton("Sent");
		btnSent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				send(txtMessage.getText());
			}
		});
		GridBagConstraints gbc_btnSent = new GridBagConstraints();
		gbc_btnSent.insets = new Insets(0, 0, 0, 5);
		gbc_btnSent.gridx = 2;
		gbc_btnSent.gridy = 2;
		contentPane.add(btnSent, gbc_btnSent);
		
		setVisible(true);
		txtMessage.requestFocusInWindow();
	}
	
	private void send(String message)
	{
		if (message.equals("")) return;
		message = name + ": " + message;
		console(message);
		send(message.getBytes());
		txtMessage.setText("");
	}
	
	public void console(String message)
	{
		history.append(message + "\n\r");
		history.setCaretPosition(history.getDocument().getLength());
	}
}
