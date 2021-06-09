import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
class UpdateStock extends JFrame implements ActionListener,WindowListener
{
	JLabel lh1,lproduct_id,lavail;
	JTextField product_id,avail;
	JButton b_update;
	Connection con;
	int getID;
	int table_name_pass;
	UpdateStock(int table_name)
	{
		table_name_pass = table_name;
		setTitle("Update Availabe Stock");
		setSize(400,200);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		setIconImage(icon);
		lproduct_id = new JLabel("Product Id");
		lproduct_id.setBounds(100,20,100,20);
		product_id = new JTextField("");
		product_id.setBounds(200,20,100,20);
		
		lavail = new JLabel("Avaliblity");
		lavail.setBounds(100,60,100,20);
		avail = new JTextField("");
		avail.setBounds(200,60,100,20);
		
		b_update = new JButton("UPDATE");
		b_update.setBounds(150,110,100,30);
		b_update.addActionListener(this);
		
		add(lproduct_id);
		add(lavail);
		add(product_id);
		add(avail);
		add(b_update);
			
		
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		getID = Integer.parseInt(product_id.getText());
		String available = avail.getText();
		System.out.println(getID+" "+available);
		if((getID!='\0')&&(available!=null))
		{
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");				
				PreparedStatement pst = con.prepareStatement("update `"+table_name_pass+"` set Available = ? where ProductID ="+getID);				pst.setString(1,available);
				pst.executeUpdate();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			dispose();
		}
				
		
	}
	public void windowActivated(WindowEvent we)
	{}
	public void windowDeactivated(WindowEvent we)
	{}
	public void windowIconified(WindowEvent we)
	{}
	public void windowDeiconified(WindowEvent we)
	{}
	public void windowOpened(WindowEvent we)
	{}
	public void windowClosed(WindowEvent we)
	{}
	public void windowClosing(WindowEvent we)
	{
		dispose();
	}
	public static void main(String args[])
	{
		//new UpdateStock();
	}
}