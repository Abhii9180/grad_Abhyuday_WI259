package com;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

	static Calculator c1;
	@Test
	void Test()
	{
		assertTrue(true);
	}
	
	@BeforeAll
	//Here we can write before connecting database
	public  static void createObject() {
		 c1 = new Calculator();
	} 
	
	@AfterAll
	public  static void removeObject()
	//Here we can write disconnection after database 
	{
		c1 = null;
		System.out.println("***Finished*****");
		
	}
	
	@BeforeEach 
	//Here operations like crud , rollback and commit related things happens 
	
	public void abc()
	{
		System.out.println("***Before Each*****");
	}
	
	
	@AfterEach
	public void xyz()
	{
		System.out.println("***After Each*****");
	}
	
	@Test
	public void testAdd() {
		Calculator c1 = new Calculator();
		int actual = c1.add(10 , 30);
		int expected = 40;
		assertEquals(expected , actual);
	}
	
	@Test 
	public void testSub()
	{
		Calculator c1 = new Calculator();
		assertEquals(-10 , c1.sub(10 , 20));
		assertEquals(10 , c1.sub(30 , 20));
		assertEquals(20 , c1.sub(40 , 20));
		assertEquals(30 , c1.sub(50 , 20));
		
	}
	
	
	@Test 
	public void testMul()
	{
		System.out.println("Multiply");
		 assertAll(
			        ()->assertEquals(200 , c1.mul(10 , 20)),
					()->assertEquals(10 , c1.mul(30 , 20)),
					()->assertEquals(800 , c1.mul(40 , 20)),
					()->assertEquals(300 , c1.mul(50 , 20))
			    );
	}
	
	@Test
	public void testDivide()
	{
		
	}
	
	
	
	}
	

