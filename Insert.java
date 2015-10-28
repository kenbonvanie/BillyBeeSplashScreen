/**
 * Program Name: Insert.java
 * Purpose: Inserts a plumbing piece into a database
 * Coder: Ken Bonvanie 0598780
 * Date: 2013-10-15
 */

import javax.swing.*;

//import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Insert extends JFrame
{
	public JLabel lblSize, lblMatType, lblFitting, lblPrice, lblFullName;
	public JTextField txtSize, txtMatType, txtFitting, txtPrice, txtFullName;
	public JButton btnInsert, btnClearFields;
	public JPanel one, two;

	static final String DATABASE_URL = "jdbc:mysql://localhost/materialpricelist";
	
	String user = "user";
	String password = "password";
	
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rslt = null;
	
	String piece= "";
	
	public Insert(String string)
	{
		
		super("Insert A Plumbing Piece");

		piece = string;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800,300);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		this.one = new JPanel();
		this.two = new JPanel();

		one.setLayout(new GridLayout(2,2,0,0));
		two.setLayout(new GridLayout(1,2,0,0));


		this.add(one, BorderLayout.CENTER);
		this.add(two, BorderLayout.SOUTH);

		this.lblPrice = new JLabel("Price:");
		this.lblFullName = new JLabel("Full Name");

		this.txtPrice = new JTextField();
		this.txtFullName = new JTextField();

		this.btnInsert = new JButton("INSERT PIECE");
		this.btnClearFields = new JButton("CLEAR FIELDS");

		one.add(lblFullName);
		one.add(txtFullName);
		
		txtFullName.setText(piece);
		
		one.add(lblPrice);
		one.add(txtPrice);
		

		two.add(btnInsert);
		two.add(btnClearFields);

		// colors
		btnInsert.setBackground(Color.LIGHT_GRAY);
		btnClearFields.setBackground(Color.LIGHT_GRAY);

		// action listener
		btnInsert.addActionListener(new ButtonHandler());
		btnInsert.addMouseListener(new MouseHandler());

		// mouse listener
		btnClearFields.addActionListener(new ButtonHandler());
		btnClearFields.addMouseListener(new MouseHandler());


		// labels
		setUpFonts(lblPrice);
		setUpFonts(lblFullName);

		// text fields
		setUpFonts(txtPrice);
		setUpFonts(txtFullName);

		this.setVisible(true);
	}

	public class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == btnInsert)
			{
				if(txtFullName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Insert a Full Name");
				}
				else if(txtPrice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Insert a Price");
				}
				else
				{
					try
					{
						// establish connection to database
						conn = DriverManager.getConnection(DATABASE_URL, user, password);

						// create statement
						stmt = conn.createStatement();

						stmt.executeUpdate("insert into Material (FullName ,Price) values ( \"" + txtFullName.getText() + "\", \"" + txtPrice.getText()  + "\");");

						JOptionPane.showMessageDialog(null, "Insertion of the material was successful");

					}
					catch(SQLException ex)
					{
						JOptionPane.showMessageDialog(null, "SQLException has occured and message is: " + ex.getMessage());
					}
					finally
					{
						try{
							if(rslt != null)
								rslt.close();

							// Step 7 - Close the Statement object
							if(stmt != null)
								stmt.close();

							// Step 8 - Close the database connection
							if(conn != null)
								conn.close();

						}
						catch(SQLException e1)
						{
							e1.printStackTrace();
						}
					}

				}
			}
			else if(e.getSource() == btnClearFields)
			{
				txtPrice.setText("");
				txtFullName.setText("");
			}
		}
	}

	public class MouseHandler implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource() == btnInsert)
			{
				btnInsert.setBackground(Color.CYAN);
			}
			else if(e.getSource() == btnClearFields)
			{
				btnClearFields.setBackground(Color.CYAN);
			}
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource() == btnInsert)
			{
				btnInsert.setBackground(Color.LIGHT_GRAY);
			}
			else if(e.getSource() == btnClearFields)
			{
				btnClearFields.setBackground(Color.LIGHT_GRAY);
			}
		}

	}

	void setUpFonts(JLabel label)
	{
		label.setSize(300, 30);
		Font oldFont = label.getFont();
		Font newFont = new Font( oldFont.getFontName(), Font.BOLD, 18 );
		label.setFont( newFont );
	}

	void setUpFonts(JTextField text)
	{
		text.setSize(300, 30);
		Font oldFont = text.getFont();
		Font newFont = new Font( oldFont.getFontName(), Font.PLAIN, 18 );
		text.setFont( newFont );
	}
	public static void main(String[] args)
	{
		Insert i = new Insert("");
	}
}
