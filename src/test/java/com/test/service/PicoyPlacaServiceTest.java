package com.test.service;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

public class PicoyPlacaServiceTest {

    private PicoyPlacaService service;

    @Before
    public final void init(){
        this.service = PicoyPlacaService.getInstance();
    }

    @Test
    public void testDayAppliesForPicoyPlaca() {

        // For the week
        assertTrue(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.MONDAY.isForUse());
        assertTrue(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.TUESDAY.isForUse());
        assertTrue(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.WEDNESDAY.isForUse());
        assertTrue(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.THURSDAY.isForUse());
        assertTrue(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.FRIDAY.isForUse());

        // For the weekend
        assertFalse(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY.isForUse());
        assertFalse(PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY.isForUse());
    }

    @Test
    public void testLastDigitCorrespondsToDay() {

        // Valid days
        assertTrue(this.service.lastDigitCorrespondsToDay("1", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.MONDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("2", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.MONDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("3", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.TUESDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("4", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.TUESDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("5", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.WEDNESDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("6", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.WEDNESDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("7", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.THURSDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("8", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.THURSDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("9", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.FRIDAY));
        assertTrue(this.service.lastDigitCorrespondsToDay("0", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.FRIDAY));

        // Invalid days
        assertFalse(this.service.lastDigitCorrespondsToDay("1", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("2", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("3", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("4", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("5", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("6", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("7", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("8", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("9", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("0", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SATURDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("1", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("2", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("3", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("4", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("5", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("6", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("7", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("8", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("9", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
        assertFalse(this.service.lastDigitCorrespondsToDay("0", PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.SUNDAY));
    }

    @Test
    public void testPicoyPlacaApplicabilityAccordingToTimeFrame() throws Exception {

        PicoyPlacaService.PicoyPlacaDailyScheduleConstraints day =  PicoyPlacaService.PicoyPlacaDailyScheduleConstraints.MONDAY;

        String startAtMorning = day.getStartTimeMorningAsString();
        String endAtMorning = day.getEndTimeMorningAsString();
        String startAtAfternoon = day.getStartTimeAfternoonAsString();
        String endAtAfternoon = day.getEndTimeAfternoonAsString();

        // before "Pico y Placa" at morning
        assertFalse(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, "06:59:00"));

        // right at the very start "Pico y Placa" at morning
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, "07:00:00"));

        // after start "Pico y Placa" at morning
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, "07:01:00"));

        // before end "Pico y Placa" at morning
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, "09:29:00"));

        // right at the very end "Pico y Placa" at morning
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, "09:30:00"));

        // right after end "Pico y Placa" at morning
        assertFalse(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, "09:31:00"));

        // before "Pico y Placa" at afternoon
        assertFalse(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, "16:29:00"));

        // right at the very start "Pico y Placa" at afternoon
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, "16:30:00"));

        // after start "Pico y Placa" at afternoon
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, "16:31:00"));

        // before end "Pico y Placa" at afternoon
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, "19:29:00"));

        // right at the very end "Pico y Placa" at afternoon
        assertTrue(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, "19:30:00"));

        // right after end "Pico y Placa" at afternoon
        assertFalse(this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, "19:31:00"));
    }

    @Test
    public void testGetTimeAsCalendarObject() throws Exception {

        String testTime = "09:00";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("HH:mm").parse(testTime));

        assertEquals(calendar, this.service.getCalendarByTimeString(testTime));
    }

    @Test
    public void testGetDayOfWeekWithStringDate() throws Exception {
        String validDate1 = "2019-04-25";
        assertEquals(Calendar.THURSDAY, this.service.getDayOfWeek(validDate1).getCalendarDayOfWeek());

        String validDate2 = "2019-04-26";
        assertEquals(Calendar.FRIDAY, this.service.getDayOfWeek(validDate2).getCalendarDayOfWeek());

        String validDate3 = "2019-04-27";
        assertEquals(Calendar.SATURDAY, this.service.getDayOfWeek(validDate3).getCalendarDayOfWeek());

        String validDate4 = "2019-04-28";
        assertEquals(Calendar.SUNDAY, this.service.getDayOfWeek(validDate4).getCalendarDayOfWeek());

        String validDate5 = "2019-04-29";
        assertEquals(Calendar.MONDAY, this.service.getDayOfWeek(validDate5).getCalendarDayOfWeek());

        String validDate6 = "2019-04-30";
        assertEquals(Calendar.TUESDAY, this.service.getDayOfWeek(validDate6).getCalendarDayOfWeek());

        String validDate7 = "2019-05-01";
        assertEquals(Calendar.WEDNESDAY, this.service.getDayOfWeek(validDate7).getCalendarDayOfWeek());

    }
}
