package com.icyfillup.philchat;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame
{
	
	private JPanel		contentPane;
	private JTextField	txtName;
	private JTextField	txtAddress;
	private JTextField	txtPort;
	private JLabel		lblAddressDesc;
	private JLabel		lblPortDesc;
	
	public Login()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(84, 20, 126, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(33, 23, 46, 14);
		contentPane.add(lblName);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(84, 57, 165, 20);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setBounds(10, 60, 69, 14);
		contentPane.add(lblIpAddress);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(33, 101, 33, 14);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(84, 98, 86, 20);
		contentPane.add(txtPort);
		
		lblAddressDesc = new JLabel("(eg. 123.158.5.0) ");
		lblAddressDesc.setBounds(84, 77, 96, 14);
		contentPane.add(lblAddressDesc);
		
		lblPortDesc = new JLabel("(eg. 1511)");
		lblPortDesc.setBounds(84, 116, 69, 14);
		contentPane.add(lblPortDesc);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = txtName.getText();
				String address = txtAddress.getText();
				int port = Integer.parseInt(txtPort.getText());
				login(name, address, port);
			}
		});
		btnLogin.setBounds(102, 163, 89, 23);
		contentPane.add(btnLogin);
	}
	
	private void login(String name, String address, int port)
	{
		dispose();
		new Client(name, address, port);
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Login frame = new Login();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
