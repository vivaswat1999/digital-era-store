import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class RemoveProduct extends JFrame implements ActionListener, WindowListener
{
	int getId;
	Connection con;
	int table_name_pass;
	RemoveProduct(int table_name)
	{
		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		setIconImage(icon);
		table_name_pass = table_name;
		setTitle("REMOVE PRODUCT");
		try{
		getId = Integer.parseInt(JOptionPane.showInputDialog("Please Enter the Product Id to remove the Product"));
		System.out.println(getId);
	
		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
			PreparedStatement pst = con.prepareStatement("DELETE from `"+table_name_pass+"` where ProductID ="+getId);
			
			pst.executeUpdate();
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent e)
	{}

	public void windowActivated(WindowEvent we)
	{}
	public void windowDeactivated(WindowEvent we)
	{}
	public void windowOpened(WindowEvent we)
	{}
	public void windowIconified(WindowEvent we)
	{}
	public void windowDeiconified(WindowEvent we)
	{}
	public void windowClosed(WindowEvent we)
	{
		dispose();
	}
	public void windowClosing(WindowEvent we)
	{}
	public static void main(String args[])
	{
		//new RemoveProduct();
	}
}