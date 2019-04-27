package com.test.main;

import com.test.predictor.PicoyPlacaPredictor;

public class MainWithKeyboardInput {
    public static void main(String[] args) {
        try {
            PicoyPlacaPredictor predictor = new PicoyPlacaPredictor();
            predictor.getInputFromKeyboard();
            predictor.startPrediction();
            predictor.showResults();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
