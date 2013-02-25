import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LifegameView extends JFrame {
	private CellGridView gridView;
	
	public LifegameView(final Lifegame lifegame) {
		gridView = new CellGridView(lifegame.getCellGrid());
		getContentPane().add("Center", gridView);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lifegame.reset();
			}
		});
		getContentPane().add("South", resetButton);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void update() {
		gridView.repaint();
	}

	class CellGridView extends JPanel {
		private CellGrid cellGrid;

		CellGridView(CellGrid cellGrid) {
			this.cellGrid = cellGrid;
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			int gridWidth = getWidth() / cellGrid.getRowSize();
			int gridHeight = getHeight() / cellGrid.getColumnSize();

			for (int row = 0; row < cellGrid.getRowSize(); row++) {
				for (int column = 0; column < cellGrid.getColumnSize(); column++) {
					Cell cell = cellGrid.getCell(row, column);
					if (cell.isAlive()) {
						g.setColor(Color.BLUE);
					} else {
						g.setColor(Color.WHITE);
					}
					g.fill3DRect(gridWidth * column, gridHeight * row, gridWidth, gridHeight, true);
				}
			}
		}
	}
}
