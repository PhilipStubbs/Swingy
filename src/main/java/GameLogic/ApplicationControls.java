package GameLogic;

import Views.BaseWindow;

public class ApplicationControls {

	static public void closeApplication(){
		BaseWindow.destoryWindow();
		System.exit(0);
	}
}
