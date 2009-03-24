/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.motionbox.requester;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import java.util.logging.*;

import org.jruby.exceptions.RaiseException;

/**
 *
 * @author Topper
 */
@MessageDriven(mappedName = "tester_queue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class RequesterBean implements MessageListener {
    private static Logger logger = Logger.getLogger("com.motionbox.requester");
    private RubyMessageRuntime runtime = new RubyMessageRuntime();
    private Message messageStore;

    public RequesterBean() {}

    public void onMessage(Message message) {
        logger.info("message received");
        TextMessage tm = (TextMessage)message;
        setMessage(message);
        logger.info("telling ruby to retrieve the message");
        runtime.eval("MessageHandler.handle_message!");
    }

    public Message setMessage(Message message) {
        messageStore = message;
        return message;
    }

    public Message getMessage() {
        return messageStore;
    }
    
}
