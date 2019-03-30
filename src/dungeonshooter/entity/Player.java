package dungeonshooter.entity;

import dungeonshooter.CanvasMap;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;

public class Player implements Entity{

	private PlayerInput input;
	private CanvasMap board;

	public Player(double x, double y, double w, double h) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasHitbox() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Drawable<?> getDrawable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HitBox getHitBox() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInput(PlayerInput input) {
		this.input= input;
	}

	public void setMap(CanvasMap board) {
		// TODO Auto-generated method stub
		this.board=board;
	}

}
