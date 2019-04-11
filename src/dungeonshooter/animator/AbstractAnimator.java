package dungeonshooter.animator;

import java.util.function.Consumer;
import dungeonshooter.CanvasMap;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.FpsCounter;
import dungeonshooter.entity.PolyShape;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractAnimator extends AnimationTimer{

	protected CanvasMap map;
	private FpsCounter fps;


	public AbstractAnimator(){
		map = new CanvasMap();
		fps = new FpsCounter(10,25);
		fps.getDrawable().setFill(Color.BLACK).setStroke(Color.WHITE).setWidth(1);

	}

	public void setCanvas( CanvasMap map){
		this.map = map;
	}

	public void clearAndFill (GraphicsContext gc,Color background) {
		gc.setFill(background);
		gc.clearRect(0, 0, map.w(), map.h());
		gc.fillRect(0, 0, map.w(), map.h());

	}

	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
		GraphicsContext gc = map.gc();

		if (map.getDrawFPS()) {
			fps.calculateFPS(now);
		}
		handle( now, gc);
		if(map.getDrawBounds() )
		{

			for(PolyShape shape: map.staticShapes())
			{

				if(map.getDrawBounds())
				{
					shape.getHitBox().getBound();
				}
				if(map.getDrawFPS())
				{
					fps.getDrawable().draw(gc);;
				}
			}

		}
	}

	abstract void handle( long now, GraphicsContext gc);

	public void drawEntities(GraphicsContext gc) { 
		Consumer<Entity> draw = new Consumer<Entity>() {

			@Override
			public void accept(Entity e) {
				if(e.isDrawable())
				{
					e.getDrawable().draw(gc); 

					if(map.getDrawBounds()) 	{
						e.getHitBox().getDrawable().draw(gc);
					}

				}

			}
		};


		draw.accept(map.getMapShape());


		for(Entity e: map.staticShapes()) 
		{
			draw.accept(e);
		}

		for(Entity e : map.projectiles()) 
		{
			draw.accept(e);
		}

		for(Entity e : map.players()) 
		{
			draw.accept(e);
		}
	}	 

}
