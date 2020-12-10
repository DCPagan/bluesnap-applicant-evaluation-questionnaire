package com.bluesnap.evaluation.questionnaire.spiral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

public class SpiralIteratorTest {
	private SpiralIterator iter;
	private static final char[][] matrix = new char[][] {
		{ 'q', 'w', 'e', 'r' }, { 'a', 's', 'd', 'f' },
		{ 'z', 'x', 'c', 'v' } };
	private static final int length = 3;
	private static final int width = 4;

	@BeforeEach
	void init() {
		this.iter = new SpiralIterator(matrix, length, width);
	}

	@Test
	void emptyTest() {
		SpiralIterator iter = new SpiralIterator(new char[][] {}, 0, 0);
		assertFalse(iter.hasNext());
		assertThrows(NoSuchElementException.class, () -> iter.next());
	}

	@Test
	void getMatrixTest() {
		char[][] clone = this.iter.getMatrix();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				assertEquals(matrix[i][j], clone[i][j]);
			}
		}
	}

	@Test
	void stepTest() {
		SpiralIterator iter =
				new SpiralIterator(new char[][] { { 'a' } }, 1, 1);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), 'a');
		assertFalse(iter.hasNext());
		assertThrows(NoSuchElementException.class, () -> iter.next());
	}

	@Test
	void finalTest() {
		final String actual = iter.getCharacterStream()
			.map(c -> c.toString())
			.collect(joining(","));
		final String expected = "q,w,e,r,f,v,c,x,z,a,s,d";
		assertEquals(actual, expected);
	}
}
