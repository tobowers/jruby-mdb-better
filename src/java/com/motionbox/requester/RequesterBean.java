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

import org.jruby.javasupport.JavaEmbedUtils;


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

    public RequesterBean() {}

    public void onMessage(Message message) {
        logger.info("message received");
        RubyMessageRuntime runtime = new RubyMessageRuntime();
        runtime.handleMessage(message);
    }
}
