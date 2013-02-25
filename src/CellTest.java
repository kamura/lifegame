import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CellTest {

	@Test
	public void 死んでいるセルに隣接する生きたセルがちょうど3つあれば次の世代が誕生する() {
		Cell cell = new Cell(false);
		List<Cell> neighbourCells = new ArrayList<Cell>();
		neighbourCells.add(new Cell(true));
		neighbourCells.add(new Cell(true));
		neighbourCells.add(new Cell(true));
		neighbourCells.add(new Cell(false));
		neighbourCells.add(new Cell(false));
		neighbourCells.add(new Cell(false));
		neighbourCells.add(new Cell(false));
		assertThat(cell.isNextGenerationAlive(neighbourCells), is(true));
	}

	@Test
	public void 生きているセルに隣接する生きたセルが2つか3つならば次の世代でも生存する() {
		{
			Cell cell = new Cell(true);
			List<Cell> neighbourCells = new ArrayList<Cell>();
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			assertThat(cell.isNextGenerationAlive(neighbourCells), is(true));
		}
		{
			Cell cell = new Cell(true);
			List<Cell> neighbourCells = new ArrayList<Cell>();
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			assertThat(cell.isNextGenerationAlive(neighbourCells), is(true));
		}
	}

	@Test
	public void 生きているセルに隣接する生きたセルが1つ以下ならば過疎により死滅する() {
		{
			Cell cell = new Cell(true);
			List<Cell> neighbourCells = new ArrayList<Cell>();
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			assertThat(cell.isNextGenerationAlive(neighbourCells), is(false));
		}
		{
			Cell cell = new Cell(true);
			List<Cell> neighbourCells = new ArrayList<Cell>();
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			assertThat(cell.isNextGenerationAlive(neighbourCells), is(false));
		}
	}

	@Test
	public void 生きているセルに隣接する生きたセルが4つ以上ならば過密により死滅する() {
		{
			Cell cell = new Cell(true);
			List<Cell> neighbourCells = new ArrayList<Cell>();
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			neighbourCells.add(new Cell(false));
			assertThat(cell.isNextGenerationAlive(neighbourCells), is(false));
		}
		{
			Cell cell = new Cell(true);
			List<Cell> neighbourCells = new ArrayList<Cell>();
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			neighbourCells.add(new Cell(true));
			assertThat(cell.isNextGenerationAlive(neighbourCells), is(false));
		}
	}
}
