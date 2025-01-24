package org.example.chap08.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAccountServiceEasyMock {

	private AccountManager mockAccountManager;

	@BeforeEach
	public void setUp() {
		mockAccountManager = createMock("mockAccountManager", AccountManager.class);
	}

	@Test
	public void testTransferOk() {
		Account senderAccount = new Account("1", 200);
		Account beneficiaryAccount = new Account("2", 100);

		// 기대를 정의한다.
		mockAccountManager.updateAccount(senderAccount);
		mockAccountManager.updateAccount(beneficiaryAccount);

		expect(mockAccountManager.findAccountForUser("1"))
				.andReturn(senderAccount);
		expect(mockAccountManager.findAccountForUser("2"))
				.andReturn(beneficiaryAccount);

		// 기대정의가 끝나면 replay를 호출한다.
		replay(mockAccountManager);

		AccountService accountService = new AccountService();
		accountService.setAccountManager(mockAccountManager);
		accountService.transfer("1", "2", 50);

		assertEquals(150, senderAccount.getBalance());
		assertEquals(150, beneficiaryAccount.getBalance());
	}

	@AfterEach
	public void tearDown() {
		verify(mockAccountManager);
	}
}