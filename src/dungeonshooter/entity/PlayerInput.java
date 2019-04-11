package dungeonshooter.entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utility.InputAdapter;

public class PlayerInput extends InputAdapter<Canvas>{

	private double x;
	private double y;
	private boolean left=false;
	private boolean right=false;
	private boolean down=false;
	private boolean up=false;
	private boolean leftClick=false;
	private boolean rightClick=false;
	private boolean middleClick=false;
	private boolean space=false;
	private boolean shift=false;

	public boolean hasMoved() {
		return (left || right||down||up);
	}

	public int leftOrRight() {
		if (right) return 1;
		if (left)return -1;
		else return 0;
	}

	public int upOrDown() {
		if (down) return 1;
		if (up) return -1;
		else return 0;
	}

	public boolean leftClicked() {
		return leftClick;
	}

	public boolean rightClicked() {
		return rightClick;
	}

	public boolean middleClicked() {
		return middleClick;
	}

	public double x() {
		return x;

	}

	public double y() {
		return y;

	}


	protected void mousePressed(MouseEvent e) {

		leftClick=e.isPrimaryButtonDown();
		if(leftClick)
		rightClick=e.isSecondaryButtonDown();
		middleClick=e.isMiddleButtonDown();
		super.mousePressed(e);
	}

	protected void mouseReleased(MouseEvent e) {
		leftClick=false;
		rightClick=false;
		middleClick=false;
		super.mouseReleased(e);
	}

	public void changeKeyStatus (KeyCode key, boolean isPressed ) {
		switch (key) {
		case W:
			up=isPressed;
			break;
		case A:
			left=isPressed;
			break;

		case S:
			down=isPressed;
			break;

		case D:
			right=isPressed;
			break;
		case SHIFT:
			shift=isPressed;
			break;
		case SPACE:
			space=isPressed;
			break;
		}
	}

	@Override
	protected void keyPressed(KeyEvent key) {
		changeKeyStatus(key.getCode(), true);
	}
	
	@Override
	protected void keyReleased(KeyEvent key) {
		changeKeyStatus(key.getCode(), false);
	}

	@Override
	protected void moved(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	protected void dragged(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public boolean isSpace() {
		return(space);
	}
	
	public boolean isShift() {
		return (shift);
	}

}









