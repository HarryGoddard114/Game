package tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.inventory.Inventory;

public class Player extends Creature{
	
	//Animation
	private Animation animStill, animDown, animUp, animRight, animLeft;
	//Attack timer
	private long lastAttackTimer, attackCooldown=200,attackTimer = attackCooldown;
	//Inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
	
		bounds.x = 20;
		bounds.y = 38;
		bounds.width = 24;
		bounds.height = 18;
		
		//Animations
		animStill = new Animation(350, Assets.player_still);
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animations
		animStill.tick();
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centreOnEntity(this);
		//Attack
		checkAttacks();
		//Inventory
		inventory.tick();

	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
		
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + cb.height;	
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height/2 - arSize/2;	
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - arSize/2;	
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
				return;
			}
		}
		
	}
	
	public void die(){
		System.out.println("You lose");
	}

	private void getInput(){
		xMove=0;
		yMove=0;
		
		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y- handler.getGameCamera().getyOffset()),width,height, null);
	}

	public void postRender(Graphics g){
		
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove<0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if (yMove < 0){
			return animUp.getCurrentFrame();
		}else if (yMove > 0){
			return animDown.getCurrentFrame();
		}else{
			return animStill.getCurrentFrame();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
