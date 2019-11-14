package edu.nyu.cs9223.hw2.model;

import java.io.Serializable;

/**
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/ljp1k3l5m2-1.0.0/BotRequest" target="_top">AWS API
 * Documentation</a>
 */
public class VisitorResponse implements Serializable, Cloneable {

    private String text;

    public VisitorResponse(String s){
        text = s;
    }
  
    public String getText() {
        return this.text;
    }


    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
