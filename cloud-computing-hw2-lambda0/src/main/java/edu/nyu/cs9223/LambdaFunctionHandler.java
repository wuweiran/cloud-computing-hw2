package edu.nyu.cs9223;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.nyu.cs9223.hw2.database.DbClientFactory;
import edu.nyu.cs9223.hw2.model.VisitorRequest;
import edu.nyu.cs9223.hw2.model.VisitorResponse;


public class LambdaFunctionHandler implements RequestHandler<VisitorRequest, VisitorResponse> {

    @Override
    public VisitorResponse handleRequest(VisitorRequest visitorRequest, Context context) {
        final LambdaLogger logger = context.getLogger();
        logger.log("Input: " + visitorRequest.toString());
        try {
            logger.log("getValidator");
            Validator validator = new Validator(DbClientFactory.getInstance());
            logger.log("getValidator success!" );
            VisitorRequestReader visitorRequestReader = new VisitorRequestReader(visitorRequest);
            logger.log("Sending message to validator: " + visitorRequestReader.getUserId() + " " + visitorRequestReader.getOTP());
            String reply = validator.validate(visitorRequestReader.getUserId(), visitorRequestReader.getOTP());
            logger.log("Message sent, response size: " + reply.length());
            VisitorResponse response = new VisitorResponse(reply);
            return response;
        } catch (Exception e) {
            logger.log("error:" + e.toString());
        }
        return null;
    }

}
