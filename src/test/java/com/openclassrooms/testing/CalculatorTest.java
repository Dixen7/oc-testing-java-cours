package com.openclassrooms.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {
	
	private Calculator calculatorUnderTest;
	private static Instant startedAt;
	
	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now(); 
	}
	
	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel aprés tous les tests");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}
	
	@BeforeEach
	public void initCalculator() {
		calculatorUnderTest = new Calculator();
		System.out.println("Appel avant chaque test");
	}
	
	@AfterEach
	public void undefCalculator() {
		System.out.println("Appel aprés chaque test");
		calculatorUnderTest = null;
	}

	@Test
	public void testAddTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;
		// Act
		int somme = calculatorUnderTest.add(a, b);
		// Assert
		assertEquals(5, somme);
	}
	
	@Test 
	public void multiplyTwoNumbers() {
		int a = 3;
		int b = 3;
		int produit = calculatorUnderTest.multiply(a, b);
		
		assertEquals(9, produit);
	}
	
	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = {1, 2, 42, 1001, 5089})
	public void multiply_shouldReturnZero(int arg) {
		// ARRANGE tout est prêt !
		
		// ACT Multiplier par zéro
		final int actualResult = calculatorUnderTest.multiply(arg, 0);
		//ASSERT ça vaut toujours zéro ! 
		assertEquals(0, actualResult);
	}
	
	@ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
	@CsvSource({"1,1,2","3,2,5","100,50,150"})
	public void multiply_shouldReturnTheSum(int arg1, int arg2, int expectResult) {
		// ARRANGE tout est prêt !
		
		// ACT
		final int actualResult = calculatorUnderTest.add(arg1, arg2);
		//ASSERT
		assertEquals(expectResult, actualResult);
	}
	
	@Test
	@Timeout(1)
	public void longCalcul_computeIn1second() {
		// ARRANGE
		
		// ACT
		calculatorUnderTest.longCalculation();
		//ASSERT

	}
}
