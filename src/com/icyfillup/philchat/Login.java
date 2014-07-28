package com.icyfillup.philchat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame
{
	
	private JPanel		contentPane;
	private JTextField	txtName;
	private JTextField txtAddress;
	private JTextField txtPort;
	private JLabel lbleg;
	private JLabel lbleg_1;
	
	public Login()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
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
		txtName.setText("bob");
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
		
		lbleg = new JLabel("(eg. 123.158.5.0) ");
		lbleg.setBounds(84, 77, 96, 14);
		contentPane.add(lbleg);
		
		lbleg_1 = new JLabel("(eg. 1511)");
		lbleg_1.setBounds(84, 116, 69, 14);
		contentPane.add(lbleg_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(102, 163, 89, 23);
		contentPane.add(btnLogin);
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
