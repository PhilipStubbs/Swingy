package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.Hero;
import Models.SavedGameLoader;
import Views.Gui.BaseWindow;
import Views.Gui.InventoryMenu;
import Views.Terminal.InventoryMenuOutput;

import java.util.List;

import static Controllers.ApplicationControls.getHero;

public class InventoryMenuParsing  extends Global {
	static List<String> instructions;
	private static boolean flag;

	enum inventoryMenuInstruction {
		save_exit,
		exit,
		gui,
	}

	public static void inventoryMenuParsing() {
		int instructionIndex = -1;

		if (ApplicationControls.status == INVENTORY_MENU) {
			if (!flag) {
				InventoryMenu.displayInventoryMenu();

				flag = true;
			}
			instructions = ApplicationControls.getInstructions();
			InventoryMenuOutput.displayInventoryInstrucitons();
		}

		try {
			while (ApplicationControls.status == INVENTORY_MENU) {

				for (int i = 0; i < instructions.size(); i++) {
					instructionIndex = i;
					/* IMPORTANT: remove instruction after use. */

					if (instructions.get(i) != null) {

						switch (inventoryMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

							case save_exit:
								ApplicationControls.setIsRunning(false);
								SavedGameLoader.saveGame();
								Controllers.ApplicationControls.closeApplication();
								break;
								
							case exit:
								ApplicationControls.setIsRunning(false);
								Controllers.ApplicationControls.closeApplication();
								break;

							case gui:
								BaseWindow.showBaseWindow();
								break;

							default:
								System.out.println("Invalid instruction:" + instructions.get(i));

						}

						ApplicationControls.removeInstructions(instructions.get(i));
					}
				}
				instructions = ApplicationControls.getInstructions();

			}
		} catch (IllegalArgumentException e) {
			try {
				Hero hero = getHero();
				List<Artifact>[] backpack = hero.getBackpack();

				String[] split = instructions.get(instructionIndex).split(" ");

				if (split.length != 3){
					System.out.println("Invalid instruction. 3 indexes are required.");
				} else {
					int helmIndex = Integer.parseInt(split[0]);
					int armourIndex = Integer.parseInt(split[1]);
					int weaponIndex = Integer.parseInt(split[2]);

					if (helmIndex >= 0 && helmIndex < backpack[HELM].size()){
						hero.equipHelm(helmIndex);
					}
					if (armourIndex >= 0 && armourIndex < backpack[ARMOUR].size()){
						hero.equipArmour(armourIndex);
					}
					if (weaponIndex >= 0 && weaponIndex < backpack[WEAPON].size()){
						hero.equipWeapon(weaponIndex);
					}
					flag = false;
					ApplicationControls.status = GAME_LOOP;
				}
			} catch (NumberFormatException x) {
				System.out.println("Not an int:" + x.getLocalizedMessage());
			}
			ApplicationControls.removeInstructions(instructions.get(instructionIndex));
			inventoryMenuParsing();

		} finally {
			instructions = ApplicationControls.getInstructions();
		}
	}
}
