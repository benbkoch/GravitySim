package me.benbkoch.gravitysim;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class GameWorld {


	public int gameHeight, gameWidth;
	private List<MassObject> objects;

	public GameWorld(List<MassObject> objects) {
		this.objects = objects;
	}

	public void update(float delta) {

		

		for (int i = 0; i < objects.size(); i++) {
			MassObject object1 = objects.get(i);
			for (int j = 0; j < objects.size(); j++) {
				MassObject object2 = objects.get(j);
				if (object1 == object2)
					continue;
				else {

					object1.x = object1.getPosition().x;
					object1.y = object1.getPosition().y;
					object2.x = object2.getPosition().x;
					object2.y = object2.getPosition().y;
					double force = (Constants.G * object1.getMass() * object2.getMass())
							/ (Math.pow(
									object1.getPosition().dst(
											object2.getPosition()) * Constants.distanceMultiplier, 2));

					Vector2 rHat = new Vector2((float)(object2.getPosition().x - object1.getPosition().x), (float)(object2.getPosition().y - object1.getPosition().y));
					object1.getForce().x = (float) (force * rHat.x);
					object1.getForce().y = (float) (force * rHat.y);
					if(object1.overlaps(object2)) {
						if(object1.getMass() >= object2.getMass()) {
							object1.setMass(object1.getMass() + object2.getMass());
							
							//Perfectly inelastic collision
							Vector2 newVelocity = (object1.getVelocity().scl((float) object1.getMass()).add(object2.getVelocity().scl((float) object2.getMass())).scl((float) (1/(object1.getMass() + object2.getMass()))));
							
							
							object1.setVelocity(newVelocity);
							object1.updateRadius();
							objects.remove(object2);
						} else {
							object2.setMass(object1.getMass() + object2.getMass());
							Vector2 newVelocity = (object1.getVelocity().scl((float) object1.getMass()).add(object2.getVelocity().scl((float) object2.getMass())).scl((float) (1/(object1.getMass() + object2.getMass()))));

							object2.setVelocity(newVelocity);

							object2.updateRadius();
							objects.remove(object1);
						}
					}

				}
			}

			for (MassObject object : objects) {
				
				object.setAcceleration(new Vector2(
						(float) (object.getForce().x / object.getMass()),
						(float) (object.getForce().y / object.getMass())));
				object.getVelocity().x = object.getVelocity().x
						+ (object.getAcceleration().x * delta);
				object.getVelocity().y = object.getVelocity().y
						+ (object.getAcceleration().y * delta);

				object.getPosition().x += object.getVelocity().x * delta;
				object.getPosition().y += object.getVelocity().y * delta;
//				if(object.getPosition().x < -gameWidth / 2 || object.getPosition().x > gameWidth / 2) {
//					object.getVelocity().x *= -1;
//				}
//				
//				if(object.getPosition().y < -gameHeight / 2 || object.getPosition().y > gameHeight / 2) {
//					object.getVelocity().y *= -1;
//				}

				object.getForce().x = 0;
				object.getForce().y = 0;

				object.getAcceleration().x = 0;
				object.getAcceleration().y = 0;
			}
		}
	}
}
