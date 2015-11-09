package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

	public static final String gamename = "xiaohan game";
//	public static final int menu = 0;
//	public static final int play = 1;

	
	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu());
		this.addState(new Play());
	}

	public static void main (String[] args){
		AppGameContainer appGameContainer;
		try {
			appGameContainer = new AppGameContainer(new Game(gamename));	//
			appGameContainer.setDisplayMode(640, 360, false);
			appGameContainer.start();//creat the window
			
		} catch (SlickException e) {
			e.printStackTrace();

		}
	}

	//gamecontainer: inputlistenner, loops, 
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(State.menu);
//		.init(gc, this);
		this.getState(State.play);
//		.init(gc, this);
		this.enterState(State.menu);	//default screen

	}
}
