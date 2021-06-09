import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
class MainFrame extends JFrame implements ActionListener,WindowListener
{
	JButton b_add,b_edit,b_update,b_remove,b_refresh;
	JPanel panel_mid,panel_top,panel_top_right;
	JLabel b_g1,logo,shop_name,shop_id,shop_location,owner_name,logout,view_profile; 
	JPanel panel[] = new JPanel[100];
	int count = 0,r_count=0;
	int col,row,product_id;
	int table_name_pass;
	ResultSet set;
	Statement stm;
	MainFrame(int table_name)

	{
		table_name_pass = table_name;
		Font f1 = new Font("SansSerif", Font.BOLD, 15);
		logo = new JLabel(new ImageIcon("Icons/Logo.png"));
		logo.setBounds(20,3,190,40);
		shop_name = new JLabel("",new ImageIcon("Icons/shops.png"),JLabel.LEFT);
		//shop_name.setBounds(830,5,360,20);
		shop_name.setFont(f1);
		shop_location = new JLabel("",new ImageIcon("Icons/location.png"),JLabel.LEFT);
		//shop_location.setBounds(830,28,360,15);
		owner_name= new JLabel("",new ImageIcon("Icons/user.png"),JLabel.LEFT);
		//owner_name.setBounds(830,45,360,20);
		shop_id = new JLabel("",new ImageIcon("Icons/id.png"),JLabel.LEFT);
		//shop_id.setBounds(830,70,100,20);
		view_profile = new JLabel("View Profile");
		//view_profile.setBounds(930,70,100,20);
		logout = new JLabel("Logout");
		//logout.setBounds(1030,70,100,20);
		
		panel_top = new JPanel();
		panel_mid = new JPanel();
		panel_top_right = new JPanel();

		panel_top_right.setBounds(830,5,330,85);
		panel_top_right.setBorder(new EmptyBorder(10, 15, 10, 10));
		
		setLayout(null);
		setTitle("Digital era Store");
		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		setIconImage(icon);
		b_g1= new JLabel("",new ImageIcon("mainbackground1.png"),JLabel.CENTER);
		setSize(1200,700);
		
		b_g1.setBounds(0,0,1200,700);
		
		panel_top.setBounds(20,50,800,40);
		panel_mid.setBounds(20,100,1140,540);
	
		panel_top.setLayout(new GridLayout(1,10));
		panel_mid.setLayout(null);
		panel_top_right.setLayout(new GridLayout(2,2));

		
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(false);
		setResizable(false);
		//Adding Buttons to panel_top
		 
		b_add = new JButton("ADD",new ImageIcon("Icons/plus.png"));
		b_edit = new JButton("EDIT",new ImageIcon("Icons/edit.png"));
		b_update = new JButton("UPDATE",new ImageIcon("Icons/update.png"));
		b_remove = new JButton("REMOVE",new ImageIcon("Icons/trash-bin.png"));
		b_refresh = new JButton("REFRESH");
		
		b_refresh.addActionListener(this);
		b_add.addActionListener(this);
	    b_edit.addActionListener(this);
		b_remove.addActionListener(this);
		b_update.addActionListener(this);
		//b_add.setBounds(10,20,80,50);
		
		add(b_g1);
		
		


	//MAKING PANEL
	
	
	
	try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
			stm = con.createStatement();
			Statement ustm = con.createStatement();
			ResultSet uset = ustm.executeQuery("select Shop_Name,Shopkeeper_Name,Address from javaregister0 where Shop_ID ="+table_name_pass);
			set = stm.executeQuery("select ProductID,ProductName,ProductPrice,MSP,Image,Available from `"+table_name_pass+"`");
			uset.next();
			shop_id.setText(table_name_pass+"");
			shop_name.setText(uset.getString(1));
			System.out.println(uset.getString(1));
			owner_name.setText(uset.getString(2));
			shop_location.setText(uset.getString(3)); 

			while(set.next())
			{
				product_id=set.getInt(1);
				if(count%6==0&&count!=0)
				{	
					r_count++;
					count=0;
				}
				try
				{
				col=20+175*count+10*count;
				row=10+165*r_count+10*r_count;
				/*panel[count] = new JPanel();
				panel[count].setBounds(col,row,175,165);
				panel[count].setBackground(Color.YELLOW);
				panel_mid.add(panel[count]);*/
				panel_mid.add(new CreatePanel(set).createPanel(panel[count],row,col));
				}
				catch(Exception e)
				{}
				count++;
			}
			//con.close();
		}catch(Exception ep)
		{}
		
		//repaint();




		b_g1.add(panel_top);
		b_g1.add(panel_mid);
		b_g1.add(panel_top_right);

		b_g1.add(logo);

		panel_top_right.add(shop_name);
			
		panel_top_right.add(shop_location);
		panel_top_right.add(owner_name);
		panel_top_right.add(shop_id);
		//panel_top_right.add(view_profile);
		//panel_top_right.add(logout);
		
		panel_top.add(b_add);
		panel_top.add(b_edit);
		panel_top.add(b_update);
		panel_top.add(b_remove);
		panel_top.add(b_refresh);
		
		setLocationRelativeTo(null);

		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_add)
			new NewProducts(product_id,table_name_pass);
		if(ae.getSource()==b_edit)
			new EditPanel(table_name_pass);
		if(ae.getSource()==b_remove)
			new RemoveProduct(table_name_pass);
		if(ae.getSource()==b_update)
			new UpdateStock(table_name_pass);
		if(ae.getSource()==b_refresh)
		{
		System.out.println("Page is REFRESH");
		panel_mid.removeAll();
		count=0;
		r_count=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
			stm = con.createStatement();
			Statement ustm = con.createStatement();
			ResultSet uset = ustm.executeQuery("select Shop_Name,Shopkeeper_Name,Address from javaregister0 where Shop_ID ="+table_name_pass);
			set = stm.executeQuery("select ProductID,ProductName,ProductPrice,MSP,Image,Available from `"+table_name_pass+"`");
			uset.next();
			shop_id.setText(table_name_pass+"");
			shop_name.setText(uset.getString(1));
			System.out.println(uset.getString(1));
			owner_name.setText(uset.getString(2));
			shop_location.setText(uset.getString(3)); 

			while(set.next())
			{
				product_id=set.getInt(1);
				if(count%6==0&&count!=0)
				{	
					r_count++;
					count=0;
				}
				try
				{
				col=20+175*count+10*count;
				row=10+165*r_count+10*r_count;
				/*panel[count] = new JPanel();
				panel[count].setBounds(col,row,175,165);
				panel[count].setBackground(Color.YELLOW);
				panel_mid.add(panel[count]);*/
				panel_mid.add(new CreatePanel(set).createPanel(panel[count],row,col));
				
				}
				catch(Exception e)
				{}
				count++;
				repaint();
				
			}
			//con.close();
			
		}catch(Exception ep)
		{}

	
		}

	}
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
		MainFrame obj = new MainFrame(1062777);
	}
}