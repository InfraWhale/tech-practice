package org.example.chap02.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedWithCsvFileSourceTest {
	private WordCounter wordCounter = new WordCounter();

	@ParameterizedTest
	@CsvFileSource(resources = "/word_counter.csv")
	void testWordsInSentence(int expected, String sentence) {
		assertEquals(expected, wordCounter.countWords(sentence));
	}
}