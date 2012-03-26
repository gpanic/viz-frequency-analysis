package gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Gregor Panič
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String appName="Frekvenčna analiza";
	
	public MainFrame() {
		setTitle(appName);
		setResizable(false);
		AnalizaPanel ap=new AnalizaPanel();
		setContentPane(ap);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
