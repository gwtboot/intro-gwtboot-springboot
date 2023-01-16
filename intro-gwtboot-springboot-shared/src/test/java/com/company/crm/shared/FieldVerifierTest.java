package com.company.crm.shared;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FieldVerifierTest {

	@Test
	void name_less_than_3_chars_not_valid() {
		boolean validName = FieldVerifier.isValidName("Lof");

		assertTrue(!validName);
	}

	@Test
	void name_longer_than_3_chars_valid() {
		boolean validName = FieldVerifier.isValidName("Lofi");

		assertTrue(validName);
	}

}
