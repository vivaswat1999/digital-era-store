import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Registration implements ActionListener,WindowListener
{
	JFrame frame=new JFrame("REGISTRATION FORM ");
	//Container con= frame.getContentPane();
	JLabel lhead,lshopname,lowner,lmobile,lpincode,ldob,lpassword,lemail,lshoptype,laddress,lcity,ldistrict;
	JTextField tshopname,towner,tmobile,temail,tshoptype,taddress,tcity,tdistrict,tpincode;
	JPasswordField pass;
	JLabel bimg;
	JCheckBox term;
	JComboBox jcb;
	JButton submit,reset;
	JPanel panel_left,panel_right;
	String list[] ={"Stationary","Medical","Grocery"};
	 Registration()
	{
		
		panel_left = new JPanel();
		panel_right = new JPanel();

		Image icon = Toolkit.getDefaultToolkit().getImage("Icons/icon.jpg");
		frame.setIconImage(icon);

		frame.setSize(1100,650);
		frame.setLocationRelativeTo(null);
		
		frame.setLayout(null);
		
		panel_left.setBounds(0,75,500,500);
		panel_right.setBounds(500,0,600,650);

		panel_left.setLayout(null);
		panel_right.setLayout(null);
		bimg = new JLabel(new ImageIcon("gifreg1.gif"));
		bimg.setBounds(0,0,500,500);

		panel_left.add(bimg);
		//con.setBackground(Color.YELLOW);
		Font f=new Font("Times New Roman",Font.BOLD,30);
		lhead=new JLabel("REGISTRATION FORM");
		lhead.setBounds(80,12,400,40);
		lhead.setFont(f);
		frame.add(lhead);
		lshopname=new JLabel("SHOP NAME");
		lshopname.setBounds(50,50,160,30);
		panel_right.add(lshopname);
		lowner=new JLabel("OWNER NAME");
		lowner.setBounds(50,100,160,30);
		panel_right.add(lowner);
		lmobile=new JLabel("MOBILE NUMBER");
		lmobile.setBounds(50,150,200,30);
		panel_right.add(lmobile);
		lemail=new JLabel("E-MAIL ID");
		lemail.setBounds(50,200,200,30);
		panel_right.add(lemail);
		lshoptype=new JLabel("SHOP TYPE");
		lshoptype.setBounds(50,250,180,30);
		panel_right.add(lshoptype);
		jcb = new JComboBox(list);
		jcb.setBounds(230,250,180,30);
		panel_right.add(jcb);
		lpassword=new JLabel("PASSWORD");
		lpassword.setBounds(50,280,200,65);
		panel_right.add(lpassword);
		laddress=new JLabel("ADDRESS");
		laddress.setBounds(50,345,160,30);
		panel_right.add(laddress);
		lcity=new JLabel("CITY");
		lcity.setBounds(50,390,150,40);
		panel_right.add(lcity);
		lpincode=new JLabel("PINCODE");
		lpincode.setBounds(50,435,130,40);
		panel_right.add(lpincode);
		ldistrict=new JLabel("STATE");
		ldistrict.setBounds(50,475,130,40);
		panel_right.add(ldistrict);
		tshopname=new JTextField();
		tshopname.setBounds(230,53,180,20);
		panel_right.add(tshopname);
		towner=new JTextField();
		towner.setBounds(230,104,180,20);
		panel_right.add(towner);
		tmobile=new JTextField();
		tmobile.setBounds(230,155,180,20);
		panel_right.add(tmobile);
		pass=new JPasswordField();
		pass.setBounds(230,298,180,20);
		panel_right.add(pass);
		temail=new JTextField();
		temail.setBounds(230,203,180,20);
		panel_right.add(temail);
		taddress=new JTextField();
		taddress.setBounds(230,348,170,20);
		panel_right.add(taddress);
		tdistrict=new JTextField();
		tdistrict.setBounds(230,485,170,20);
		panel_right.add(tdistrict);
		tcity=new JTextField();
		tcity.setBounds(230,402,170,20);
		panel_right.add(tcity);
		tpincode=new JTextField();
		tpincode.setBounds(230,444,170,20);
		panel_right.add(tpincode);
		term =new JCheckBox("I confirm that all the information entered by me is correct");
		term.setBounds(50,516,350,25);
		term.setBackground(Color.YELLOW);
		panel_right.add(term);
		submit=new JButton("SUBMIT");
		submit.setBounds(200,556,80,25);
		panel_right.add(submit);
		reset=new JButton("RESET");
		reset.setBounds(400,556,80,25);
		panel_right.add(reset);

		frame.add(panel_left);
		frame.add(panel_right);

		submit.addActionListener(this);
		reset.addActionListener(this);	
		frame.setUndecorated(false);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String ch=ae.getActionCommand();
		if(ch=="SUBMIT")
		{
			
			if(term.isSelected())
			{
				JFrame id_frame = new JFrame();
				
				try{
				RegConnection reg = new RegConnection();
				Connection conn = reg.connect();
				int rnum = reg.genrandom();
				reg.createTable(rnum,conn);
				String q ="insert into javaregister0 values(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstat = conn.prepareStatement(q);
				pstat.setString(1,tshopname.getText());
				pstat.setString(2,towner.getText());
				pstat.setString(3,"select");
				pstat.setString(4,temail.getText());
				pstat.setString(5,tmobile.getText());
				pstat.setString(6,pass.getText());
				pstat.setString(7,tcity.getText());
				pstat.setString(8,tdistrict.getText());
				pstat.setString(9,tpincode.getText());
				pstat.setInt(10,rnum);
				pstat.setString(11,taddress.getText());
				pstat.executeUpdate();
				
				JOptionPane.showMessageDialog(id_frame,"Registered Successfully ShopID is "+rnum);
				frame.dispose();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			
			}
			else
			
			
			{
					

				//display.setText("please select terms and condition");
				
		    }
					
		}
		else
		{
			tshopname.setText(null);
			towner.setText(null);
			tmobile.setText(null);
			temail.setText(null);
			taddress.setText(null);
			tdistrict.setText(null);
			tcity.setText(null);
			tpincode.setText(null);
			pass.setText(null);
			term.setSelected(false);
			
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
		Registration ob = new Registration();

	}

}