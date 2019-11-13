package edu.nyu.cs9223.hw2.rekognition;

public class StreamStarter {

    public static void main(String[] args) {


        String streamProcessorName="face-recognition-processor";
        String kinesisVideoStreamArn="arn:aws:kinesisvideo:us-east-1:651078706552:stream/my-stream/1573330844791";
        String kinesisDataStreamArn="arn:aws:kinesis:us-east-1:651078706552:stream/face-stream";
        String roleArn="arn:aws:iam::651078706552:role/kinesis-rekognition";
        String collectionId="MyCollection";
        Float matchThreshold=50F;

        try {
            StreamManager sm= new StreamManager(streamProcessorName,
                    kinesisVideoStreamArn,
                    kinesisDataStreamArn,
                    roleArn,
                    collectionId,
                    matchThreshold);
            //sm.createStreamProcessor();
            //sm.startStreamProcessor();
            sm.deleteStreamProcessor();
            //sm.deleteStreamProcessor();
            //sm.stopStreamProcessor();
            //sm.listStreamProcessors();
            //sm.describeStreamProcessor();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

