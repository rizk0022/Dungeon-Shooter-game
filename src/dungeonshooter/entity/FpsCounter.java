package dungeonshooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utility.Point;

public class FpsCounter implements Entity {

	public static final double ONE_SECOND = 1000000000L;
	public static final double HALF_SECOND = ONE_SECOND / 2F;
	private Font fpsFont;
	private String fpsDisplay;
	private int frameCount;
	private double lastTime;
	private Sprite sprite;
	private double x;
	private double y;
	private Paint fill;
	private Paint stroke;
	private double strokeWidth;

	public FpsCounter(double x, double y) {
		sprite = new Sprite() {
			public void draw(GraphicsContext gc) 
			{
				Font temp;
				temp = gc.getFont();

				gc.setFont(fpsFont);
				gc.setFill(getFill());
				gc.fillText(fpsDisplay, x, y);
				gc.setStroke(getFill());
				gc.setLineWidth(getWidth());
				gc.strokeText(fpsDisplay, x, y);
				gc.setFont(temp);


			}

		};
		setPos(x, y);
		setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24));
	}


	public void calculateFPS(long now) {
		if (now - lastTime > HALF_SECOND) {
			fpsDisplay = Integer.toString(frameCount * 2);
			frameCount = 0;
			lastTime = now;

		}
		frameCount++;
	}

	public FpsCounter setFont(Font font) {
		fpsFont = font;
		return this;

	}

	public FpsCounter setPos(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
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
		return sprite;
	}

	@Override
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public HitBox getHitBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
