# To change this template, choose Tools | Templates
# and open the template in the editor.
require 'java'
require 'yaml'
require 'net/http'

def handle_message(msg)
  puts "message received"
  puts msg.inspect
end

class MessageHandler

  def self.handle_message!
    
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
