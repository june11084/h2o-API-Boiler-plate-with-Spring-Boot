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
        EasyPredictModelWrapper model = new EasyPredictModelWrapper(MojoModel.load("./src/main/java/com/example/demo/model/GBM_model_R_1560275251624_1.zip"));

//        File file = new File("src/main/java/com/example/demo/model");
//        for(String filename : file.list()){
//            System.out.println((filename));
//        }
//
//        try {
//            ZipFile zipFile = new ZipFile("src/main/java/com/example/demo/model/GBM_model_R_1560275251624_1.zip");
//            Enumeration<?> enu = zipFile.entries();
//            while (enu.hasMoreElements()) {
//                ZipEntry zipEntry = (ZipEntry) enu.nextElement();
//                String name = zipEntry.getName();
//                long size = zipEntry.getSize();
//                long compressedSize = zipEntry.getCompressedSize();
//                System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n",
//                        name, size, compressedSize);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
