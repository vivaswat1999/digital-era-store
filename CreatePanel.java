import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.io.*;
import javax.imageio.*;
class CreatePanel
{
	 static JLabel proId,proImage,proName,proStock,proPrice,proMaxPrice,proAvail;
	
	CreatePanel(ResultSet set)
	{
		try
		{
			
			proId = new JLabel("Product ID "+set.getInt(1));
			proName = new JLabel(set.getString(2));
			
			Blob b = set.getBlob("Image");
			InputStream is = b.getBinaryStream();
			Image img = ImageIO.read(is);
			ImageIcon icon = new ImageIcon(img);
			Image image = icon.getImage().getScaledInstance(150,100,Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
			proImage = new JLabel(icon);
			//proImage.setIcon(icon);
			
		//	proStock = new JLabel("Stock "+set.getString(3);
			proPrice = new JLabel("Rs."+set.getString(3));
			proMaxPrice = new JLabel("MSP "+set.getString(4));
			proAvail = new JLabel("Stock "+set.getString(6));
		}catch(Exception e)
		{}		

		
		proId.setBounds(75,5,100,10);
		proImage.setBounds(10,20,155,100);
		proName.setBounds(10,128,150,15);
		proPrice.setBounds(120,135,100,10);
		proMaxPrice.setBounds(120,150,100,10);
		proAvail.setBounds(10,150,80,12);
	
	}
	public static JPanel createPanel(JPanel panel,int row,int col)
	{
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.YELLOW);
		panel.setBounds(col,row,175,165);
		Border blackline = BorderFactory.createLineBorder(Color.orange);
		panel.setBorder(blackline);
		panel.add(proId);
		panel.add(proImage);
		panel.add(proName);
		panel.add(proPrice);
		panel.add(proAvail);
		panel.add(proMaxPrice);
		
		return panel;
	}
}