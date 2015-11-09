package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	
	private int xpos;
	private int ypos;
	
	private int cur_xpos;
	private int cur_ypos;
	private float speed = 500f;
	private float speed_trial = 0.08f;


	private Image image_trail;
	
	private Image playNow;
	private Image exitGame;
	
	public String mouse_state = "No input yet";
	
	
//	public Menu(int state_id){
//		this.state_id = state_id;
//		
//	}

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		image_trail = new Image("res/wukong.png");
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
		
	}

	//draw graphics
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString(mouse_state, 50, 50); 		//right+, down+
		
		g.drawString("Welcome to Xiaohan's First Game", 150, 30);
		playNow.draw(100,100);
		exitGame.draw(100, 200);
		
		image_trail.draw(cur_xpos-40, cur_ypos-40,0.5f);
		
//		image_trail.setCenterOfRotation(30, 30);
//		image_trail.rotate(5);
		

		
	}

	//update images, move around, animation
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		/** MOUSE **/
		xpos = Mouse.getX();
		ypos = -Mouse.getY() + 359;	//opposite to the system 
		mouse_state = "mouse detected x:" + xpos + " y:"+ypos;
		
		/** KEYBOARD**/		
		Input input = gc.getInput();	//get input from mouse and keyboard
		if(input.isKeyDown(Input.KEY_DOWN)){
			ypos+=110;
		}
		
		/** click **/
		//playNow button
		if((xpos > 100 && xpos < 311) && (ypos > 100 && ypos < 140 )){
			if(input.isMouseButtonDown(0)){			//o left, 1 right click
				sbg.enterState(1);
			}
		}
		
		//exist button
		if((xpos > 100 && xpos < 311) && (ypos > 200 && ypos < 240 )){
			if(input.isMouseButtonDown(0)){			//o left, 1 right click
				System.exit(0);	// a nonzero status code indicates abnormal termination.
			}
		}
		
		/** pull **/
		
//		float angle = (float) Math.toDegrees(Math.atan2(ypos-cur_ypos,  xpos-cur_xpos ));
//		int a = (int) (Math.cos(angle) *10);
//		int b = (int) (Math.sin(angle) *10);
//		System.out.println(a);
//		System.out.println(b);
//		cur_xpos += speed_trial*delta * a;
//		cur_ypos += speed_trial*delta * b;
		
		if((xpos - cur_xpos)!=0){
			if((xpos - cur_xpos)>0){
				cur_xpos += (xpos - cur_xpos)/speed*delta;
			}else{
				cur_xpos -= (-(xpos - cur_xpos))/speed*delta;
			}
		}

		if((ypos - cur_ypos)!=0){
			if((ypos - cur_ypos)>0){
				cur_ypos += (ypos - cur_ypos)/speed*delta;
			}else{
				cur_ypos -= (-(ypos - cur_ypos))/speed*delta;
			}
		}


	}

	public int getID() {
		return State.menu;
	}




}
