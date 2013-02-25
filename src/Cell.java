import java.util.List;



public class Cell {
	private boolean isAlive = false;
	private boolean isNextGenerationAlive = false;
	
	public Cell(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void prepareNextGeneration(List<Cell> neighbourCells) {
		this.isNextGenerationAlive = isNextGenerationAlive(neighbourCells);
	}
	
	public void gotoNextGeneration() {
		this.isAlive = this.isNextGenerationAlive;
	}

	public boolean isNextGenerationAlive(List<Cell> neighbourCells) {
		int aliveNeighbourCellCount = countAliveCells(neighbourCells);
		if(this.isAlive) {
			if (aliveNeighbourCellCount != 2 && aliveNeighbourCellCount != 3) {
				return false;
			} else {
				return true;
			}
		} else {
			if (aliveNeighbourCellCount == 3) {
				return true;
			}
		}
		return false;
	}

	private int countAliveCells(List<Cell> neighbourCells) {
		int count = 0;
		for (Cell cell: neighbourCells) {
			if (cell.isAlive()) {
				count++;
			}
		}
		return count;
	}
}
