package tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	public static BufferedImage  grass, tree;
	public static BufferedImage  wood;
	public  static BufferedImage[] player_still, player_down, player_up, player_right, player_left;
	public static BufferedImage[] evilMe_still, evilMe_down, evilMe_up, evilMe_right, evilMe_left;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	
	public static void init(){
		font28 = FontLoader.loadFont("src/main/resources/fonts/slkscrb.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		
		wood = sheet.crop(width*3,height,width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] =sheet.crop(width*4, 0, width*2, height); 
		btn_start[1] = sheet.crop(width*4, height, width*2, height);
		
		player_still = new BufferedImage[4];
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		
		player_still[0] = sheet.crop(0, 0, width, height);
		player_still[1] = sheet.crop(width, 0, width, height);
		player_still[2] = sheet.crop(width * 2, height, width, height);
		player_still[3] = sheet.crop(width, 0, width, height);
		player_down[0] = sheet.crop(width*4, height *2, width, height);
		player_down[1] = sheet.crop(width*5, height *2, width, height);
		player_up[0] = sheet.crop(0, height, width, height);
		player_up[1] = sheet.crop(width, height, width, height);
		player_right[0] = sheet.crop(width, height*2, width, height);
		player_right[1] = sheet.crop(width * 3, height * 2, width, height);
		player_left[0] = sheet.crop(0, height*2, width, height);
		player_left[1] = sheet.crop(width * 2, height * 2, width, height);
		
		evilMe_still = new BufferedImage[4];
		evilMe_down = new BufferedImage[2];
		evilMe_up = new BufferedImage[2];
		evilMe_right = new BufferedImage[2];
		evilMe_left = new BufferedImage[2];
		
		evilMe_still[0] = sheet.crop(0, height*3, width, height);
		evilMe_still[1] = sheet.crop(width, height*3, width, height);
		evilMe_still[2] = sheet.crop(width * 2, height*4, width, height);
		evilMe_still[3] = sheet.crop(width, height*3, width, height);
		evilMe_down[0] = sheet.crop(width*2, height*3, width, height);
		evilMe_down[1] = sheet.crop(width*3, height*3, width, height);
		evilMe_up[0] = sheet.crop(0, height*4, width, height);
		evilMe_up[1] = sheet.crop(width, height*4, width, height);
		evilMe_right[0] = sheet.crop(width, height*5, width, height);
		evilMe_right[1] = sheet.crop(width*3, height*5, width, height);
		evilMe_left[0] = sheet.crop(0, height*5, width, height);
		evilMe_left[1] = sheet.crop(width*2, height*5, width, height);
		
		
		grass = sheet.crop(2*width, 0, width, height);
		tree = sheet.crop(3*width, 0, width, height);
	}
}
