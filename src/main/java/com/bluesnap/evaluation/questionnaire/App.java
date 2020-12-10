package com.bluesnap.evaluation.questionnaire;

import static com.bluesnap.evaluation.questionnaire.spiral.SpiralIterator.print_spiral;
import static com.bluesnap.evaluation.questionnaire.stickdraw.StickDraw.win;

public class App {
	public static void main(String[] args) {
		print_spiral(
				new char[][] {
					{ 'q', 'w', 'e', 'r' }, { 'a', 's', 'd', 'f' },
					{ 'z', 'x', 'c', 'v' } },
				3, 4);
		System.out.println(win(3, new int[] { 1, 4, 2 }));
		System.out.println(win(5, new int[] { 1, 4, 2 }));
	}
}
