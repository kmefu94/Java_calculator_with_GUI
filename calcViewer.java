import javax.swing.JFrame;
   
public class calcViewer
{
	public static void main(String[] args)
	{
		JFrame frame = new calcFrame();
		frame.setTitle("A calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(calcFrame.FRAME_WIDTH, calcFrame.FRAME_HEIGHT);
	}
}