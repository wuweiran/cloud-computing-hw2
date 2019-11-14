package edu.nyu.cs9223;

import edu.nyu.cs9223.hw2.model.VisitorRequest;

/**
 * @author 
 */

public class VisitorRequestReader {

    private final String userId;
    private final String OTP;

    public VisitorRequestReader(final VisitorRequest visitorRequest) {
        OTP = visitorRequest.getOTP().toLowerCase();
        userId = visitorRequest.getUserId().toLowerCase();    
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getOTP() {
        return OTP;
    }
}
