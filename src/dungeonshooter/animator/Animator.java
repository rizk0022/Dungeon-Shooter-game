package dungeonshooter.animator;

import java.util.Iterator;
import java.util.List;

import dungeonshooter.CanvasMap;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.property.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animator extends AbstractAnimator{
	private Color background = Color.ANTIQUEWHITE;
	@Override
	
	
	void handle(long now, GraphicsContext gc) {
		// TODO Auto-generated method stub
		updateEntities();
		clearAndFill ( gc, background);
		drawEntities(gc);
		
	}
		
	public void proccessEntityList(Iterator<Entity> iterator, HitBox shapeHitBox) {
//		for (Entity e : iterator.hasNext()) {
//			next();
//		shapeHitBox.getHitBox();
//		
//		if (!map.inMap(bounds)) {
//		if (entity )
//		}
//		}
	}
	
	public void updateEntities() 
	{

		map.updateProjectilesList();
//		List<Entity> players = map.players();
		map.players();
		map.projectiles();
		map.staticShapes();
		
//		
//		for(Entity e : map.projectiles()) 
//		{
//			e.update();
//		}
//		
//		for(Entity e : map.players()) 
//		{
//			e.update();
//		}
		
		for(Entity e : map.staticShapes()) 
		{
			e.update();
		}
		
		
		if(map.getDrawBounds()) 
		{
			for(Entity e : map.projectiles()) 
			{
				e.getHitBox().getDrawable().setStroke(Color.RED);

			}
			
//			for(Entity e : map.players()) 
//			{
//				e.getHitBox().getDrawable().setStroke(Color.RED);
//
//			}
		}
		
//		for(Entity e: map.staticShapes()) 
//		{
//			
//			proccessEntityList(map.players().iterator(), e.getHitBox());
//			
//			proccessEntityList(map.projectiles().iterator(), e.getHitBox());
//		}
		
		
	}
}
