package de.glenn.fizzbuzz;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * Created by glenn on 25.04.16.
 */
public class MainTest {

    @Test
    public void testFizzBuzz() {

        assertEquals( "FizzBuzz ", Main.fizzBuzz( 0, 0 ).toString() );
        assertEquals( "1 2 Fizz ", Main.fizzBuzz( 1, 3 ).toString() );

        assertEquals( "", Main.fizzBuzz( 1, 0 ).toString() );

        assertEquals( "14 FizzBuzz 16 ", Main.fizzBuzz( 14, 16 ).toString() );

    }


    @Test
    public void testFizzBuzzFrom1To2() {

        assertEquals( "1 2 ", Main.fizzBuzz( 1, 2 ).toString() );

    }

    @Test
    public void testFizzBuzzFrom0To0() {

        assertEquals( "FizzBuzz ", Main.fizzBuzz( 0, 0 ).toString() );

    }

    @Test
    public void testFizzBuzzFrom1To3() {

        assertEquals( "1 2 Fizz ", Main.fizzBuzz( 1, 3 ).toString() );

    }

    @Test
    public void testFizzBuzzFrom1To5() {

        assertEquals( "1 2 Fizz 4 Buzz ", Main.fizzBuzz( 1, 5 ).toString() );

    }

    @Test
    public void testFizzBuzzMultiplesOf15() {

        for ( int i = 1; i < 9; i++ ) {

            assertEquals( "FizzBuzz ", Main.fizzBuzz( 3*5*i, 3*5*i ).toString() );

        }

    }

    @Test
    public void testFizzBuzzMultiplesOf3AndNot5() {

        for ( int i = 1; i < 9; i++ ) {

            if ( i*3 % 5 != 0 ) {
                assertEquals( "Fizz ", Main.fizzBuzz( 3*i, 3*i ).toString() );
            }

        }

    }

    @Test
    public void testFizzBuzzMultiplesOf5AndNot3() {

        for ( int i = 1; i < 9; i++ ) {

            if ( i*5 % 3 != 0 ) {
                assertEquals( "Buzz ", Main.fizzBuzz( 5*i, 5*i ).toString() );
            }

        }

    }

    @Test
    public void testFizzBuzzNotDivisibleBy5Or3() {

        for ( int i = 1; i < 100; i++ ) {

            if ( i % 3 != 0 && i % 5 != 0 ) {
                assertEquals( i + " ", Main.fizzBuzz( i, i ).toString() );
            }

        }

    }

    @Test
    public void testAskUserForIntAndValidate() {

        System.setIn( new ByteArrayInputStream( "1".getBytes() ) );
        Integer i = Main.askUserForIntAndValidate( "start" );

        assertEquals( new Integer( 1 ) , i );

    }


    @Test
    public void testAskUserForIntAndValidate10Chars() {

        System.setIn( new ByteArrayInputStream( "1234567890".getBytes() ) );
        Integer i = Main.askUserForIntAndValidate( "start" );

        assertEquals( null , i );

    }

    @Test
    public void testAskUserForIntAndValidate9Chars() {

        System.setIn( new ByteArrayInputStream( "123456789".getBytes() ) );
        Integer i = Main.askUserForIntAndValidate( "start" );

        assertEquals( new Integer( 123456789 ) , i );

    }

    @Test
    public void testAskUserForIntAndValidateAA() {

        System.setIn( new ByteArrayInputStream( "AA".getBytes() ) );
        Integer i = Main.askUserForIntAndValidate( "start" );

        assertEquals( null , i );

    }


    @Test
    public void testAskUserForVersion1() {

        System.setIn( new ByteArrayInputStream( "1".getBytes() ) );
        Byte b = Main.askUserForVersion();

        assertEquals( new Byte( "1" ), b );

    }

    @Test
    public void testAskUserForVersion2() {

        System.setIn( new ByteArrayInputStream( "2".getBytes() ) );
        Byte b = Main.askUserForVersion();

        assertEquals( new Byte( "2" ), b );

    }

    @Test
    public void testAskUserForVersion3() {

        System.setIn( new ByteArrayInputStream( "3".getBytes() ) );
        Byte b = Main.askUserForVersion();

        assertEquals( new Byte( "3" ), b );

    }

    @Test
    public void testAskUserForVersion0() {

        System.setIn( new ByteArrayInputStream( "0".getBytes() ) );
        Byte b = Main.askUserForVersion();

        assertEquals( null, b );

    }

    @Test
    public void testAskUserForVersion4() {

        System.setIn( new ByteArrayInputStream( "4".getBytes() ) );
        Byte b = Main.askUserForVersion();

        assertEquals( null, b );

    }

    @Test
    public void testAskUserForVersionA() {

        System.setIn( new ByteArrayInputStream( "A".getBytes() ) );
        Byte b = Main.askUserForVersion();

        assertEquals( null, b );

    }

}