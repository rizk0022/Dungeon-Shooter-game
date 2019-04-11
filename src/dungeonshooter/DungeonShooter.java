package dungeonshooter;

import dungeonshooter.animator.AbstractAnimator;
import dungeonshooter.animator.Animator;
import dungeonshooter.entity.Player;
import dungeonshooter.entity.PlayerInput;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DungeonShooter extends Application{


	private double width = 700, height = 700;

	private Canvas canvas;

	private PlayerInput input;

	private BorderPane root;

	private CanvasMap board;
	private Color background = Color.LIGHTPINK;



	@Override
	public void init() throws Exception{

		canvas = new Canvas();
		board = new CanvasMap();
		input = new PlayerInput();
		root = new BorderPane();

		Player player = new Player(650, 250, 70, 46);
		AbstractAnimator animator = new Animator();


		input.forceFocusWhenMouseEnters(canvas);
		input.registerMouseMovment(canvas);
		input.registerMouseClick(canvas);
		input.registerKey(canvas);

		player.setInput(input);
		player.setMap(board);


		animator.setCanvas(board);
		board.setDrawingCanvas(canvas);
		board.setAnimator(animator);
		board.addSampleShapes();
		board.players().add(player);


		ToolBar statusBar = createStatusBar();
		ToolBar optionsBar = createOptionsBar();

		root = new BorderPane();
		root.setTop( optionsBar);
		root.setCenter( board.getCanvas());
		root.setBottom( statusBar);

		board.getCanvas().widthProperty()
		.bind( root.widthProperty());
		board.getCanvas().heightProperty()
		.bind( root.heightProperty()
				.subtract( statusBar.heightProperty())
				.subtract( optionsBar.heightProperty()));

	}


	@Override
	public void start( Stage primaryStage) throws Exception{
		//scene holds all JavaFX components that need to be displayed in Stage
		Scene scene = new Scene( root, width, height, background );
		primaryStage.setScene( scene);
		primaryStage.setTitle( "Dongeon Shooter");
		// when escape key is pressed close the application
		primaryStage.addEventHandler( KeyEvent.KEY_RELEASED, ( KeyEvent event) -> {
			if( KeyCode.ESCAPE == event.getCode()){
				primaryStage.hide();
			}
		});
		// display the JavaFX application
		primaryStage.show();
		//select first index of animatorsBox as start,
		//this will also sets the new animator as the lambda we setup will be triggered
		//animatorsBox.getSelectionModel().select( 1); TODO
		board.start();
	}

	/**
	 * this method is called at the very end when the application is about to exit.
	 * this method is used to stop or release any resources used during the application.
	 */
	@Override
	public void stop() throws Exception{
		board.stop();
	}

	/**
	 * create a {@link ToolBar} that represent the menu bar at the top of the application.
	 * @return customized {@link ToolBar}
	 */
	public ToolBar createOptionsBar(){
		Button startButton = createButton( "Start", a -> board.start());

		Button stopButton = createButton( "Stop", a -> board.stop());

		Pane menuBarFiller1 = new Pane();
		Pane menuBarFiller2 = new Pane();
		HBox.setHgrow( menuBarFiller1, Priority.ALWAYS);
		HBox.setHgrow( menuBarFiller2, Priority.ALWAYS);

		MenuButton options = new MenuButton( "Options", null,
				createCheckMenuItem( "FPS", true, board.drawFPSProperty()),
				createCheckMenuItem( "Bounds", false, board.drawBoundsProperty()));

		return new ToolBar(
				startButton, stopButton,
				menuBarFiller1,
				menuBarFiller2,
				options);
	}


	public ToolBar createStatusBar(){
		Label mouseCoordLabel = new Label( "(0,0)");
		Label dragCoordLabel = new Label( "(0,0)");

		return new ToolBar(
				new Label( "Mouse: "), mouseCoordLabel,
				new Label( "Drag: "), dragCoordLabel);
	}


	public CheckMenuItem createCheckMenuItem( String text, boolean isSelected, BooleanProperty binding){
		CheckMenuItem check = new CheckMenuItem( text);
		binding.bind( check.selectedProperty());
		check.setSelected( isSelected);
		return check;
	}


	public Button createButton( String text, EventHandler< ActionEvent> action){
		Button button = new Button( text);
		button.setOnAction( action);
		return button;
	}


	public static void main( String[] args){
		// launch( args); method starts the javaFX application.
		// some IDEs are capable of starting JavaFX without this method.
		launch( args);
	}
}
