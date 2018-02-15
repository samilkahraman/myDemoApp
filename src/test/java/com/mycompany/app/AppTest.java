package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.util.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testEmptyString() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertEquals(new App().sifrele(array,array,"a",5),"bbbbbb");
        // bir kaydır 5 kere genişlet
    }

    public void testDoesEmptySpacesCount() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1));
        assertNotSame(new App().sifrele(array,array,"aa bb",1),"bb cc");
        //boşlukları geçtiği için eşit çıkmadılar
    }

    public void testExpectsIndexOutofException() {
        ArrayList<Integer> array = new ArrayList<>();
        try{
            new App().sifrele(array,array,"aabb",5);
        }catch (IndexOutOfBoundsException e){

        }

    }

    public void testExpectsNullPointerExceptionIfOneOftheArrayEqualsNull() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1));
        try{
            new App().sifrele(array, null,"asd",1);
        }catch (NullPointerException e){

        }

    }

    public void testExpectsEmptyResultIfMessageBoxIsEmpty() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1));
        assertEquals(new App().sifrele(array,array,"",1),"");
        //şifrelenecek metnin boş gelmesi
    }

}
