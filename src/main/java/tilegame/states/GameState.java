package tilegame.states;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Tree;
import tilegame.worlds.World;

public class GameState extends State {
	
	private World world;

	public GameState(Handler handler){
		super(handler);
		world = new World(handler,"src/main/resources/worlds/world1.txt");
		handler.setWorld(world);
		

	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
