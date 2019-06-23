package Views.Gui;

import javax.swing.*;
import java.awt.*;

public class BaseWindow {
	static public JFrame frame ;

	static public void createBaseWindow(){
		frame = new JFrame("Swingy");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height  / 4;
		int width = screenSize.width  / 4;

		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);

		}

	static public void destroyWindow(){
		frame.dispose();
		frame.setVisible(false);
	}

	static public void showBaseWindow(){
		frame.setVisible(true);
	}
}
