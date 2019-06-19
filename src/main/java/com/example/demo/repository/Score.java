package com.example.demo.repository;

import hex.genmodel.ModelMojoReader;
import hex.genmodel.MojoModel;
import hex.genmodel.MojoReaderBackend;
import hex.genmodel.MojoReaderBackendFactory;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class Score {

    public double getScore() throws PredictException, IOException {

        InputStream mojoIS = getClass().getClassLoader().getResourceAsStream("GBM_model_R_1560275251624_1.zip");
        MojoReaderBackend reader = MojoReaderBackendFactory.createReaderBackend(mojoIS, MojoReaderBackendFactory.CachingStrategy.MEMORY);
        MojoModel mojoModel = ModelMojoReader.readFrom(reader);
        EasyPredictModelWrapper model = new EasyPredictModelWrapper(mojoModel);

        RowData row = new RowData();
        row.put("AGE", "68");
        row.put("RACE", "2");
        row.put("DCAPS", "2");
        row.put("VOL", "0");

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
