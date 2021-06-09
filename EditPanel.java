import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class EditPanel extends JFrame implements ActionListener,WindowListener
{
	JLabel lpro_id,lpro_name,lpro_price,lpro_msp,lpro_des,lpro_img,limg_des,lmsg;
	JTextField pro_id,pro_name,pro_price,pro_msp;
	JTextArea pro_des;
	JButton go,upload,submit;
	int table_name_pass;
	EditPanel(int table_name)
	{
		table_name_pass = table_name;
		setTitle("Edit");
		setSize(400,400);
		setLayout(null);
		setBackground(Color.GREEN);

		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		setIconImage(icon);

		lpro_id = new JLabel("Product ID");
		lpro_name = new JLabel("Product Name");
		lpro_price = new JLabel("Product Price");
		lpro_msp = new JLabel("MSP");
		lpro_img = new JLabel("Image");
		lpro_des= new JLabel("Description");
		limg_des = new JLabel("(Image size must be less than 60Kb)");
		lmsg = new JLabel("");
		setLocationRelativeTo(null);
		lpro_id.setBounds(50,50,100,20);
		lpro_name.setBounds(80,90,100,30);
		lpro_price.setBounds(80,130,100,30);
		lpro_msp.setBounds(80,170,100,30);
		lpro_des.setBounds(80,210,100,30);
		lpro_img.setBounds(80,250,100,30);
		
		pro_id = new JTextField("");
		pro_name = new JTextField("");
		pro_price = new JTextField("");
		pro_msp = new JTextField("");
		pro_des = new JTextArea("");
		go= new JButton("GO..");
		upload=new JButton("UPLOAD");
		submit= new JButton("Submit");
		
		pro_id.setBounds(160,50,100,20);
		go.setBounds(290,50,80,20);
		pro_name.setBounds(200,90,100,30);
		pro_price.setBounds(200,130,100,30);
		pro_msp.setBounds(200,170,100,30);
		pro_des.setBounds(200,210,100,30);
		upload.setBounds(200,250,100,30);
		limg_des.setBounds(80,290,300,20);
		lmsg.setBounds(50,320,100,30);
		submit.setBounds(280,330,80,30);
		
		add(lpro_id);
		add(pro_id);
		add(go);
		add(lpro_name);
		add(pro_name);
		add(lpro_price);
		add(pro_price);
		add(lpro_msp);
		add(pro_msp);
		add(lpro_des);
		add(pro_des);
		add(lpro_img);
		add(upload);
		add(limg_des);
		add(lmsg);
		add(submit);
		
		go.addActionListener(this);
		submit.addActionListener(this);
		upload.addActionListener(this);
		submit.addActionListener(this);
		setResizable(false);
		setVisible(true);
		addWindowListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		int id,flag=-1;
		PreparedStatement pstat;
		String img_path=null;
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
			
			if(ae.getSource()==upload)
			{
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				img_path = file.getAbsolutePath();
			}
			if(ae.getSource()==go)
			{
				PreparedStatement stat = con.prepareStatement("select ProductName,ProductPrice,MSP,Description from `"+table_name_pass+"` where ProductID = ?");
				String sid =pro_id.getText();
				id = Integer.parseInt(sid);
				stat.setInt(1,id);
				
				ResultSet set = stat.executeQuery();
				
				set.next();
				String name = set.getString(1);
				String price = set.getString(2);
				String msp = set.getString(3);
				String desc = set.getString(4);
				pro_name.setText(name);
				pro_price.setText(price);
				pro_msp.setText(msp);
				pro_des.setText(desc);
				
			}
			if(ae.getSource()==submit)
			{
				if(img_path==null)
				{
					pstat = con.prepareStatement("update `"+table_name_pass+"` set ProductName=?,ProductPrice=?,MSP=?,Description=? where ProductID=?");
					flag=0;
				}
					else
					pstat = con.prepareStatement("update `"+table_name_pass+"` set ProductName=?,ProductPrice=?,MSP=?,Description=?,Image=? where ProductID=?");
				
				pstat.setString(1,pro_name.getText());
				pstat.setString(2,pro_price.getText());
				pstat.setString(3,pro_msp.getText());
				pstat.setString(4,pro_des.getText());
				if(flag==-1)
				{
					FileInputStream fis = new FileInputStream(img_path);
					pstat.setBinaryStream(5,fis,fis.available());
					pstat.setInt(6,Integer.parseInt(pro_id.getText()));
				}
				else
					pstat.setInt(5,Integer.parseInt(pro_id.getText()));
				pstat.executeUpdate();
				con.close();
				dispose();
				
			}
		}catch(Exception e)
		{
			System.out.println(e);
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
		public void windowClosing(WindowEvent we)
	{}
		public void windowClosed(WindowEvent we)
	{
		dispose();
	}
	public static void main(String args[])
	{
	//	new EditPanel();
	}
}