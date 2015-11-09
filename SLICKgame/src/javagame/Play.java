package javagame;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	
	private Animation bucky, movingUp, movingDown, movingLeft, movingRight;
	private Image worldMap;
	private boolean quit = false;
	int[] duration = {200,200};	//how long the animation(each image) last for (0.2s)
	float buckyPositionX = 0;
	float buckyPositionY = 0;
	float shiftX = buckyPositionX + 320;	//make the map move rather than bucky itself, 640*360
	float shiftY = buckyPositionY + 180;
	float obstacleX = 0;
	float obstacleY = 0;
	int mapHight = 1;
	int mapWidth = 1;
	Random randomGenerator = new Random();
	int obstacle_direction;
	float obstacle_speed = .3f;
	
	private Obstacle_gen obstacle1 = null;
	private Obstacle_gen obstacle2 = null;
	private Obstacle_gen obstacle3 = null;
	private Obstacle_gen obstacle4 = null;
	private Obstacle_gen obstacle5 = null;
	private Obstacle_gen obstacle6 = null;

	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		worldMap = new Image("res/world.png");
		mapHight = worldMap.getHeight();
		mapWidth = worldMap.getWidth();
		Image[] walkUp = {new Image("res/buckysBack.Png"), new Image("res/buckysBack.Png")};	//why two images?
		Image[] walkDown = {new Image("res/buckysFront.Png"), new Image("res/buckysFront.Png")};
		Image[] walkLeft = {new Image("res/buckysLeft.Png"), new Image("res/buckysLeft.Png")};
		Image[] walkRight = {new Image("res/buckysRight.Png"), new Image("res/buckysRight.Png")};
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		bucky = movingDown;
//		past_time = System.currentTimeMillis();
		System.out.println("hahha");
		obstacle1 = new Obstacle_gen(mapHight,mapWidth,obstacle_speed);
		obstacle2 = new Obstacle_gen(mapHight,mapWidth,obstacle_speed);
		obstacle3 = new Obstacle_gen(mapHight,mapWidth,obstacle_speed);
		obstacle4 = new Obstacle_gen(mapHight,mapWidth,obstacle_speed);
		obstacle5 = new Obstacle_gen(mapHight,mapWidth,obstacle_speed);
		obstacle6 = new Obstacle_gen(mapHight,mapWidth,obstacle_speed);
	}

	//draw graphics
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		worldMap.draw(buckyPositionX, buckyPositionY);		//(0,0) the worldmap matched left top corner, (-10, -10) image matches the outside the left top corner
		bucky.draw(shiftX,shiftY);	//shiftX & shiftY not change with buckyPositionX automatically .......WHY?
	
		g.setColor(Color.white);
		g.drawString("Bucky Position: " + (int)buckyPositionX+" , "+(int)buckyPositionY, 360, 20);
		
		//obstacle

		g.setColor(Color.red);
		g.fillRect(buckyPositionX+obstacle1.obstacleX, buckyPositionY+obstacle1.obstacleY, 40, 40);  //xiang dui ju li!  move with the map!

		g.fillOval(buckyPositionX+obstacle2.obstacleX, buckyPositionY+obstacle2.obstacleY, 40, 40);  

		g.fillRect(buckyPositionX+obstacle3.obstacleX, buckyPositionY+obstacle3.obstacleY, 40, 40);  

		g.fillRect(buckyPositionX+obstacle4.obstacleX, buckyPositionY+obstacle4.obstacleY, 40, 40); 

		g.fillRect(buckyPositionX+obstacle5.obstacleX, buckyPositionY+obstacle5.obstacleY, 40, 40);  

		g.fillOval(buckyPositionX+obstacle6.obstacleX, buckyPositionY+obstacle6.obstacleY, 40, 40);  

	//pause menu
	if (quit == true){
		g.drawString("Resume (R)", 250, 100);
		g.drawString("Main Menu (M)", 250, 120);
		g.drawString("Quit Game (Q)", 250, 140);
		if (quit == false){
			g.clear();
		}
	}	
}

//update images, move around, animation
public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	Input input = gc.getInput();
	if(input.isKeyDown(Input.KEY_UP) && quit != true){
		if(buckyPositionY<160){
			bucky = movingUp;
			buckyPositionY += delta * .5f;
		}
	}
	else if(input.isKeyDown(Input.KEY_DOWN) && quit != true){
		if(buckyPositionY>-590){
			bucky = movingDown;
			buckyPositionY -= delta * .5f;
		}
	}
	else if(input.isKeyDown(Input.KEY_LEFT) && quit != true){
		if(buckyPositionX < 330){
			bucky = movingLeft;
			buckyPositionX += delta * .5f;
		}
	}
	else if(input.isKeyDown(Input.KEY_RIGHT) && quit != true){
		if (buckyPositionX > -840) {
			bucky = movingRight;
			buckyPositionX -= delta * .5f;
		}
	}
	
	//when the menu is up
	if(input.isKeyPressed(Input.KEY_ESCAPE)){	
		quit = !quit;
	}
	
	//choose the menu
	if(input.isKeyPressed(Input.KEY_R)){
		quit = !quit;
	}
	else if(input.isKeyPressed(Input.KEY_M)){
		sbg.enterState(0);
	}
	else if(input.isKeyPressed(Input.KEY_Q)){
		System.exit(0);
	}
	
	//obstacle
	obstacle1.Obstacle_update(delta);
	obstacle2.Obstacle_update(delta);
	obstacle3.Obstacle_update(delta);
	obstacle4.Obstacle_update(delta);
	obstacle5.Obstacle_update(delta);
	obstacle6.Obstacle_update(delta);
}

	public int getID() {	
		return State.play;
	}

	
	
	
}
