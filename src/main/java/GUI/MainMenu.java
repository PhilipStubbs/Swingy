package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainMenu {
	private JButton button1;
	public JPanel panel1;

	public MainMenu() {
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				InputStream guiInput = new ByteArrayInputStream("this is a test".getBytes());
				System.setIn(guiInput);
			}
		});
	}
}
