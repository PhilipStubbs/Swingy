package Controllers.Services;

import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.*;

import java.util.ArrayList;
import java.util.List;

public class NewGameMenuService extends Global {

	enum HeroClasses{
		wizard,
		fighter,
		hunter,
		rouge
	}

	public static Hero createHero(String name, String heroClass){
		List<Artifact>[] backpack = new ArrayList[3];
		backpack[HELM] = new ArrayList<Artifact>();
		backpack[ARMOUR] = new ArrayList<Artifact>();
		backpack[WEAPON] = new ArrayList<Artifact>();
		Artifact[] equipped = new Artifact[3];

		switch (HeroClasses.valueOf(heroClass.toLowerCase())) {
			case wizard:
				equipped[HELM] = new Artifact("Cool_Wizard_Hat", 1, HELM);
				equipped[ARMOUR] = new Artifact("Robe", 1, ARMOUR);
				equipped[WEAPON] = new Artifact("Staff", 1, WEAPON);
				return (new Wizard(name, 0, 0, 100, 20, 20, backpack, equipped));

			case rouge:
				equipped[HELM] = new Artifact("Basic_cap", 1, HELM);
				equipped[ARMOUR] = new Artifact("Cloth_shirt", 1, ARMOUR);
				equipped[WEAPON] = new Artifact("Daggers", 1, WEAPON);
				return (new Rouge(name, 0, 0, 100, 20, 20, backpack, equipped));

			case hunter:
				equipped[HELM] = new Artifact("Hunters_Hat", 1, HELM);
				equipped[ARMOUR] = new Artifact("Vest", 1, ARMOUR);
				equipped[WEAPON] = new Artifact("Bow", 1, WEAPON);
				return (new Hunter(name, 0, 0, 100, 20, 20, backpack, equipped));

			case fighter:
				equipped[HELM] = new Artifact("Steel_Cap", 1, HELM);
				equipped[ARMOUR] = new Artifact("Cloth_shirt", 1, ARMOUR);
				equipped[WEAPON] = new Artifact("Wooden_sword", 1, WEAPON);
				return (new Fighter(name, 0, 0, 100, 20, 20, backpack, equipped));

			default:
				return (new Hero(name, 0, 0, 100, 20, 20, backpack, equipped));
		}
	}
}
