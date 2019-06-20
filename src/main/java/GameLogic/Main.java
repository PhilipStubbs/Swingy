package GameLogic;

import javax.swing.*;
import java.util.Scanner;

import GUI.MainMenu;

public class Main {

		public static void main(String[] args) {
			System.out.println("Starting");

			JFrame frame = new JFrame("Swingy");

			frame.setContentPane(new MainMenu().panel1);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);

			System.out.println("Starting");
			EventParsing parser = new EventParsing();
			EventData eventData = new EventData();

			parser.instructionParseAsync();

			eventData.readStdinAsync();

			System.out.println("End of program");

		}
}
