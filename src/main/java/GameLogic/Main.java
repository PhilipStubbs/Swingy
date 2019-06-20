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

			while (!EventData.getOutput().contains("exit")) {
				if (EventData.getOutput().contains("print")){
					System.out.println(EventData.getOutput());
					EventData.removeInstructions("print");
				}
				System.out.print("input");

				EventData.readSTDinAsync();

//				EventData.addOutput(input.nextLine());
			}
			System.out.println();

		}
}
