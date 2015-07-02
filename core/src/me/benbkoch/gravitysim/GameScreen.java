package me.benbkoch.gravitysim;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	
	
	private GameWorld world;
	private GameRenderer renderer;
	private List<MassObject> objects;

	public GameScreen() {
		
		objects = new ArrayList<MassObject>();

		for(int i = 0; i < Constants.numObjects; i++) {			
			objects.add(new MassObject(MathUtils.random(Constants.massMin, Constants.massMax), new Vector2(MathUtils.random((-Gdx.graphics.getWidth() / 2) * Constants.areaMultiplier, (Gdx.graphics.getWidth() / 2) * Constants.areaMultiplier), MathUtils.random(-Gdx.graphics.getHeight() * Constants.areaMultiplier, Gdx.graphics.getHeight() * Constants.areaMultiplier)), new Vector2(MathUtils.random(0.0f, 0.0f), MathUtils.random(0.0f, 0.0f)), new Color(MathUtils.random(0.5f, 1.0f), MathUtils.random(0.5f, 1.0f), MathUtils.random(0.5f, 1.0f), 1)));
		}
		world = new GameWorld(objects);
		renderer = new GameRenderer(world, objects);
	}
	
	public void show() {
		
	}

	@Override
	public void render(float delta) {

		world.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
