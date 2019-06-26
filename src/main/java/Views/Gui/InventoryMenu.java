package Views.Gui;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Mobs.Hero;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InventoryMenu extends BaseWindow {
	private JPanel inventoryMenu;
	private JButton exitButton;
	private JButton equipItemsButton;
	private JList helmList;
	private JList armourList;
	private JList weaponList;

	public InventoryMenu() {
		displayInventory();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("save_exit");
			}
		});
		equipItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String indexString = helmList.getSelectedIndex() + " " + armourList.getSelectedIndex() + " " + weaponList.getSelectedIndex();
				ApplicationControls.addInstructions(indexString);
			}
		});
	}


	public void updateHelmBox(List<Artifact> helmL){
		List<String>list = new ArrayList<String>();

		for (int i = 0; i< helmL.size(); i++){
			list.add(helmL.get(i).getDetails());
		}
		helmList.setListData(list.toArray());
	}

	public void updateAmourBox(List<Artifact> amourLi){
		List<String>list = new ArrayList<String>();

		for (int i = 0; i< amourLi.size(); i++){
			list.add(amourLi.get(i).getDetails());
		}
		armourList.setListData(list.toArray());
	}

	public void updateWeaponBox(List<Artifact> weaponLi){
		List<String>list = new ArrayList<String>();

		for (int i = 0; i< weaponLi.size(); i++){
			list.add(weaponLi.get(i).getDetails());
		}
		weaponList.setListData(list.toArray());
	}

	public void displayInventory(){
		Hero hero = ApplicationControls.getHero();

		List<Artifact>[] backpack = hero.getBackpack();

		updateHelmBox(backpack[HELM]);
		updateAmourBox(backpack[ARMOUR]);
		updateWeaponBox(backpack[WEAPON]);
	}

	static public void displayInventoryMenu(){
		frame.setContentPane(new InventoryMenu().inventoryMenu);
		frame.pack();
	}
}
