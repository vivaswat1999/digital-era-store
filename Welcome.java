import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Welcome extends JFrame implements ActionListener,MouseListener
{
	JLabel bg_image,logo,lclose;
	JButton login,register,close;
	Welcome()
	{
		Color bt = Color.decode("#fab87a");
		setSize(600,400);
		setUndecorated(true);
		setLayout(null);
		setLocationRelativeTo(null);
		bg_image = new JLabel(new ImageIcon("welcome.jpg"));
		bg_image.setBounds(0,0,600,400);
		register = new JButton("REGISTER");
		register.setBounds(50,350,100,30);
		//register.setRolloverEnabled(true);
register.setOpaque(false);
//register.setUI(new BasicButtonUI());
//register.setContentAreaFilled(false);
register.setFocusable(false);
register.setToolTipText("Register you Store");
		
		login = new JButton("LOGIN");
		login.setBounds(450,350,100,30);

		lclose = new JLabel(new ImageIcon("Icons/signboard64.png"));
		lclose.setBounds(520,10,64,64);
		lclose.setToolTipText("Exit");
		add(bg_image);
		bg_image.add(login);
		bg_image.add(register);
		bg_image.add(lclose);
		 
		register.addActionListener(this);
		login.addActionListener(this); 
		lclose.addMouseListener(this);
		setVisible(true);
		
	}
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource()==lclose)
			dispose();
	}
	public void mousePressed(MouseEvent me)
	{}
	public void mouseExited(MouseEvent me)
	{}
	public void mouseReleased(MouseEvent me)
	{}
	public void mouseEntered(MouseEvent me)
	{
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==register)
			new Registration();
		if(ae.getSource()==login)
			new loginform();
	}
	public static void main(String args[])
	{
		new Welcome();
	}
}