package org.example.chap02.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedWithValueSourceTest {
	private WordCounter wordCounter = new WordCounter();

	@ParameterizedTest
	@ValueSource(strings = { "Check three parameters",
			"JUnit in action" })
	void testWordsInSentence(String sentence) {
		assertEquals(3, wordCounter.countWords(sentence));
	}
}