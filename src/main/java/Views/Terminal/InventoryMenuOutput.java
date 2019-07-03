package Views.Terminal;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.Hero;

import java.util.List;

public class InventoryMenuOutput extends Global {

	private static  void displayArtifactList(List<Artifact> itemList, String type){
		System.out.println(type);
		for (int i = 0; i < itemList.size(); i++){
			System.out.print("	"+i + ":"+itemList.get(i).getDetails() + "\n");
		}
	}

	public static void displayInventory(){
		Hero hero = ApplicationControls.getHero();

		List<Artifact>[] backpack = hero.getBackpack();
		System.out.print(" | ");
		displayArtifactList(backpack[HELM], "Helm");
		System.out.print(" | ");
		displayArtifactList(backpack[ARMOUR], "Armour");
		System.out.print(" | ");
		displayArtifactList(backpack[WEAPON], "Weapons");
		System.out.print(" | ");
	}

	public static void displayInventoryInstrucitons(){

		System.out.println("Inventory format: Index, Index, Index");
		System.out.println("if you wish to keep current loadout use '-1 -1 -1'");
		displayInventory();
	}
}
