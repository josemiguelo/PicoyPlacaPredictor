package com.test.predictor;

import com.test.inputhandlers.date.DateHandler;
import com.test.inputhandlers.licenseplatenumber.LicensePlateNumberHandler;
import com.test.service.PicoyPlacaService;
import com.test.inputhandlers.time.TimeHandler;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PicoyPlacaPredictor {

    private LicensePlateNumberHandler licensePlateNumberHandler;
    private DateHandler dateHandler;
    private TimeHandler timeHandler;
    private PicoyPlacaService service;
    private String result;

    public PicoyPlacaPredictor() {
        this.licensePlateNumberHandler = new LicensePlateNumberHandler();
        this.dateHandler = new DateHandler();
        this.timeHandler = new TimeHandler();
        this.service = PicoyPlacaService.getInstance();
    }

    public void receiveInputs(String licensePlateNumber, String date, String time) throws Exception {
        verifyHandlersAreInitialized();
        this.licensePlateNumberHandler.receiveInput(licensePlateNumber);
        this.dateHandler.receiveInput(date);
        this.timeHandler.receiveInput(time);
    }

    private void verifyHandlersAreInitialized() {
        if (this.licensePlateNumberHandler == null || this.dateHandler == null || this.timeHandler == null) {
            throw new RuntimeException("Predictor has not been initialized");
        }
    }

    private void verifyInputReceived() {
        this.verifyHandlersAreInitialized();
        if (this.licensePlateNumberHandler.getLicensePlateNumber() == null || this.dateHandler.getDate() == null || this.timeHandler.getTime() == null) {
            throw new RuntimeException("Input has not been received");
        }
    }

    public void getInputFromKeyboard() {
        verifyHandlersAreInitialized();
        Scanner scanner = new Scanner(System.in);
        try {

            // Input for license plate number
            System.out.print("Input License Plate Number (Form: ABC-1234): ");
            String licensePlateNumberInput = null;
            if (scanner.hasNextLine()) {
                licensePlateNumberInput = scanner.nextLine();
            }
            this.licensePlateNumberHandler.receiveInput(licensePlateNumberInput);

            // Input for date
            System.out.print("Input Date (Form: yyyy-MM-dd): ");
            String dateInput = null;
            if (scanner.hasNextLine()) {
                dateInput = scanner.nextLine();
            }
            this.dateHandler.receiveInput(dateInput);

            // Input for time
            System.out.print("Input Time (Form HH:mm   24h): ");
            String timeInput = null;
            if (scanner.hasNextLine()) {
                timeInput = scanner.nextLine();
            }
            this.timeHandler.receiveInput(timeInput);

            scanner.close();
        } catch (Exception e) {
            System.out.println("\n\n>>> errors: ");
            System.out.println(e.getMessage());
        }
    }

    public void startPrediction() throws Exception {

        verifyInputReceived();

        // User input
        String licensePlateNumberInput = this.licensePlateNumberHandler.getLicensePlateNumber();
        String dateInput = this.dateHandler.getDate();
        String timeInput = this.timeHandler.getTime();
        String nameOfWeekDay = this.service.getDayOfWeek(dateInput).getName();

        // Applicability according to week day (weekdays are not applicable)
        boolean dayApplies = service.getDayOfWeek(dateInput).isForUse();
        if (!dayApplies) {
            this.result = this.dateHandler.getDate()
                    .concat(" is ").concat(service.getDayOfWeek(dateInput).getName()).concat(". ")
                    .concat("It doesn't apply for Pico y Placa. You can jump on the road!!");
            return;
        }

        // Applicability according to last digit and weekday
        String lastDigit = this.licensePlateNumberHandler.getLastDigit(licensePlateNumberInput);
        if (!service.lastDigitCorrespondsToDay(lastDigit, service.getDayOfWeek(dateInput))) {
            this.result = this.dateHandler.getDate()
                    .concat(" is ").concat(nameOfWeekDay).concat(". ")
                    .concat("It doesn't correspond to last digit ")
                    .concat(lastDigit).concat(" of your license plate number ").concat(licensePlateNumberInput)
                    .concat(". You can jump on the road!!");
            return;
        }

        // Applicability according to the hour
        DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String startAtMorning = service.getDayOfWeek(dateInput).getStartTimeMorning().format(timeFormatter1);
        String endAtMorning = service.getDayOfWeek(dateInput).getEndTimeMorning().format(timeFormatter1);
        String startAtAfternoon = service.getDayOfWeek(dateInput).getStartTimeAfternoon().format(timeFormatter1);
        String endAtAfternoon = service.getDayOfWeek(dateInput).getEndTimeAfternoon().format(timeFormatter1);

        boolean timeInputIsWithinPicoyPlacaMorningTimeFrame = this.service.timeIsWithinPicoyPlacaTimeFrame(startAtMorning, endAtMorning, timeInput);
        boolean timeInputIsWithinPicoyPlacaAfternoonTimeFrame = this.service.timeIsWithinPicoyPlacaTimeFrame(startAtAfternoon, endAtAfternoon, timeInput);
        if (!(timeInputIsWithinPicoyPlacaAfternoonTimeFrame || timeInputIsWithinPicoyPlacaMorningTimeFrame)) {
            this.result = timeInput
                    .concat(" is out of \"Pico y Placa\" time. ")
                    .concat("You can jump on the road!!");
            return;
        }

        // All constraints verification (if none of the other options are true)
        this.result = "You cannot be on the road at "
                .concat(nameOfWeekDay).concat(", ").concat(timeInput)
                .concat(" showing license plate number ").concat(licensePlateNumberInput)
                .concat(". It is Pico y Placa!!");
    }

    public void showResults() {
        if (this.result == null) {
            throw new RuntimeException("Results are null !!");
        }
        System.out.println("\n\n".concat("Results: ").concat(this.result));
    }

    public LicensePlateNumberHandler getLicensePlateNumberHandler() {
        return this.licensePlateNumberHandler;
    }

    public DateHandler getDateHandler() {
        return this.dateHandler;
    }

    public TimeHandler getTimeHandler() {
        return this.timeHandler;
    }

    public String getResults() {
        return this.result;
    }

}
