/**
 * Program Name: Update.java
 * Purpose: A Program so that Billy Bee can Update the fullname and price data
 * Coder: Ken Bonvanie 0598780
 * Date: 2013-10-17
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class Update extends JFrame
{
	
	public JButton btnSearch, btnUpdate;
	public JTable table;
	public JLabel lblSize, lblMatType, lblfitting, lblTwoSize, lblTwoMatType, lblTwoFitting, lblThreeFitting, lblPrice, lblStepOne, lblStepTwo, lblThreeSize, lblSearch;
	public JTextField txtPrice, lblThreeMatType, txtSearch;
	boolean cb1 = true, cb2 = true, cb3 = true;
	Vector colNames = new Vector();
	Vector dataVector = new Vector();
	Vector row = new Vector();
	Border blackLine;
	public JPanel one, two, three, four, five, six, search;
	
	static final String DATABASE_URL = "jdbc:mysql://localhost/materialpricelist";

	String user = "user";	
	String password = "password";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rslt = null;
	private static ResultSet size = null;
	private static ResultSet matType = null;
	private static ResultSet fittingType = null;
	
	public Update()
	{
		super("Update a Plumbing Part's Price");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1200,550);
		this.setLocationRelativeTo(null);
		
		this.one = new JPanel();
		this.two = new JPanel();
		this.three = new JPanel();
		this.four = new JPanel();
		this.five = new JPanel();
		this.six = new JPanel();
		this.search = new JPanel();
		
		// three
		this.btnSearch = new JButton("Search");
		this.btnUpdate = new JButton("Update");
		this.lblStepOne = new JLabel("Step One:");
		this.lblStepTwo = new JLabel("Step Two:");
		
		
		// six
		this.lblTwoSize = new JLabel("Material ID:");
		this.lblTwoMatType = new JLabel("MatType:");
		this.lblTwoFitting = new JLabel("Fitting:");
		this.lblPrice = new JLabel("Price:");

		this.lblThreeSize = new JLabel();
		this.lblThreeMatType = new JTextField("");
		this.lblThreeFitting = new JLabel();
		this.txtPrice = new JTextField("");
		

		this.setLayout(new GridLayout(1,2,0,0));
		one.setLayout(new BorderLayout());
		two.setLayout(new BorderLayout());
		four.setLayout(new BorderLayout());
		five.setLayout(new BorderLayout());
		six.setLayout(new GridLayout(3,2,10,10));
		search.setLayout(new GridLayout(1,2,0,0));
		
		one.add(lblStepOne, BorderLayout.NORTH);
		two.add(lblStepTwo, BorderLayout.NORTH);

		
		
		two.add(five);
		five.add(six, BorderLayout.CENTER);
		
		six.add(lblTwoSize);
		
		five.add(btnUpdate, BorderLayout.SOUTH);
		
		this.add(one);
		this.add(two);
		
		one.add(four, BorderLayout.CENTER);
		one.add(three, BorderLayout.SOUTH);
		
		four.add(three, BorderLayout.SOUTH);
		one.add(search, BorderLayout.SOUTH);
		
		six.add(lblTwoSize);
		six.add(lblThreeSize);
		
		six.add(lblTwoMatType);
		six.add(lblThreeMatType);
		
		six.add(lblPrice);
		six.add(txtPrice);
		
		this.lblSearch = new JLabel("Search: ");
		this.txtSearch = new JTextField();
		
		search.add(lblSearch);
		search.add(txtSearch);
		txtSearch.getDocument().addDocumentListener(new DocumentHandler());
		txtSearch.addKeyListener(new KeyHandler());
		
		// table info
		populateTable();
		table = new JTable(dataVector,colNames);
		JScrollPane scrollPane = new JScrollPane(table);
		four.add(scrollPane, BorderLayout.CENTER);

		btnSearch.setBackground(Color.LIGHT_GRAY);		
		btnSearch.addMouseListener(new MouseHandler());
		btnSearch.addActionListener(new ButtonHandler());
		
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.addActionListener(new ButtonHandler());
		btnUpdate.addMouseListener(new MouseHandler());
		
		table.addMouseListener(new MouseHandler());
		
		table.setRowHeight(4, 4);
		table.setFont (new Font("Arial", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font(null, Font.BOLD, 28));
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);

		table.setSize (table.getPreferredSize ());//otherwise you get an empty box because there is no size
		table.setRowHeight(30);
		
		// Set up Fonts
		// label
		setUpFonts(lblTwoSize);
		setUpFonts(lblTwoMatType);
		setUpFonts(lblTwoFitting);
		setUpFonts(lblPrice);
		setUpFonts(lblStepOne);
		setUpFonts(lblStepTwo);
		setUpFonts(lblSearch);
		setUpFonts(txtSearch);
		
		blackLine = BorderFactory.createLineBorder(Color.black);
		
		lblThreeSize.setBorder(blackLine);
		lblThreeMatType.setBorder(blackLine);
		lblThreeFitting.setBorder(blackLine);
		
		lblStepOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblStepTwo.setHorizontalAlignment(SwingConstants.CENTER);

		
		// text
		setUpFonts(lblThreeSize);
		setUpFonts(lblThreeMatType);
		setUpFonts(lblThreeFitting);
		setUpFonts(txtPrice);
		setUpFonts(btnUpdate);
		
		this.setVisible(true);
	}
	
	
	public class DocumentHandler implements DocumentListener
	{

		@Override
		public void changedUpdate(DocumentEvent arg0)
		{
			// TODO Auto-generated method stub
			Search();
		}

		@Override
		public void insertUpdate(DocumentEvent arg0)
		{
			// TODO Auto-generated method stub
			Search();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0)
		{
			// TODO Auto-generated method stub
			Search();
		}

		
	}
	
	public class KeyHandler implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
			Search();
		}

		@Override
		public void keyReleased(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == btnUpdate)
			{
			  int n = JOptionPane.showConfirmDialog(
			  		Update.this, "Are you sure you want to update this row?",
						"User Confirmation", JOptionPane.YES_NO_OPTION);
				
				if ( n == JOptionPane.YES_OPTION)
				{
					
					try
					{
						conn = DriverManager.getConnection(DATABASE_URL, user, password);
						
						//create statement
						stmt = conn.createStatement();
						stmt.executeUpdate("update material set price = '" + txtPrice.getText() + "' , fullname = '" + lblThreeMatType.getText()  + "' where MaterialID = '" + lblThreeSize.getText() + "';");
						
						table.setValueAt(txtPrice.getText(), table.getSelectedRow(), 2);
						table.setValueAt(lblThreeMatType.getText(), table.getSelectedRow(), 1);
						
						JOptionPane.showMessageDialog(null, "Row was updated successfully!");
					}
					catch(SQLException ex)
					{
						System.out.print("SQLException has occured and message is: ");
						System.out.println(ex.getMessage());
					}
					//catch all block
					catch(Exception ex)
					{
						System.out.print("Some other exception occured, caught by catch-all...");
						System.out.println(ex.getMessage());
					}

					finally
					{
						// Step 6 - Close the ResultSet (if used)
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
						catch (SQLException e1)
						{
							e1.printStackTrace();
						}
					}//end finally 
				}
			}
		}
	}
	public class MouseHandler implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource() == table)
			{
				// poplutate the text fields with these values.
				lblThreeSize.setText((String) table.getValueAt(table.getSelectedRow(), 0 ));
				lblThreeMatType.setText((String) table.getValueAt(table.getSelectedRow(), 1 ));
				txtPrice.setText((String) table.getValueAt(table.getSelectedRow(), 2 ));				
			}
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
			if(e.getSource() == btnSearch)
			{
				btnSearch.setBackground(Color.CYAN);
			}
			else if(e.getSource() == btnUpdate)
			{
				btnUpdate.setBackground(Color.CYAN);
			}
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource() == btnSearch)
			{
				btnSearch.setBackground(Color.LIGHT_GRAY);
			}
			else if(e.getSource() == btnUpdate)
			{
				btnUpdate.setBackground(Color.LIGHT_GRAY);
			}
			
		}
		
	}
	
	void populateTable()
	{
			try
			{
				//establish connection to db
				conn = DriverManager.getConnection(DATABASE_URL, user, password);
				
				//create statement
				stmt = conn.createStatement();
				
				// if all are ALL
				rslt = stmt.executeQuery("select materialid, fullname, price from material order by fullname;");
				
				ResultSetMetaData metaData = rslt.getMetaData(); //gets metadata from
		    //the recordset returned
		
				int numberOfColumns = metaData.getColumnCount();
				
				//loop to output column names as headers
				for(int i = 1; i <= numberOfColumns; i++)
				{
					colNames.add( metaData.getColumnName(i) );
				} //end for
		
				
				while(rslt.next() )
				{
					row = new Vector();
					for(int i = 1; i <= numberOfColumns; i++)
					{
						row.add(rslt.getString(i) );
					}//end for
					dataVector.add(row);
					
				}//end while			
	
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			//catch all block
			catch(Exception ex)
			{
				System.out.print("Some other exception occured, caught by catch-all...");
				System.out.println(ex.getMessage());
			}
			finally
			{
				// Step 6 - Close the ResultSet (if used)
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
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}//end finally
	}
	
	
	void setUpFonts(JLabel label)
	{
		label.setSize(300, 30);
		Font oldFont = label.getFont();
		Font newFont = new Font( oldFont.getFontName(), Font.BOLD, 18 );
		label.setFont( newFont );
	}	
	
	void setUpFonts(JButton label)
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
	
	
	void Search()
	{
		try
		{
	    while (table.getRowCount() > 0) 
	    {
	      ((DefaultTableModel) table.getModel()).removeRow(0);
	      
	    }
			//establish connection to db
			conn = DriverManager.getConnection(DATABASE_URL, user, password);
			
			//create statement
			stmt = conn.createStatement();
			
			rslt = stmt.executeQuery("select materialid, fullname, price from material  where fullname like(" + "\"" + txtSearch.getText() + "%\") order by fullname;");
			System.out.print(txtSearch.getText());
			ResultSetMetaData metaData = rslt.getMetaData(); //gets metadata from
	        //the recordset returned
	
			int numberOfColumns = metaData.getColumnCount();
			
			//loop to output column names as headers
			for(int i = 1; i <= numberOfColumns; i++)
			{
				colNames.add( metaData.getColumnName(i) );
			} //end for
	
			
			while(rslt.next() )
			{
				row = new Vector();
				for(int i = 1; i <= numberOfColumns; i++)
				{
					row.add(rslt.getString(i) );
				}//end for
				dataVector.add(row);
				
			}//end while			

		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		//catch all block
		catch(Exception ex)
		{
			System.out.print("Some other exception occured, caught by catch-all...");
			System.out.println(ex.getMessage());
		}
		finally
		{
			// Step 6 - Close the ResultSet (if used)
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
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}//end finally
	}
	
	public static void main(String[] args)
	{
		Update ug = new Update();
	}
}
