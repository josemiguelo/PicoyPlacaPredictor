package com.test.predictor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PredictorTest {

    private PicoyPlacaPredictor predictor;
    private final String licensePlateNumberTest = "JKL-087";
    private final String dateTest = "2019-05-05";
    private final String timeTest = "08:43";

    @Before
    public final void init() {
        this.predictor = new PicoyPlacaPredictor();
        assertNotNull(this.predictor.getLicensePlateNumberHandler());
        assertNotNull(this.predictor.getDateHandler());
        assertNotNull(this.predictor.getTimeHandler());
    }

    @Test(expected = RuntimeException.class)
    public void testPredictorStartsPredictionWithoutReceiveInput() throws Exception {
        this.predictor.startPrediction();
    }

    @Test
    public void testAllInputsAreReceived() throws Exception {
        this.predictor.receiveInputs(licensePlateNumberTest, dateTest, timeTest);
        assertNotNull(this.predictor.getLicensePlateNumberHandler().getLicensePlateNumber());
        assertNotNull(this.predictor.getDateHandler().getDate());
        assertNotNull(this.predictor.getTimeHandler().getTime());
    }

    /*
    This test just hangs up!!!
    @Test
    public void testReceiveInputsByKeyboard() {
        this.predictor.getInputFromKeyboard();
        assertNotNull(this.predictor.getLicensePlateNumberHandler().getLicensePlateNumber());
        assertNotNull(this.predictor.getDateHandler().getDate());
        assertNotNull(this.predictor.getTimeHandler().getTime());
    }
    */

    @Test
    public void testAfterPredictionThereAreResults() throws Exception {
        this.predictor.receiveInputs(licensePlateNumberTest, dateTest, timeTest);
        this.predictor.startPrediction();
        assertNotNull(this.predictor.getResults());
    }

    @Test
    public void testThereAreResultsBeforePresentThem() throws Exception {
        this.predictor.receiveInputs(licensePlateNumberTest, dateTest, timeTest);
        this.predictor.startPrediction();
        assertNotNull(this.predictor.getResults());
        this.predictor.showResults();

    }
}
