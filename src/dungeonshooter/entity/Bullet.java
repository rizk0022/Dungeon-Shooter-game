package dungeonshooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;

public class Bullet implements Entity {
	private double angle;
	private Sprite sprite;
	private HitBox hitbox;
	
	public Bullet() {
		hitbox = new HitBox();
	}

	@Override
	public void update() 
	{
		double x = Math.cos( Math.toRadians( angle)) * 7;
		double y = Math.sin( Math.toRadians( angle)) * 7;
		hitbox.translate( x, y);
		
	}

	@Override
	public boolean isDrawable() 
	{
		return true;
	}

	@Override
	public String toString() 
	{
		return "bullets";
	}

	@Override
	public boolean hasHitbox() 
	{
		return true;
		
	}

	@Override
	public HitBox getHitBox() 
	{
		return hitbox;
		
	}
	public Sprite getDrawable() 
	{
		return sprite; 
	}

}
