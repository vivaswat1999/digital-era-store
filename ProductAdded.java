import javax.swing.*;
class ProductAdded
{
	
	JFrame frame;
	ProductAdded(int id)
	{
		frame = new JFrame();
		JOptionPane.showMessageDialog(frame,"Your Product "+id+" added Successfully");
	}
	
}