package com.example.carmi.gittest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PhoneCorrectTest {
    @Test
    public void doesPhoneHaveTenDigits(){
        Appointment a = new Appointment(0,0,0,0);
        assertEquals(true, a.checkPhone("2082063868"));
    }
}
