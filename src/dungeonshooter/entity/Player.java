package dungeonshooter.entity;

import dungeonshooter.CanvasMap;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import utility.Point;

public class Player implements Entity{

	private PlayerInput input;
	private CanvasMap map;
	private Rotate rotationPlayer;
	private double angle;
	private double playerFrame=0;
	private double muzzleFrame=0;
	private Point pos;
	private Point dimension;
	private Point prev;
	private Sprite sprite;
	private HitBox hitbox;


	public Player(double x, double y, double w, double h) {
		// TODO Auto-generated constructor stub
		rotationPlayer = new Rotate();
		map = new CanvasMap();
		pos = new Point((x-w)/2,(y-5)/2);
		prev = new Point(pos);

		dimension = new Point(w,h);
		double size = h * .74;
		hitbox = new HitBox().setBounds( pos.x() + dimension.x() * .303 - size / 2, pos.y() +
				dimension.y() * .58 - size / 2, size, size);

		sprite = new Sprite(){

			//player and muzzle each have 20 and 16 set of images than can be loaded 
			private final Image[] PLAYER = new Image[20];
			private final Image[] MUZZLE = new Image[16];
			{
				//load the images
				for( int i = 0; i < PLAYER.length; i++){
					PLAYER[i] = new Image( "file:assets/rifle/idle/survivor-idle_rifle_" + i + ".png");
				}
				for( int i = 0; i < MUZZLE.length; i++){
					MUZZLE[i] = new Image( "file:assets/muzzle_flashs/m_" + i + ".png");
				}
			}
			public void draw( GraphicsContext gc){
				gc.save();
				//rotate gc for drawing
				gc.setTransform( rotationPlayer.getMxx(), rotationPlayer.getMyx(),
						rotationPlayer.getMxy(), rotationPlayer.getMyy(),rotationPlayer.getTx(), rotationPlayer.getTy());
				//if left click display fire animation 
				if( input.leftClicked()){
					gc.drawImage( MUZZLE[(int) muzzleFrame], getRifleMuzzleX() - 8, getRifleMuzzleY() - 25, 50, 50);
					//this number is how fast the next frame of fire animation will be drawn. The higher the faster.
					muzzleFrame += .5;
				}
				//darw player image
				gc.drawImage( PLAYER[(int) playerFrame], pos.x(), pos.y(), dimension.x(), dimension.y());
				gc.restore();
				// this number is how fast the next frame of player animation will be drawn. The higher the faster. 
				playerFrame += 0.25;
				//reset frame counts if reach the max frame 
				if( playerFrame >= PLAYER.length){
					playerFrame = 0;
				}
				if( muzzleFrame >= MUZZLE.length || !input.leftClicked()){
					muzzleFrame = 0;
				}
			}
		};
	}
	public Player setMap(CanvasMap map) {
		// TODO Auto-generated method stub
		this.map=map;
		return this;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		double x,y;
		boolean speed;
		calculateAngles();
		if (input.hasMoved()) {
			speed = input.isShift();
			x = input.leftOrRight() * 2;
			y = input.upOrDown() *2;
			if (speed) x *=7.4;
			else x *=2.4;
			if (speed) y *=7.4;
			else y *=2.4;

			prev.move(pos);
			pos.translate(x, y);
			hitbox.translate(x, y);
		}
		if (input.leftClicked()) {
			Point2D muzzle = rotationPlayer.transform( getRifleMuzzleX(), getRifleMuzzleY());
			map.fireBullet( new Bullet( this.angle, muzzle.getX(), muzzle.getY()));
		}
	}
	public double getPlayerCenterX() {
		return pos.x() + dimension.x() * .303;
	}

	public double getPlayerCenterY() {
		return pos.y() + dimension.y() * .58;
	}
	private double getRifleMuzzleY() {
		// TODO Auto-generated method stub
		return pos.y() + dimension.y() * .73;
	}

	private double getRifleMuzzleX() {
		// TODO Auto-generated method stub
		return pos.x() + dimension.x() * .93;
	}

	private void calculateAngles() {
		// TODO Auto-generated method stub
		angle=Math.toDegrees( Math.atan2( input.y() - getPlayerCenterY(), input.x() - getPlayerCenterX())); 	
		rotationPlayer.setAngle(angle);
		rotationPlayer.setPivotX(getPlayerCenterX()); 
		rotationPlayer.setPivotY (getPlayerCenterY()); 

	}

	public void setInput(PlayerInput input) {
		this.input= input;
	}



	public void stepBack() {
		hitbox.undoTranslate();
		pos.move(prev);

	}

	@Override
	public boolean hasHitbox() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Sprite getDrawable() {
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
		return hitbox;
	}


}
