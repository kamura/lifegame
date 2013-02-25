import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

public class Lifegame {
	private static final int ROW_SIZE = 50;
	private static final int COLUMN_SIZE = 50;
	private static final int SLEEP_MSEC = 400;
	private CellGrid cellGrid;
	private LifegameView view;
	private ScheduledExecutorService scheduler;

	public Lifegame() {
		cellGrid = new CellGrid(ROW_SIZE, COLUMN_SIZE);
		view = new LifegameView(this);
	}

	public void start() {
		Runnable gotoNextGenerationTask = new Runnable() {
			@Override
			public void run() {
				view.update();
				cellGrid.prepareNextGeneration();
				cellGrid.gotoNextGeneration();
			}
		};
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(gotoNextGenerationTask, 0, SLEEP_MSEC,
				TimeUnit.MILLISECONDS);
	}

	public void stop() {
		scheduler.shutdown();
		scheduler = null;
	}

	public void reset() {
		stop();
		cellGrid.reset();
		start();
	}

	public CellGrid getCellGrid() {
		return cellGrid;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Lifegame game = new Lifegame();
				game.start();
			}
		});
	}
}