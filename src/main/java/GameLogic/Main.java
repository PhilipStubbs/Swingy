package GameLogic;

import javax.swing.*;
import java.util.Scanner;
import GUI.MainMenu;

public class Main {

		public static void main(String[] args) {
			System.out.println("Starting");

			JFrame frame = new JFrame("Swingy");
			Scanner input = new Scanner(System.in);

			frame.setContentPane(new MainMenu().panel1);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);

			System.out.println("Starting");

			while (input.hasNext()) {
				System.out.println(EventData.getOutput());

				EventData.addOutput(input.nextLine());
//				System.out.print("input:"+input.nextLine());
			}
			System.out.println();

		}
}
