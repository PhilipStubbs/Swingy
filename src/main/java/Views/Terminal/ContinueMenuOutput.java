package Views.Terminal;

import Models.Mobs.Hero;

import java.util.List;

public class ContinueMenuOutput {

    public static void outputHeroes(List<Hero> heroes){
        System.out.println("please select a hero by using their index");
        for (int i = 0; i < heroes.size(); i++){
            System.out.println("Index:"+i +" -> "+ heroes.get(i).toString());
        }
    }
}
