//login form
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class loginform implements ActionListener,WindowListener
{	
	Connection con;
	Statement stmt;
	JFrame frame;
	JTextField shop_id,email_id;
	JPasswordField password;
	JButton blogin,breset,bexit;
	JPanel panel_left,panel_right;
	JLabel message;
	int login_pass;
	loginform()
	{
		frame = new JFrame();
		frame.setSize(800,300);
		frame.setLayout(null);

		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		frame.setIconImage(icon);

		panel_right = new JPanel();
		panel_left = new JPanel();
		panel_left.setLayout(null);
		panel_right.setLayout(null);
		panel_left.setBounds(0,0,400,300);
		panel_right.setBounds(400,0,400,300);

		JLabel imgbg = new JLabel(new ImageIcon("logingif.gif"));
		imgbg.setBounds(0,0,400,300);
		panel_left.add(imgbg);

		message = new JLabel("");
		
		
		
		frame.setLocationRelativeTo(null);
		
		
		
		//Container con=frame.getContentPane();
		
		//frame.setBackground(Color.PINK);
		
		Font f=new Font("Arial",Font.BOLD,30);
		
		JLabel lloginform=new JLabel("LOGIN FORM");
		lloginform.setBounds(100,10,200,40);
		lloginform.setFont(f);
		panel_right.add(lloginform);
		
		JLabel lshop_id=new JLabel("SHOP ID");
		lshop_id.setBounds(50,70,70,20);
		panel_right.add(lshop_id);
		
		shop_id=new JTextField();
		shop_id.setBounds(130,70,130,20);
		panel_right.add(shop_id);
		
		JLabel lpassword=new JLabel("Password");
		lpassword.setBounds(50,160,70,20);
		panel_right.add(lpassword);
		
		password=new JPasswordField();
		password.setBounds(130,160,130,20);
		panel_right.add(password);
		
		JLabel email=new JLabel("E-Mail ID");
		email.setBounds(50,115,70,20);
		panel_right.add(email);
		
		email_id=new JTextField();
		email_id.setBounds(130,115,130,20);
		panel_right.add(email_id);
		
		blogin=new JButton("LOGIN");
		blogin.setBounds(30,220,100,30);
		panel_right.add(blogin);
		
		breset=new JButton("RESET");
		breset.setBounds(140,220,100,30);
		panel_right.add(breset);
		
		bexit=new JButton("EXIT");
		bexit.setBounds(250,220,100,30);
		panel_right.add(bexit);

		message.setBounds(140,250,200,30);
		//message.setColor(Color.RED);
		message.setForeground(Color.red);
		panel_right.add(message);

		frame.add(panel_left);
		frame.add(panel_right);
		
		bexit.addActionListener(this);
		breset.addActionListener(this);
		blogin.addActionListener(this);

		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public int loginValidate(String email,String password,int s_id )
	{
		ResultSet rset;
		int valid = 0;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
		String query ="select * from javaregister0 where Shop_ID ="+s_id;
		stmt = con.createStatement();
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
						
			if(email.equals(rset.getString(4)))
			{
				if(password.equals(rset.getString(6)))
					{

						message.setText("Login Successfully");
						valid = 1;
					}
				else
					message.setText("Incorrect Password");				
			}
			else{
				message.setText("Email Id doesn't exit");
			}	
		}
		else
		{
			message.setText("Shop Id doesn't exist");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
		if (valid==1)
			return s_id;
		else
			return -1;
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==bexit)
			frame.dispose();
		if(ae.getSource()==blogin)
		{
			login_pass =loginValidate(email_id.getText(),password.getText(),Integer.parseInt(shop_id.getText()));
			if(login_pass!=-1)
			{
				frame.dispose();
				System.out.println(login_pass);
				new MainFrame(login_pass);
			}
		}
		if(ae.getSource()==breset)
		{
			shop_id.setText("");
			email_id.setText("");
			password.setText("");
			message.setText("");
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
		frame.dispose();
	}
	public void windowClosing(WindowEvent we)
	{}
	public static void main(String args[])
	{
	  	loginform ob = new loginform();
	}
}