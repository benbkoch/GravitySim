package me.benbkoch.gravitysim;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import me.benbkoch.gravitysim.Constants;

public class MassObject extends Circle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private double mass;
	
	private Color color;
	
	private Vector2 position, velocity, force, acceleration;
	
	
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = (float) radius;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}
	
	public double getTotalVelocity() {
		return Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y, 2));
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}


	public MassObject(double mass, Vector2 position, Vector2 velocity, Color color) {
		this.mass = mass;
		this.radius = (float) Math.sqrt(mass / (Math.PI * Constants.density));
		this.position = position;
		this.velocity = velocity;
		this.color = color;
		this.force = new Vector2(0, 0);
		this.acceleration = new Vector2(0, 0);
		//this.velocity = new Vector2((float)Math.cos(this.position.angleRad()), (float)Math.sin(this.position.angleRad()));
		//this.velocity.rotate90(1);
		//this.velocity.scl((float) .2);
	}
	
	public void updateRadius() {
		double bottom = Math.PI * Constants.density;
		this.radius = (float) Math.sqrt(mass / (bottom));
	}

	public void setForce(Vector2 force) {
		this.force = force;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2 getForce() {
		return force;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void render(ShapeRenderer shapeRenderer) {
        
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(color);
		shapeRenderer.circle(position.x, position.y, radius);
		shapeRenderer.end();
	}

}
