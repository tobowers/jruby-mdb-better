/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.motionbox.requester;

import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaEmbedUtils;


import java.util.List;
import java.util.ArrayList;

import javax.jms.Message;

import java.util.logging.*;

/**
 *
 * @author Topper
 */
public class RubyMessageRuntime {
    private Ruby runtime;
    private RubyRuntimeAdapter evaler;
    private static Logger logger = Logger.getLogger("com.motionbox.testMessageHandler");


    public RubyMessageRuntime() {
//        new ArrayList(new String[] ["foo", "bar"])
        List loadPaths = new ArrayList();
        loadPaths.add("META-INF/ruby");
        runtime = JavaEmbedUtils.initialize(loadPaths);
        evaler = JavaEmbedUtils.newRuntimeAdapter();
    }

    public void handleMessage (Message message) {
        logger.info("so now rubyruntime is processing it");
        IRubyObject rubyMessage = JavaEmbedUtils.javaToRuby(runtime, message);
        logger.info("so now the rubyMessage has been converted");
        IRubyObject rubyReceiver = evaler.eval(runtime, "require 'META-INF/boot.rb'");

        logger.info("sending to ruby");

        JavaEmbedUtils.invokeMethod(runtime, rubyReceiver, "handle_message!", new Object[] {rubyMessage}, null);
    }


    public void eval (String r) {
        evaler.eval(runtime, r);
    }

}
