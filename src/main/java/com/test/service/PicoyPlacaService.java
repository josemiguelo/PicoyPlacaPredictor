package com.test.service;

import com.test.exceptions.InvalidDateException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class PicoyPlacaService {

    private static PicoyPlacaService ourInstance = new PicoyPlacaService();

    public static PicoyPlacaService getInstance() {
        return ourInstance;
    }

    private PicoyPlacaService() {
    }

    public boolean lastDigitCorrespondsToDay(String lastDigit, PicoyPlacaDailyScheduleConstraints day) {
        return Arrays.asList(day.getLastDigits()).contains(lastDigit);
    }

    public boolean timeIsWithinPicoyPlacaTimeFrame(String start, String end, String timeInput) throws Exception {
        Date startDate = this.getCalendarByTimeString(start).getTime();
        Date endDate = this.getCalendarByTimeString(end).getTime();
        Date inputDate = this.getCalendarByTimeString(timeInput).getTime();
        return (inputDate.equals(startDate) || inputDate.after(startDate)) && (inputDate.equals(endDate) || inputDate.before(endDate));
    }

    public PicoyPlacaDailyScheduleConstraints getDayOfWeek(String dateString) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            throw new InvalidDateException("Date is invalid", e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWekk = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWekk) {

            case Calendar.SUNDAY:
                return PicoyPlacaDailyScheduleConstraints.SUNDAY;

            case Calendar.MONDAY:
                return PicoyPlacaDailyScheduleConstraints.MONDAY;

            case Calendar.TUESDAY:
                return PicoyPlacaDailyScheduleConstraints.TUESDAY;

            case Calendar.WEDNESDAY:
                return PicoyPlacaDailyScheduleConstraints.WEDNESDAY;

            case Calendar.THURSDAY:
                return PicoyPlacaDailyScheduleConstraints.THURSDAY;

            case Calendar.FRIDAY:
                return PicoyPlacaDailyScheduleConstraints.FRIDAY;

            case Calendar.SATURDAY:
                return PicoyPlacaDailyScheduleConstraints.SATURDAY;
        }

        throw new InvalidDateException("Invalid date of week");
    }

    public Calendar getCalendarByTimeString(String time) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("HH:mm").parse(time));
        return calendar;
    }

    public enum PicoyPlacaDailyScheduleConstraints {

        MONDAY(
                "Monday",
                true,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{"1", "2"},
                Calendar.MONDAY
        ),
        TUESDAY("Tuesday",
                true,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{"3", "4"},
                Calendar.TUESDAY
        ),
        WEDNESDAY("Wednesday",
                true,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{"5", "6"},
                Calendar.WEDNESDAY
        ),
        THURSDAY("Thursday",
                true,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{"7", "8"},
                Calendar.THURSDAY
        ),
        FRIDAY("Friday",
                true,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{"9", "0"},
                Calendar.FRIDAY
        ),
        SATURDAY("Saturday",
                false,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{},
                Calendar.SATURDAY
        ),
        SUNDAY("Sunday",
                false,
                LocalTime.of(7, 0, 0), LocalTime.of(9, 30, 0),
                LocalTime.of(16, 30, 0), LocalTime.of(19, 30, 0),
                new String[]{},
                Calendar.SUNDAY
        );

        // Name of day
        private String name;

        // Day is being used in "Pico y Placa" schedules
        private boolean forUse;

        // Start/end schedules for every day
        private LocalTime startTimeMorning;
        private LocalTime endTimeMorning;
        private LocalTime startTimeAfternoon;
        private LocalTime endTimeAfternoon;

        // Last digits of a license plate number that apply at a certain day
        private String[] lastDigits;

        // Day of week (Calendar constants)
        private int calendarDayOfWeek;

        // Time formatter
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        PicoyPlacaDailyScheduleConstraints(
                String name,
                boolean forUse,
                LocalTime startTimeMorning,
                LocalTime endTimeMorning,
                LocalTime startTimeAfternoon,
                LocalTime endTimeAfternoon,
                String[] lastDigits,
                int calendarDayOfWeek
        ) {
            this.forUse = forUse;
            this.name = name;
            this.startTimeMorning = startTimeMorning;
            this.endTimeMorning = endTimeMorning;
            this.startTimeAfternoon = startTimeAfternoon;
            this.endTimeAfternoon = endTimeAfternoon;
            this.lastDigits = lastDigits;
            this.calendarDayOfWeek = calendarDayOfWeek;
        }

        public boolean isForUse() {
            return forUse;
        }

        public String getName() {
            return name;
        }

        public LocalTime getStartTimeMorning() {
            return startTimeMorning;
        }

        public String getStartTimeMorningAsString() {
            return this.getFormatTimeAsString(this.getStartTimeMorning());
        }

        public LocalTime getEndTimeMorning() {
            return endTimeMorning;
        }

        public String getEndTimeMorningAsString() {
            return this.getFormatTimeAsString(this.getEndTimeMorning());
        }

        public LocalTime getStartTimeAfternoon() {
            return startTimeAfternoon;
        }

        public String getStartTimeAfternoonAsString() {
            return this.getFormatTimeAsString(this.getStartTimeAfternoon());
        }

        public LocalTime getEndTimeAfternoon() {
            return endTimeAfternoon;
        }

        public String getEndTimeAfternoonAsString() {
            return this.getFormatTimeAsString(this.getEndTimeAfternoon());
        }

        public String[] getLastDigits() {
            return lastDigits;
        }

        public int getCalendarDayOfWeek() {
            return calendarDayOfWeek;
        }

        public String getFormatTimeAsString(LocalTime time) {
            return time.format(timeFormatter);
        }


    }

}
