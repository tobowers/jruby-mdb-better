# To change this template, choose Tools | Templates
# and open the template in the editor.
require 'java'
require 'yaml'
require 'net/http'

include_class "com.motionbox.requester.RequesterBean"

class MessageHandler

  def self.handle_message!
    if defined?(RequesterBean)
      puts "the java class is defined"
    end
    if defined?(RequesterBean.getMessage)
      puts "the getMessage method is defined"
      msg = RequesterBean.getMessage
      puts "body is: " + msg.getText()
      url = YAML.load(msg)
      Net::HTTP.get_print URI.parse(url[:url])
    end
#    message_body = serialized_messageed_message.get_content.get_data.inject("") { |body, byte| body << byte }
#    puts message_body
    # I will get the message and do whatever you want with it
    # edit TesterMessageHandlerBean.java to change the message queue I listen to
  end

#  def onMessage(msg)
#    puts "I'm handling this bitch"
#    self.class.handle_message(msg)
#  end

end
