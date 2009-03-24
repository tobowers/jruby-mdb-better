/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.motionbox.requester;

import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.exceptions.RaiseException;
import java.util.ArrayList;
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
        runtime = JavaEmbedUtils.initialize(new ArrayList());
        evaler = JavaEmbedUtils.newRuntimeAdapter();

        try {
          evaler.eval(runtime, "require 'META-INF/boot.rb'");
        }
        catch (RaiseException re) {
            logger.severe("Raise Exception on eval require: " + re.getMessage());
            throw re;
        }
    }

    public void eval (String r) {
        evaler.eval(runtime, r);
    }

}
