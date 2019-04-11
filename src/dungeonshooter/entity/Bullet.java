package dungeonshooter.entity;

import dungeonshooter.entity.geometry.RectangleBounds;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet implements Entity {
	protected static final Image BULLET = new Image("file:assets/bullet/b_3.png");
	private double angle;
	private Sprite sprite;
	private HitBox hitbox;

	public Bullet(double angle, double x, double y) {

		this(angle, x, y,6,6);
	}

	public Bullet(double angle, double x, double y, double h, double w) {
		this.angle = angle;
		hitbox = new HitBox();
		hitbox.setBounds(x, y, w, h);
		sprite = new Sprite() {
			private RectangleBounds bounds = hitbox.getBound();

			public void draw(GraphicsContext gc) {
				gc.drawImage(BULLET, bounds.x(), bounds.y(), bounds.w(), bounds.h());
			}
		};

	}

	@Override
	public void update() {
		double x = Math.cos(Math.toRadians(angle)) * 7;
		double y = Math.sin(Math.toRadians(angle)) * 7;
		hitbox.translate(x, y);

	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public String toString() {
		return "bullets";
	}

	@Override
	public boolean hasHitbox() {
		return true;

	}

	@Override
	public HitBox getHitBox() {
		return hitbox;

	}

	public Sprite getDrawable() {
		return sprite;
	}

}
