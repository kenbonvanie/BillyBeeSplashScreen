/**
 * Program Name: ViewMaterial.java
 * Purpose:
 * Coder: Ken Bonvanie 0598780
 * Date: 2013-11-22
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class ViewMaterial extends JFrame
{
	public JButton btnClose, btnDelete;
	public JTable table;
	public JPanel bottom;
	Vector colNames = new Vector();
	Vector dataVector = new Vector();
	Vector row = new Vector();
	Vector<Material> y;

	ViewMaterial(Vector<Material> x)
	{
		super("VIEWING THE MATERIAL");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,800);
		this.setLocationRelativeTo(null);
		bottom = new JPanel();
		this.setLayout(new BorderLayout());
		bottom.setLayout(new GridLayout(2,1,0,5));
		btnClose = new JButton("Close");
		btnDelete = new JButton("Delete");

		btnDelete.addActionListener(new ButtonHandler());

		this.add(bottom, BorderLayout.SOUTH);
		bottom.add(btnDelete);
		bottom.add(btnClose);

		//populateTable();

		colNames.add( "Quantity" );
		colNames.add( "Part" );
		colNames.add( "Price" );


		for(int i = 0; i < x.size(); ++i)
		{
			row = new Vector();

			row.add(x.get(i).getQuantity());
			row.add(x.get(i).getPart());
			row.add(x.get(i).getPrice());

			dataVector.add(row);
		}



		table = new JTable(dataVector,colNames);
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setRowHeight(4, 4);
		table.setFont (new Font("Arial", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font(null, Font.BOLD, 28));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);

		
		
		
		table.setSize (table.getPreferredSize ());//otherwise you get an empty box because there is no size
		table.setRowHeight(30);
		
		btnClose.addMouseListener(new MouseHandler());
		btnDelete.addMouseListener(new MouseHandler());
		
		btnClose.addActionListener(new ButtonHandler());
		btnDelete.setBackground(Color.gray);
		btnClose.setBackground(Color.gray);

		
		// Set Up Fonts
		setUpFonts(btnDelete);
		setUpFonts(btnClose);
		this.y = x;
		this.add(scrollPane);
		this.setVisible(true);
	}

	public class ButtonHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource() == btnClose)
			{
				ViewMaterial.this.dispose();
			}
			else if(e.getSource() == btnDelete)
			{
				//try
				//{
				try
				{
					System.out.print(table.getValueAt(table.getSelectedRow(), 0) + " ");
					System.out.print(table.getValueAt(table.getSelectedRow(), 1) + " ");
					System.out.print(table.getValueAt(table.getSelectedRow(), 2));
					
					// deleting it from the table, now we need to delete it from the vector
		      
		      // Deleting it from the vector
		      for(int i = 0; i < y.size(); ++i)
		      {
		      	System.out.println(y.get(i).getQuantity() + " " + y.get(i).getPart() + " " + y.get(i).getPrice());
		      	if(y.get(i).getQuantity() == (int)table.getValueAt(table.getSelectedRow(), 0) && 
		      		 y.get(i).getPart() == table.getValueAt(table.getSelectedRow(), 1) &&
		      		 y.get(i).getPrice() == (double)table.getValueAt(table.getSelectedRow(), 2))
		      	{
			      	System.out.println("Removing: " + y.get(i).getQuantity() + " " + y.get(i).getPart() + " " + y.get(i).getPrice());
		      		y.remove(i);
				      ((DefaultTableModel) table.getModel()).removeRow(i);

		      		break;
		      	}
		      }
				}
				catch(ArrayIndexOutOfBoundsException lool)
				{
					JOptionPane.showMessageDialog(null,"Please select a row you would like to delete");
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
			if(e.getSource() == btnClose)
			{
				btnClose.setBackground(Color.cyan);
			}
			else if(e.getSource() == btnDelete)
			{
				btnDelete.setBackground(Color.cyan);
			}
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource() == btnClose)
			{
				btnClose.setBackground(Color.gray);
			}
			else if(e.getSource() == btnDelete)
			{
				btnDelete.setBackground(Color.gray);
			}
		}
		
	}
	
	
	void setUpFonts(JButton text)
	{
		text.setSize(300, 30);
		Font oldFont = text.getFont();
		Font newFont = new Font( oldFont.getFontName(), Font.PLAIN, 18 );
		text.setFont( newFont );
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//ViewMaterial hi = new ViewMaterial();

	}
}
