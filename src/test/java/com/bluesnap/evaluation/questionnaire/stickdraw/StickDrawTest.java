package com.bluesnap.evaluation.questionnaire.stickdraw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StickDrawTest {
	@Test
	public void zeroTestCase() {
		StickDraw game = new StickDraw(0, new int[] {});
		assertFalse(game.win());
	}

	@Test
	public void singleDrawTestCase() {
		StickDraw game = new StickDraw(1, new int[] { 1, 2, 4 });
		assertTrue(game.win());
	}
	
	@Test
	public void RemnantVictoryTestCase() {
		StickDraw game = new StickDraw(3, new int[] { 2 });
		assertTrue(game.win());
	}
	
	@Test
	public void nextTurnVictoryTestCase() {
		StickDraw game = new StickDraw(3, new int[] { 1, 2, 4 });
		assertFalse(game.win());
	}
	
	@Test
	public void nextTurnDefeatTestCase() {
		StickDraw game = new StickDraw(5, new int[] { 1, 2, 4 });
		assertTrue(game.win());
	}
}
