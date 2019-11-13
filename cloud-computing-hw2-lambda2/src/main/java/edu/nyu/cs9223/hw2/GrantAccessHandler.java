package edu.nyu.cs9223.hw2;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.nyu.cs9223.hw2.database.DbClient;
import edu.nyu.cs9223.hw2.database.DbClientFactory;
import edu.nyu.cs9223.hw2.model.GrantAccessRequest;
import edu.nyu.cs9223.hw2.authentication.OtpGenerator;
import edu.nyu.cs9223.hw2.notification.SnsClient;
import edu.nyu.cs9223.hw2.notification.SnsClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wwrus
 */
public class GrantAccessHandler implements RequestHandler<GrantAccessRequest, Object> {

    @Override
    public Object handleRequest(GrantAccessRequest grantAccessRequest, Context context) {
        DbClient dbClient = DbClientFactory.getInstance();
        Map<String, String> map = new HashMap<>(5);
        map.put("face_id", grantAccessRequest.getFaceId());
        map.put("name", grantAccessRequest.getName());
        map.put("phone_number", grantAccessRequest.getPhoneNumber());
        String otp = OtpGenerator.generate();
        saveOtp(otp, grantAccessRequest.getFaceId());
        dbClient.putItem("visitors", map);
        SnsClient snsClient = SnsClientFactory.getInstance();
        snsClient.sendSms("Your One-time Password is: " + otp, grantAccessRequest.getPhoneNumber());
        return null;
    }

    private void saveOtp(String otp, String faceId) {
        DbClient dbClient = DbClientFactory.getInstance();
        Map<String, String> lookUp = dbClient.getItem("passcodes", "face_id", faceId);
        lookUp.put("otp", otp);
        dbClient.updateItem("passcodes", "face_id", faceId, lookUp);
    }
}
