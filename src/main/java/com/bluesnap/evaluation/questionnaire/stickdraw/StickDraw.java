package com.bluesnap.evaluation.questionnaire.stickdraw;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StickDraw {
	private int sticks;
	private int[] possibleMoves;

	public StickDraw(int sticks, int[] possibleMoves)
			throws IllegalArgumentException {
		if (sticks < 0) {
			throw new IllegalArgumentException(
					"Total number of sticks must be non-negative.");
		} else if (Arrays.stream(possibleMoves)
				.anyMatch(draw -> draw <= 0)) {
			throw new IllegalArgumentException(
					"Each draw option must be positive.");
		}
		this.sticks = sticks;
		this.possibleMoves = possibleMoves.clone();
	}

	public static boolean win(int sticks, int[] possibleMoves) {
		StickDraw game = new StickDraw(sticks, possibleMoves);
		return game.win();
	}

	/**
	 * Returns whether the game ends in a win for player 1s
	 * 
	 * @return Whether player 1 wins
	 */
	public boolean win() {
		return this.win(this.sticks);
	}

	/**
	 * Returns whether the game ends in a win, loss, or stalemate for the
	 * current player.
	 * 
	 * Base case: 0 sticks → Defeat
	 * 
	 * Base case: total sticks equals a draw option → Victory
	 * 
	 * Base case: total sticks is less than all draw options → Defeat
	 * 
	 * Inductive case: Either any draw leads to opponent's defeat, ergo
	 * Victory, or all draws lead to opponent's victory, ergo Defeat.
	 * 
	 * @param sticks Total number of sticks
	 * @param turn   Turn; true if player 1's turn, false otherwise
	 * @return Victory or Defeat
	 */
	public boolean win(int sticks) {
		if (sticks <= 0) {
			return false;
		} else if (Arrays.stream(possibleMoves)
				.allMatch(draw -> sticks < draw)) {
			return false;
		} else if (Arrays.stream(this.possibleMoves)
				.anyMatch(draw -> sticks == draw)) {
			return true;
		} else if (Arrays.stream(this.possibleMoves)
				.filter(draw -> sticks >= draw)
				.anyMatch(draw -> this.win(sticks - draw) == false)) {
			return true;
		} else {
			return false;
		}
	}
}
