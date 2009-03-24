# To change this template, choose Tools | Templates
# and open the template in the editor.
require 'java'
require 'yaml'

def handle_message!(message)
  puts "message received in ruby"
  MessageHandler.handle_message(message)
  # this return nil is important since the java invoke method expects
  # nothing to be returned to it.
  return nil
end

class MessageHandler

  def self.handle_message(msg)
    txt = msg.getText()
    obj = YAML.load(txt)
    UrlRequester.request(obj[:method], obj[:url], obj[:post_body])
  end

end