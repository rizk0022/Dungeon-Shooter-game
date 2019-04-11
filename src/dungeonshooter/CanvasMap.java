package dungeonshooter;

import java.util.ArrayList;
import java.util.List;

import dungeonshooter.animator.AbstractAnimator;
import dungeonshooter.entity.Bullet;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.PolyShape;
import dungeonshooter.entity.property.HitBox;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


public class CanvasMap 
{
	private Canvas map;
	private BooleanProperty drawBounds;
	private BooleanProperty drawFPS;
	private List<Entity> players;
	private List<Entity> projectiles;
	private PolyShape border;
	private AbstractAnimator animator;
	private List<Entity> buffer;
	private List<PolyShape> staticShapes;


	public CanvasMap()
	{

		drawFPS = new SimpleBooleanProperty( false);
		drawBounds = new SimpleBooleanProperty( true);
		projectiles = new ArrayList<Entity>(500);
		buffer = new ArrayList<Entity>(500);
		staticShapes = new ArrayList<>(50);
		players = new ArrayList<>(1);
		border = new PolyShape();
		border.getDrawable().setFill( new ImagePattern( new Image( "file:assets/floor/pavin.png"), 0, 0, 256, 256, false));
	}

	public List<PolyShape> staticShapes()
	{
		return staticShapes;
	}
	public List<Entity> players()
	{
		return players;
	}
	public List<Entity> projectiles()
	{
		return projectiles;
	}

	public CanvasMap setDrawingCanvas(Canvas map) {
		if (map==null) {
			throw new NullPointerException("Canvas is null");
		} 
		this.map = map;
		this.map.widthProperty().addListener((obs, oldVal, newVal) -> {
			border.setPoints(0, 0, w(), 0,
					w(), h(), 0, h());
		});
		this.map.heightProperty().addListener((obs, oldVal, newVal) -> {
			border.setPoints(0, 0, w(), 0,
					w(), h(), 0, h());
		});
		border.setPoints(0, 0, w(), 0,
				w(), h(), 0, h());
		return this;
	}



	public PolyShape getMapShape()
	{
		return border;
	}


	public void addSampleShapes()
	{
		PolyShape p1 = new PolyShape().randomize(500, 500, 120, 6, 10);
		PolyShape p2 = new PolyShape().randomize(150, 500, 120, 6, 10);
		PolyShape p3 = new PolyShape().randomize(150, 150, 120, 6, 10);
		PolyShape p4 = new PolyShape().randomize(500, 150, 120, 6, 10);

		staticShapes.add(p1);
		staticShapes.add(p2);
		staticShapes.add(p3);
		staticShapes.add(p4);
	}


	public BooleanProperty drawFPSProperty(){
		return drawFPS;
	}

	public boolean getDrawFPS(){
		return drawFPS.get();
	}

	public BooleanProperty drawBoundsProperty(){
		return drawBounds;
	}

	public boolean getDrawBounds(){
		return drawBounds.get();
	}



	public CanvasMap setAnimator( AbstractAnimator newAnimator)
	{
		if( animator != null){
			stop();
		}
		animator = newAnimator;
		return this;
	}

	/**
	 * create a method called start.
	 * start the animator. {@link AnimationTimer#start()}
	 */
	public void start(){
		animator.start();
	}

	/**
	 * create a method called stop.
	 * stop the animator. {@link AnimationTimer#stop()}
	 */
	public void stop(){
		animator.stop();
	}

	/**
	 * create a method called getCanvas.
	 * get the JavaFX {@link Canvas} node 
	 * @return {@link Canvas} node 
	 */
	public Canvas getCanvas(){
		return map;
	}


	public GraphicsContext gc(){
		return map.getGraphicsContext2D();
	}

	public double h(){
		return map.getHeight();
	}

	public double w(){
		return map.getWidth();
	}

	public void fireBullet(Bullet bullet ) 
	{
		buffer.add(bullet);
		updateProjectilesList();
	} 

	public void updateProjectilesList() 
	{
		projectiles.addAll(buffer);
		buffer.clear();
	}

	public boolean inMap(HitBox hitbox) 
	{
		return border.getHitBox().containsBounds(hitbox);
	}




}
