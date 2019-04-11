package dungeonshooter.entity.property;

import dungeonshooter.entity.Entity;
import dungeonshooter.entity.geometry.RectangleBounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.IntersectUtil;
import utility.Point;

public class HitBox implements Entity {
	private Point prev;
	private RectangleBounds bounds;
	private Sprite sprite;
	private double[][] points;
	private double[] result;

	public HitBox() {
		bounds = new RectangleBounds();
		points = new double[2][4];
		result = new double[4];
		prev = new Point();
		sprite = new Sprite() {
			public void draw(GraphicsContext gc) {
				gc.setStroke(getStroke());
				gc.setLineWidth(getWidth());
				gc.strokeRect(bounds.x(), bounds.y(), bounds.w(), bounds.h());
			}
		};
		sprite.setStroke(Color.RED).setWidth(3);

	}

	public HitBox setBounds(RectangleBounds bounds) {
		this.bounds = bounds;
		return this;
	}

	public HitBox setBounds(double x, double y, double w, double h) {
		bounds = new RectangleBounds(x, y, w, h);
		setBounds(bounds);
		return this;
	}

	public HitBox translate(double dx, double dy) {
		prev.move(bounds.startPos());
		bounds.translate(dx, dy);
		return this;
	}

	public HitBox undoTranslate() {
		bounds.move(prev);
		return this;
	}

	public boolean containsBounds(HitBox box) {
		return bounds.contains(box.getBound());
	}

	public boolean intersectBounds(HitBox box) {
		return bounds.intersects(box.getBound());
	}

//Below method: should be called after intersectBounds has returned true as this method is more expensive. With in this method call intersectFull( box.getPoints());
//	a. in protected intersectFull check for intersection between all line segments of points in current hitbox and points passes as argument.
//	i. This method will have 2 for loops one nested in other. Outer loop will go through current hitbox points while inner loop goes through otherpoints.
//	ii. In the inner loop IntersectUtil.getIntersection() method to check if lines cross.
//	iii. If the result of IntersectUtil.getIntersection() is true check to see if result[2] <= 1 is also true. We want to make sure lines are crossing each other on segments specified. If both are true return true otherwise continue looking.
//	iv. Return false if nothing found.
	public boolean intersectFull(HitBox box) {
		return intersectFull(box.getPoints());
	}

	protected boolean intersectFull(double[][] otherPoints) {
		points = getPoints();

		for (int i = 0, j = points[0].length - 1; i < points[0].length; i++, j = i - 1) {
			for (int k = 0, l = otherPoints[0].length - 1; k < otherPoints[0].length; k++, l = k - 1) {
				boolean intersect = IntersectUtil.getIntersection(result, points[0][i], points[1][i], points[0][j],
						points[1][j], otherPoints[0][k], otherPoints[1][k], otherPoints[0][l], otherPoints[1][l]);
				if (intersect && result[2] <= 1)
					return true;
			}
		}
		return false;
	}

	protected boolean hasIntersectFull() {
		return false;
	}

	protected double[][] getPoints() {
		return bounds.toArray(points);
	}

	public void update() {

	}

	public boolean hasHitbox() {
		return true;
	}

	public HitBox getHitBox() {
		return this;
	}

	public boolean isDrawable() {
		return true;
	}

	public Sprite getDrawable() {
		return sprite;
	}

	public String toString() {
		return "testing";
	}

	public RectangleBounds getBound() {
		return bounds;
	}

	public Point getPrev() {
		return prev;
	}

}
