package cop2805;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import javax.swing.*;

import javax.swing.JButton;

import javax.swing.JList;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {
	
	private static JButton btn;
	private static JList<String> list;
	private JPanel contentPane;
	private static JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setResizable(false);
		
		// JFrame design
		
		setTitle("LineSearcher - Diego Cordero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter line # :");
		lblNewLabel.setFont(UIManager.getFont("CheckBoxMenuItem.font"));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 13, 107, 19);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(UIManager.getFont("CheckBoxMenuItem.font"));
		textField.setBounds(88, 10, 165, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setBackground(SystemColor.menu);
		list.setBounds(10, 95, 366, 112);
		contentPane.add(list);
		
		btn = new JButton("Search");
		btn.setFont(UIManager.getFont("InternalFrame.titleFont"));
		btn.setBounds(275, 10, 101, 25);
		contentPane.add(btn);
		btn.addActionListener(new ActionListener() {
			
		

			@Override // Runs when button is clicked
			public void actionPerformed(ActionEvent x) {
				
				//clears jList
				model.clear();
				
				// Reads user text from JtextField
				String userChoice = textField.getText() + "\n";
				
				// Try to connect to Server
				try {
					//Socket conn = new Socket("ip",port); 
					OutputStream output = conn.getOutputStream();
					BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					output.write(userChoice.getBytes());
					
					
					String line;
                    while ((line = input.readLine()) != null) {
                    	if (line.trim().isEmpty()) {
                            // Adds blank lines to the list
                            model.addElement(" ");
                        } else {
                            // Adds normal lines to the list
                            model.addElement(line);
                        }
                    }
                    
                    //Refresh list and close the connection         
                    list.repaint();
                    conn.close();
                    
				} catch (IOException ioE) {
					ioE.printStackTrace();
				}
				
			}
			
		});
	}
}