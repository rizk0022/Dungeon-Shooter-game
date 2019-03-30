package dungeonshooter.entity.property;

import dungeonshooter.entity.Entity;
import dungeonshooter.entity.geometry.RectangleBounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.IntersectUtil;
import utility.Point;

public class HitBox implements Entity
{
	private Point prev;
	private RectangleBounds bounds;
	private Sprite sprite;
	private double[][] points;
	private double[] result;
	
	public HitBox() 
	{
		bounds = new RectangleBounds();
		points = new double[2][4];
		result = new double[4];
		prev = new Point();
		sprite = new Sprite()
		{ public void draw( GraphicsContext gc)
		{ 
			gc.setStroke( getStroke());
			gc.setLineWidth( getWidth());
			gc.strokeRect( bounds.x(), bounds.y(), bounds.w(), bounds.h()); 
		} 
		};
		sprite.setStroke( Color.RED).setWidth( 3);
		
	}
	
	public HitBox setBounds(RectangleBounds bounds)
	{
		this.bounds = bounds;
		return this;
	}
	
	public HitBox setBounds( double x, double y, double w, double h) 
	{
		bounds = new RectangleBounds(x, y, w, h);
		setBounds(bounds);
		return this;
	}
	
	public HitBox translate(double dx, double dy)
	{
		prev.move( bounds.startPos());
		bounds.translate(dx, dy);
		return this;
	}
	
	public HitBox undoTranslate() 
	{
		bounds.move(prev);
		return this;
	}
	
	public boolean containsBounds(HitBox box) 
	{
		bounds.contains(box.getBound());
		return true;
	}
	
	public boolean intersectBounds(HitBox box) 
	{
		bounds.intersects(box.getBound());
		return true;
	}
	
	public boolean intersectFull(HitBox box)
	{
		if(intersectBounds(box))
		{
			intersectFull(box.getPoints());
		}
		return true;
	}
	
	protected boolean intersectFull(double[][] otherPoints)
	{
		

		return false;
	}
	
	protected boolean hasIntersectFull()
	{
		return false;
	}
	protected double[][] getPoints()
	{
		return bounds.toArray( points);		
	}
	
	public void update()
	{
		
	}
	
	public boolean hasHitbox()
	{
		return true;  
	}
	
	public HitBox getHitBox()
	{
		return this;  
	}
	
	public boolean isDrawable()
	{
		return true; 
	}
	
	public Sprite getDrawable() 
	{
		return sprite;
	}
	
	public String toString()
	{
		return"testing";
	}
	
	public RectangleBounds getBound() 
	{
		return bounds;
	}
	
	public Point getPrev()
	{
		return prev;
	}

}
