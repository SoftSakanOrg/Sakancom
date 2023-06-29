package mainpkg;

import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Sakan {

	JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private ButtonGroup bg= new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sakan window = new Sakan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sakan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 800, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 10, 761, 565);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(270, 0, 493, 570);
		panel_1.setBackground(SystemColor.activeCaption);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(216, 140, 132, 19);
		panel_1.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(119, 138, 78, 19);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(119, 167, 78, 26);
		panel_1.add(lblNewLabel_1);
		
		final JRadioButton adminRadio = new JRadioButton("Admin");
		adminRadio.setSelected(true);
		adminRadio.setForeground(SystemColor.inactiveCaptionBorder);
		adminRadio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adminRadio.setBackground(SystemColor.inactiveCaptionBorder);
		adminRadio.setBounds(169, 240, 67, 21);
		adminRadio.setOpaque(false);
		panel_1.add(adminRadio);
		
		final JRadioButton ownerRadio = new JRadioButton("Owner");
		ownerRadio.setForeground(SystemColor.inactiveCaptionBorder);
		ownerRadio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ownerRadio.setBackground(SystemColor.activeCaption);
		ownerRadio.setBounds(238, 240, 67, 21);
		ownerRadio.setOpaque(false);
		panel_1.add(ownerRadio);
		
		final JRadioButton tenantRadio = new JRadioButton("Tenant");
		tenantRadio.setForeground(SystemColor.inactiveCaptionBorder);
		tenantRadio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tenantRadio.setBackground(SystemColor.activeCaption);
		tenantRadio.setBounds(307, 240, 78, 21);
		tenantRadio.setOpaque(false);
		panel_1.add(tenantRadio);
		
		bg.add(adminRadio);
		bg.add(ownerRadio);
		bg.add(tenantRadio);
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username=usernameField.getText();
				String password=passwordField.getText();
				
				if(username.equals("admin")&&password.equals("a123")&&adminRadio.isSelected()) {
					JOptionPane.showMessageDialog(null, "Login Successful");
					frame.dispose();  //hide first scene
					admin AdminInfo=new admin();
					AdminInfo.setVisible(true);
				}	
				else if(username.equals("admin")&&password.equals("a123")&&ownerRadio.isSelected()) {
					JOptionPane.showMessageDialog(null, "Login Successful");
					frame.dispose();  //hide first scene
					owner OwnerInfo=new owner(); 
					OwnerInfo.setVisible(true);
				}
				
				else if(username.equals("admin")&&password.equals("a123")&&tenantRadio.isSelected()) {
					JOptionPane.showMessageDialog(null, "Login Successful");
					frame.dispose();  //hide first scene
					tenant TenantInfo=new tenant(); 
					TenantInfo.setVisible(true);
				}
				
				
				
				else {
					JOptionPane.showMessageDialog(null, "Invalid username or password");
				}
			}
		});
		btnNewButton.setBounds(232, 291, 85, 21);
		panel_1.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 169, 132, 19);
		panel_1.add(passwordField);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setBounds(0, 0, 494, 568);
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/Space.jpg"));
		imageLabel.setIcon(icon);
		panel_1.add(imageLabel);
		
		JLabel imageLabel2 = new JLabel("");
		imageLabel2.setBounds(0, 0, 273, 570);
		ImageIcon icon2=new ImageIcon(this.getClass().getResource("/Space.jpg"));
		imageLabel2.setIcon(icon2);
		panel.add(imageLabel2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 269, 565);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
	}

}
