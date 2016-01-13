/**
 * Program Name: SplashScreen.java
 * Purpose: from this screen, you can branch put to different parts of the 
 * program, creat invoice, insert a piece ect
 * Coder: Ken Bonvanie 0598780
 * Date: 2013-11-06
 */

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageProducer;


public class SplashScreen extends JFrame
{
	
	JButton invoice;
	JButton insert;
	JButton update;
	//Graphics backg;
	//Image img = Toolkit.getDefaultToolkit().createImage("bee.jpg");

	SplashScreen()
	{
		super("Splash Screen");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new FlowLayout());
		
		invoice = new JButton("Create an Invoice");
		insert = new JButton("Insert a Plumbing Part");
		update = new JButton("Update a Price");
		
		setUpFonts(invoice);
		setUpFonts(insert);
		setUpFonts(update);
		
		
		
		this.add(invoice);
		this.add(insert);
		this.add(update);
		
		invoice.addActionListener(new ButtonHandler());
		insert.addActionListener(new ButtonHandler());
		update.addActionListener(new ButtonHandler());
		
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				int n = JOptionPane.showConfirmDialog(
						SplashScreen.this, "If you exit the Splash Screen then you'll exit all other programs running along with it. Are you sure you want to exit?",
						"WARNING!!!", JOptionPane.YES_NO_OPTION);

				if ( n == JOptionPane.YES_OPTION)
				{
					//destory the frame object
					SplashScreen.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
				}			
				else if(n == JOptionPane.NO_OPTION)
				{
					// dont exit
					SplashScreen.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
			}//end method
		});//end method addWindowListener() arg list
		
		
		//this.getContentPane().createImage((ImageProducer) img);
		
		this.setVisible(true);
		
	}
	

	public class ButtonHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getSource().equals(invoice))
			{
				Invoice v = new Invoice();
			}
			else if(e.getSource().equals(insert))
			{
				Insert i = new Insert("");
			}
			else if(e.getSource().equals(update))
			{
				Update ug = new Update();
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
		SplashScreen ss = new SplashScreen();
	}
}
