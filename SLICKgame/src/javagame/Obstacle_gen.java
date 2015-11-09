package javagame;

import java.util.Random;

public class Obstacle_gen {
	private int oid;
	private long past_time;
	private long cur_time;
	private Random randomGenerator;
	private int mapHight;
	private int mapWidth;
	private float obstacle_speed;
	private int obstacle_direction;
	static int num_construct;
	float obstacleX;
	float obstacleY;
	private boolean obstacle_exist;
	
//	Obstacle_gen(){
//
//	}
	
	Obstacle_gen(int mapHight, int mapWidth, float obstacle_speed){
		past_time= System.currentTimeMillis();
		randomGenerator = new Random();
		this.mapHight = mapHight;
		this.mapWidth = mapWidth;
		this.obstacle_speed = obstacle_speed;
		num_construct++;
		oid = num_construct;
		System.out.println("this is No: " + oid +" onject");
		obstacle_exist = false;
	}
	
	void Obstacle_update(int delta){
		//when the obstacle is just generated, sleep for 0.2s
		if(!obstacle_exist){
			obstacleX = randomGenerator.nextInt(mapWidth);
			obstacleY = randomGenerator.nextInt(mapHight);
			obstacle_direction = randomGenerator.nextInt(4);
			obstacle_speed = .3f;
			obstacle_exist = true;
		}else{
			obstacle_speed += .02f;
			switch (obstacle_direction) {
				case 0:	//left
					obstacleX -= delta * obstacle_speed;
					break;
				case 1:	//right
					obstacleX += delta * obstacle_speed;
					break;
				case 2:	//up
					obstacleY += delta * obstacle_speed;
					break;
				case 3: //down
					obstacleY -= delta * obstacle_speed;
					break;
				default:
					break;
			}
			cur_time = System.currentTimeMillis();

			if((cur_time-past_time)>= 2000){
				past_time =System.currentTimeMillis();
				obstacle_exist = false;			
			}
		}
		
	}

	
	
	
}

