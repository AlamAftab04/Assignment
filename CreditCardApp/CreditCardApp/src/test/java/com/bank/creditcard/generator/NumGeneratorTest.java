package com.bank.creditcard.generator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.cc.generator.NumGenerator;
import com.bank.cc.model.CreditCategory;

@RunWith(MockitoJUnitRunner.class)
public class NumGeneratorTest {
	
	@Test
	public void testRandomNumberGeneration() {
		int generateRandomNumber = NumGenerator.generateRandomNumber();
		System.out.println(generateRandomNumber);
		System.out.println(CreditCategory.MEDIUM.toString());
		assertTrue(generateRandomNumber>0);
	}
}
