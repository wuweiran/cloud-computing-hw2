package edu.nyu.cs9223.hw2;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;
import com.amazonaws.util.StringUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.nyu.cs9223.hw2.authentication.OtpGenerator;
import edu.nyu.cs9223.hw2.database.DbClient;
import edu.nyu.cs9223.hw2.database.DbClientFactory;
import edu.nyu.cs9223.hw2.notification.SnsClient;
import edu.nyu.cs9223.hw2.notification.SnsClientFactory;

import java.util.Map;

/**
 * @author wuweiran
 */
public class RecognitionHandler implements RequestHandler<KinesisEvent, Object> {

    private static final double KNOWN_FACE_THRESHOLD = 50;

    private static final String ADMIN_PHONE_NUMBER = "+13478854596";

    @Override
    public Void handleRequest(KinesisEvent event, Context context)
    {
        for(KinesisEventRecord eventRecord : event.getRecords()) {
            JsonObject faceData = JsonParser.parseString(new String(eventRecord.getKinesis().getData().array()))
                    .getAsJsonObject();
            if (isKnownFace(faceData)) {
                String faceId = getFaceId(faceData);
                String phoneNumber = getPhoneNumberByFaceId(faceId);
                if (StringUtils.isNullOrEmpty(phoneNumber)) {
                    String otp = OtpGenerator.generate();
                    saveOtp(otp, faceId);
                    SnsClient snsClient = SnsClientFactory.getInstance();
                    snsClient.sendSms("Your One-time Password is: " + otp, phoneNumber);
                }
            } else {
                SnsClient snsClient = SnsClientFactory.getInstance();
                snsClient.sendSms("An unknown visitor is trying to login in.", ADMIN_PHONE_NUMBER);
            }
        }
        return null;
    }

    private void saveOtp(String otp, String faceId) {
        DbClient dbClient = DbClientFactory.getInstance();
        Map<String, String> lookUp = dbClient.getItem("visitors", "face_id", faceId);
        lookUp.put("otp", otp);
        dbClient.updateItem("visitors", "face_id", faceId, lookUp);
    }

    private boolean isKnownFace(JsonObject faceData) {
        return faceData.get("FaceSearchResponse").getAsJsonObject().get("MatchedFaces")
                .getAsJsonObject().get("Similarity").getAsDouble() > KNOWN_FACE_THRESHOLD;
    }

    private String getFaceId(JsonObject faceData) {
        return faceData.get("FaceSearchResponse").getAsJsonObject().get("MatchedFaces")
                .getAsJsonObject().get("Face").getAsJsonObject().get("FaceId").getAsString();
    }

    private String getPhoneNumberByFaceId(String faceId) {
        DbClient dbClient = DbClientFactory.getInstance();
        return dbClient.getItem("visitors", "face_id", faceId).get("phone_number");
    }

}
