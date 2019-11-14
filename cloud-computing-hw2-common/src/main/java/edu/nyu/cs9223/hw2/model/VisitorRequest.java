package edu.nyu.cs9223.hw2.model;

import java.io.Serializable;


/**
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/ljp1k3l5m2-1.0.0/BotRequest" target="_top">AWS API
 * Documentation</a>
 */
public class VisitorRequest implements Serializable, Cloneable {

    private String OTP;
    private String userId;


    public VisitorRequest(){
        
    }
    
    public VisitorRequest(String userId, String OTP){
        this.userId = userId;
        this.OTP = OTP;
    }
    
    public String getOTP() {
        return this.OTP;
    }


    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
    
    public VisitorRequest OTP(String OTP) {
        setOTP(OTP);
        return this;
    }
    
    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public VisitorRequest userId(String userId) {
        setUserId(userId);
        return this;
    }
    
    @Override
    public String toString(){
        return userId + ":" + OTP;
    }
}
