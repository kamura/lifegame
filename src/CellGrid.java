import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CellGrid {
	private int rowSize = 10;
	private int columnSize = 10;

	private Cell[][] cellArray;

	public CellGrid(int rowSize, int columnSize) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		createCellGrid();
	}
	
	public Cell getCell(int row, int column) {
		return cellArray[row][column];
	}
	
	private void createCellGrid() {
		Random random = new Random();
		
		cellArray = new Cell[getRowSize()][getColumnSize()];
		for (int row = 0; row < getRowSize(); row++) {
			for (int column = 0; column < getColumnSize(); column++) {
				boolean isAlive = random.nextBoolean();
				cellArray[row][column] = new Cell(isAlive);
			}
		}
	}

	public void prepareNextGeneration() {
		for (int row = 0; row < getRowSize(); row++) {
			for (int column = 0; column < getColumnSize(); column++) {
				getCell(row, column).prepareNextGeneration(getNeightbourCells(row, column));
			}
		}
	}

	public void gotoNextGeneration() {
		for (int row = 0; row < getRowSize(); row++) {
			for (int column = 0; column < getColumnSize(); column++) {
				getCell(row, column).gotoNextGeneration();
			}
		}
	}

	private List<Cell> getNeightbourCells(int cellRow, int cellColumn) {
		List<Cell> neighbourCells = new ArrayList<Cell>();
		for (int row = cellRow - 1; row <= cellRow + 1; row++) {
			for (int column = cellColumn - 1; column <= cellColumn + 1; column++) {
				if (isInCellGrid(row, column)
					 && !isSameGridPosition(cellRow, cellColumn, row, column)) {
					neighbourCells.add(getCell(row, column));
				}
			}
		}
		return neighbourCells;
	}

	private boolean isSameGridPosition(int cellRow, int cellColumn, int row, int column) {
		return row == cellRow && column == cellColumn;
	}

	private boolean isInCellGrid(int row, int column) {
		return row >= 0 && row < getRowSize() && column >=0 && column < getColumnSize();
	}
	
	public void reset() {
		createCellGrid();
	}

	public int getRowSize() {
		return rowSize;
	}

	public int getColumnSize() {
		return columnSize;
	}
}