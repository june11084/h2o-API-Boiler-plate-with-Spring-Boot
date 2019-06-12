package com.example.demo.repository;

import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class Score {

    public double getScore() throws PredictException, IOException {
        EasyPredictModelWrapper model = new EasyPredictModelWrapper(MojoModel.load("C:\\Users\\june1\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\model\\GBM_model_R_1560275251624_1.zip"));

        RowData row = new RowData();
        row.put("AGE", "68");
        row.put("RACE", "2");
        row.put("DCAPS", "2");
        row.put("VOL", "0");
        row.put("GLEASON", "6");

        BinomialModelPrediction p = model.predictBinomial(row);
        System.out.println("Has penetrated the prostatic capsule (1=yes; 0=no): " + p.label);
        System.out.print("Class probabilities: ");
        for (int i = 0; i < p.classProbabilities.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(p.classProbabilities[i]);
        }

        return p.classProbabilities[1];
    }


}
