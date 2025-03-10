
package trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class GameTest {
//	@Test
//	public void caracterizationTest() {
//		// runs 10.000 "random" games to see the output of old and new code mathces
//		for (int seed = 1; seed < 10_000; seed++) {
//			testSeed(seed, false);
//		}
//	}
//
//	private void testSeed(int seed, boolean printExpected) {
//		String expectedOutput = extractOutput(new Random(seed), new GameOld());
//		if (printExpected) {
//			System.out.println(expectedOutput);
//		}
//		String actualOutput = extractOutput(new Random(seed), new Game());
//		assertEquals(expectedOutput, actualOutput);
//	}
//
//	@Test
//	@Disabled("enable back and set a particular seed to see the output")
//	public void oneSeed() {
//		testSeed(1, true);
//	}
//
//	private String extractOutput(Random rand, IGame aGame) {
//		PrintStream old = System.out;
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try (PrintStream inmemory = new PrintStream(baos)) {
//			// WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
//			System.setOut(inmemory);
//
//			aGame.add("Chet");
//			aGame.add("Pat");
//			aGame.add("Sue");
//
//			boolean notAWinner = false;
//			do {
//				aGame.roll(rand.nextInt(5) + 1);
//
//				if (rand.nextInt(9) == 7) {
//					notAWinner = aGame.wrongAnswer();
//				} else {
//					notAWinner = aGame.handleCorrectAnswer();
//				}
//
//			} while (notAWinner);
//		} finally {
//			System.setOut(old);
//		}
//
//		return baos.toString();
//	}

	@Test
	public void test_01_MaxPlayer(){
		Game game = new Game();
		game.add("Chet");
		game.add("Pat");
		game.add("Sue");
		game.add("John");
		game.add("Doe");
		game.add("Jane");
		assertEquals(6, game.howManyPlayers());

		// On teste l'exception levée lors de l'ajout d'un 7ème joueur
		assertThrows(IllegalStateException.class, () -> game.add("Jack"), "Maximum number of players reached");
	}

	@Test
	public void test_02_SameNamePlayer(){
		Game game = new Game();
		game.add("Chet");
		game.add("Pat");
		game.add("Sue");
		assertEquals(3, game.howManyPlayers());

		// On teste l'exception levée lors de l'ajout d'un joueur avec le même nom
		assertThrows(IllegalArgumentException.class, () -> game.add("Chet"), "Player name already exists");
	}

	@Test
	public void test_03_Roll(){

	}
}
