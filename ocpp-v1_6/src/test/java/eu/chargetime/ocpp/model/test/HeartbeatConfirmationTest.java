package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.model.core.HeartbeatConfirmation;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class HeartbeatConfirmationTest {
    HeartbeatConfirmation confirmation;

    @Before
    public void setUp() throws Exception {
        confirmation = new HeartbeatConfirmation();
    }

    @Test
    public void setCurrentTime_now_currentTimeIsSet() {
        // Given
        Calendar now = Calendar.getInstance();

        // When
        confirmation.setCurrentTime(now);

        // Then
        assertThat(confirmation.objCurrentTime(), equalTo(now));
    }

    @Test
    public void getCurrentTime_currentTimeIsSet_returnsFormattetString() {
        // Given
        String expected = "2016-01-01T01:01:01.001Z";
        Calendar someDate = new Calendar.Builder().setDate(2016,00,01)
                .setTimeOfDay(01,01,01,001).setTimeZone(TimeZone.getTimeZone("GMT+00:00")).build();
        confirmation.setCurrentTime(someDate);

        // When
        String result = confirmation.getCurrentTime();

        // Then
        assertThat(result, equalTo(expected));
    }

    @Test
    public void validate_currentTimeIsSet_returnsTrue() {
        // Given
        confirmation.setCurrentTime(Calendar.getInstance());

        // When
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(false));
    }
}