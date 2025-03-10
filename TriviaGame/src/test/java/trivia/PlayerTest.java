package trivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("John Doe");
    }

    @Test
    public void test_01_Purse() {
        assert player.getPurse() == 0;
        player.incrementPurse();
        assert player.getPurse() == 1;
    }

    @Test
    public void test_02_PenaltyBox() {
        assert !player.isInPenaltyBox();
        player.sendToPenaltyBox();
        assert player.isInPenaltyBox();
    }

    @Test
    public void test_03_Winning() {
        assert !player.hasWon(6);
        assert player.hasWon(0);
    }
}
