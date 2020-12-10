package com.bluesnap.evaluation.questionnaire.spiral;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.joining;

public class SpiralIterator implements Iterator<Character> {
	private char[][] matrix;
	private int length;
	private int width;
	private int[] cursor;
	private Direction direction;
	private boolean finishFlag;
	private int fromTop;
	private int fromBottom;
	private int fromLeft;
	private int fromRight;

	/**
	 * State object that specifies direction in which the cursor is
	 * currently iterating in the spiral, and to which directional
	 * iteration logic is delegated.
	 *
	 */
	private enum Direction {
		RIGHT {
			@Override
			public void step(SpiralIterator iter)
					throws NoSuchElementException {
				if (iter.cursor[1] < iter.width - iter.fromRight - 1) {
					iter.cursor[1]++;
				} else {
					iter.fromTop++;
					if (iter.hasNext()) {
						iter.direction = Direction.DOWN;
						iter.direction.step(iter);
					}
				}
			}
		},
		DOWN {
			@Override
			public void step(SpiralIterator iter)
					throws NoSuchElementException {
				if (iter.cursor[0] < iter.length - iter.fromBottom - 1) {
					iter.cursor[0]++;
				} else {
					iter.fromRight++;
					if (iter.hasNext()) {
						iter.direction = Direction.LEFT;
						iter.direction.step(iter);
					}
				}
			}
		},
		LEFT {
			@Override
			public void step(SpiralIterator iter)
					throws NoSuchElementException {
				if (iter.cursor[1] > iter.fromLeft) {
					iter.cursor[1]--;
				} else {
					iter.fromBottom++;
					if (iter.hasNext()) {
						iter.direction = Direction.UP;
						iter.direction.step(iter);
					}
				}
			}
		},
		UP {
			@Override
			public void step(SpiralIterator iter)
					throws NoSuchElementException {
				if (iter.cursor[0] > iter.fromTop) {
					iter.cursor[0]--;
				} else {
					iter.fromLeft++;
					if (iter.hasNext()) {
						iter.direction = Direction.RIGHT;
						iter.direction.step(iter);
					}
				}
			}
		};

		public abstract void step(SpiralIterator iter)
				throws NoSuchElementException;
	}

	public SpiralIterator(char[][] matrix, int length, int width) {
		this.matrix = SpiralIterator.deepClone2DArray(matrix);
		this.cursor = new int[] { 0, 0 };
		this.direction = Direction.RIGHT;
		this.finishFlag = false;
		this.length = length;
		this.width = width;
		this.fromTop = 0;
		this.fromBottom = 0;
		this.fromLeft = 0;
		this.fromRight = 0;
	}

	public static char[][] deepClone2DArray(char[][] matrix) {
		return Arrays.stream(matrix)
			.map((char[] chars) -> chars.clone())
			.toArray($ -> matrix.clone());
	}

	public char[][] getMatrix() {
		return SpiralIterator.deepClone2DArray(this.matrix);
	}

	@Override
	public boolean hasNext() {
		return this.fromTop + this.fromBottom != this.length
				&& this.fromLeft + this.fromRight != this.width;
	}

	@Override
	public Character next() throws NoSuchElementException {
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		} else {
			char nextChar = this.matrix[this.cursor[0]][this.cursor[1]];
			this.direction.step(this);
			return nextChar;
		}
	}

	public Stream<Character> getCharacterStream() {
		Spliterator<Character> spliterator =
				spliteratorUnknownSize(this, Spliterator.ORDERED);
		return StreamSupport.stream(spliterator, false);
	}

	public static void print_spiral(
			char[][] matrix, int length, int width) {
		SpiralIterator iter = new SpiralIterator(matrix, length, width);
		Stream<Character> charStream = iter.getCharacterStream();
		System.out.println(
				charStream.map(c -> c.toString())
					.collect(joining(",")));
	}
}
