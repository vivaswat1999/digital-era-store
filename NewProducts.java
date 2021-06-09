import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
class NewProducts extends JFrame implements ActionListener,WindowListener
{
	JLabel lheading,lsub_head,lpro_id,lpro_name,lpro_Price,lpro_msp,lpro_image,limg_des,lpro_des,lpro_msg,lpro_avail;
	JTextField pro_name,pro_price,pro_msp,pro_avail;
	JButton b_add,b_upload;
	JPanel panel_top,panel_bottom;
	JTextArea pro_des;
	String f_name = null;
	Connection con;
	PreparedStatement pstm;
	int table_name_pass;
	
	int gen_id;
	NewProducts(int id,int table_name)
	{
		
		table_name_pass = table_name;
		gen_id = id+1;
		setLayout(null);
		panel_top = new JPanel();
		panel_bottom = new JPanel();
		panel_top.setBounds(0,0,500,80);
		//panel_top.setBackground(Color.BLUE);
		panel_bottom.setBounds(0,100,500,300);
		//panel_bottom.setBackground(Color.BLUE);
		
		setSize(500,500);
		panel_top.setLayout(null);
		panel_bottom.setLayout(null);

		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		setIconImage(icon);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Digital era Store");
		
		lheading = new JLabel("NEW PRODUCT");
		lsub_head= new JLabel("Enter below the details of product you want to add");
		lpro_id= new JLabel("Product ID "+gen_id);
		lpro_name= new JLabel("Product Name");
		lpro_Price= new JLabel("Product Price");
		lpro_msp= new JLabel("MSP");
		lpro_des = new JLabel("Description");
		lpro_image= new JLabel("Image");
		limg_des= new JLabel("(Picture size must be less than 60Kb)");
		lpro_msg = new JLabel("");
		lpro_avail = new JLabel("Availability");
		
		pro_name = new JTextField("");
		pro_price = new JTextField("");
		pro_msp = new JTextField("");
		pro_avail = new JTextField("");
		pro_des = new JTextArea("");
		b_upload = new JButton("Upload");
		b_add = new JButton("ADD");
		
			
		b_add.addActionListener(this);
		b_upload.addActionListener(this);
		
		lheading.setBounds(200,10,200,30);
		lsub_head.setBounds(100,45,300,20);
		
		add(panel_top);
		add(panel_bottom);
		
		panel_top.add(lheading);
		panel_top.add(lsub_head);
		
		lpro_id.setBounds(210,0,200,30);
		lpro_name.setBounds(50,40,200,20);
		pro_name.setBounds(200,40,200,20);
		lpro_Price.setBounds(50,70,200,20);
		pro_price.setBounds(200,70,200,20);
		lpro_msp.setBounds(50,100,200,20);
		pro_msp.setBounds(200,100,200,20);
		
		lpro_avail.setBounds(50,130,200,20);
		pro_avail.setBounds(200,130,200,20);
		
		lpro_des.setBounds(50,160,200,20);
		pro_des.setBounds(200,160,200,40);
		lpro_image.setBounds(50,210,200,20);
		b_upload.setBounds(200,210,100,25);
		limg_des.setBounds(200,245,300,10);
		lpro_msg.setBounds(50,265,400,20);
		b_add.setBounds(350,410,100,30);
		
		panel_bottom.add(lpro_id); 
		panel_bottom.add(lpro_name);
		panel_bottom.add(pro_name);
		panel_bottom.add(lpro_Price);
		panel_bottom.add(pro_price);
		panel_bottom.add(lpro_msp);
		panel_bottom.add(pro_msp);
		panel_bottom.add(lpro_avail);
		panel_bottom.add(pro_avail);
		panel_bottom.add(lpro_des);
		panel_bottom.add(pro_des);
		panel_bottom.add(lpro_image);
		panel_bottom.add(b_upload);
		panel_bottom.add(limg_des);
		panel_bottom.add(lpro_msg);
		add(b_add);
		//repaint();
		setVisible(true);
		setResizable(false);
		addWindowListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		

		
		if(ae.getSource()==b_upload)
		{
		try
		{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f= chooser.getSelectedFile();
		f_name = f.getAbsolutePath();
		}
		catch(Exception e){}
			if(f_name!=null)
			{
				lpro_msg.setText("File Path: "+f_name);
			}
		
		}
		
		
		
		if(ae.getSource()==b_add)
		{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
			String q = "insert into `"+table_name_pass+"`() values (?,?,?,?,?,?,?)";
			System.out.println(q);
			pstm = con.prepareStatement(q);
			pstm.setInt(1,gen_id);
			pstm.setString(2,pro_name.getText());
			pstm.setString(3,pro_price.getText());
			pstm.setString(4,pro_msp.getText());
			pstm.setString(5,pro_des.getText());
			pstm.setString(7,pro_avail.getText());
			//Image
			FileInputStream fis = new FileInputStream(f_name);
			pstm.setBinaryStream(6,fis,fis.available());
			pstm.executeUpdate();
			
			ProductAdded added = new ProductAdded(gen_id);
			
			con.close();
			
		}
		catch(Exception e)
		{}
		gen_id++;
		repaint();
		dispose();
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
	//	NewProducts obj = new NewProducts(12,1062777);
	}
}