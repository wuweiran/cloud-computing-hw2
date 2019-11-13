package edu.nyu.cs9223.hw2.rekognition;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;

public class CreateCollection {
    public static void main(String[] args) throws Exception {


        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();


        String collectionId = "MyCollection";
        System.out.println("Creating collection: " +
                collectionId );

        CreateCollectionRequest request = new CreateCollectionRequest()
                .withCollectionId(collectionId);

        CreateCollectionResult createCollectionResult = rekognitionClient.createCollection(request);
        System.out.println("CollectionArn : " +
                createCollectionResult.getCollectionArn());
        //aws:rekognition:us-east-1:651078706552:collection/MyCollection
        System.out.println("Status code : " +
                createCollectionResult.getStatusCode().toString());

    }
}
