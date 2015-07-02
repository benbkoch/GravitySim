package me.benbkoch.gravitysim;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameRenderer {

	private GameWorld world;
	private OrthographicCamera cam;
	private int trackingIndex = 0;
	private MassObject tracking;
	private boolean keyWasPressed = false, locked = false;

	public OrthographicCamera getCam() {
		return cam;
	}

	private ShapeRenderer shapeRenderer;
	private List<MassObject> objects;

	public GameRenderer(GameWorld world, List<MassObject> objects) {
		this.world = world;
		this.objects = objects;

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		world.gameHeight = (int) cam.viewportHeight;
		world.gameWidth = (int) cam.viewportWidth;

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		tracking = objects.get(0);
	}

	public void render() {
		if(trackingIndex >= objects.size())
			trackingIndex = objects.size() - 1;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			if (locked)
				locked = false;
			else
				locked = true;
		}

		if (locked) {
			cam.position.set(tracking.x, tracking.y, 0);
			cam.update();
			shapeRenderer.setProjectionMatrix(cam.combined);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			keyWasPressed = true;
			for (MassObject object : objects) {

				if (object.getMass() > tracking.getMass()) {
					tracking = object;
					trackingIndex = objects.indexOf(object);
				}
			}
			cam.position.set(tracking.x, tracking.y, 0);
			cam.update();
			shapeRenderer.setProjectionMatrix(cam.combined);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)
				&& trackingIndex > 0 && !keyWasPressed) {
			keyWasPressed = true;
			trackingIndex--;
			tracking = objects.get(trackingIndex);
			cam.position.set(tracking.x, tracking.y, 0);
			cam.update();
			shapeRenderer.setProjectionMatrix(cam.combined);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)
				&& trackingIndex < objects.size() - 1 && !keyWasPressed) {
			keyWasPressed = true;
			trackingIndex++;
			tracking = objects.get(trackingIndex);
			cam.position.set(tracking.x, tracking.y, 0);
			cam.update();
			shapeRenderer.setProjectionMatrix(cam.combined);
		} else {
			keyWasPressed = false;
		}

		for (MassObject object : objects) {
			object.render(shapeRenderer);
		}
	}

	public void resize(int width, int height) {
		cam = new OrthographicCamera(width, height);
		world.gameHeight = (int) cam.viewportHeight;
		world.gameWidth = (int) cam.viewportWidth;
		shapeRenderer.setProjectionMatrix(cam.combined);
	}
}
