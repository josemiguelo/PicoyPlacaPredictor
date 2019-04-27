package com.test.main;

import com.test.predictor.PicoyPlacaPredictor;

public class MainWithSetInputs {

    public static void main(String[] args) {
        try {
            PicoyPlacaPredictor predictor = new PicoyPlacaPredictor();

            // Example 1
            System.out.println(">>> Example 1: ");
            predictor.receiveInputs("AKJ-678", "2019-05-01", "07:45");
            predictor.startPrediction();
            predictor.showResults();

            // Example 2
            System.out.println("\n\n\n>>> Example 2: ");
            predictor.receiveInputs("LOP-0192", "2020-08-23", "12:03");
            predictor.startPrediction();
            predictor.showResults();

            // Example 2
            System.out.println("\n\n\n>>> Example 3: ");
            predictor.receiveInputs("AMJ-0197", "2019-10-17", "19:13");
            predictor.startPrediction();
            predictor.showResults();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
