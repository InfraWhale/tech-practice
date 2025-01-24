package org.example.chap08.account;

import org.example.chap08.configurations.MockConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAccountManager {

	@Test
	public void testFindAccountByUser() {
		MockLog logger = new MockLog(); // 실제로는 아무것도 하지 않음
		MockConfiguration configuration = new MockConfiguration(); // 얘도 깡통
		configuration.setSQL("SELECT * [...]");
		DefaultAccountManager2 am = new DefaultAccountManager2(logger, configuration);

		Account account = am.findAccountForUser("1234");
		
		// assert문은 여기에
	}

}