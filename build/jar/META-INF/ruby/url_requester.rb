# To change this template, choose Tools | Templates
# and open the template in the editor.

require 'net/http'

class UrlRequester

  def self.request(method, url, post_body = {})
    puts "request: #{url}"
    case method.upcase
    when "GET"
      do_get(url)
    when "POST"
      do_post(url, post_body)
    when "PUT"
      do_put(url, post_body)
    else
      puts "Severe: unrecognized method"
      raise "Could not connect to #{url}"
    end

  end

  def self.do_get(url)
    puts "GETTING: #{url}"
    uri = parse_url_and_normalize_path(url)
    req = Net::HTTP::Get.new(path_from_uri(uri))
    puts 'request set'
    res = Net::HTTP.start(uri.host, uri.port) do |http|
      http.request(req)
    end
    puts "response was #{res}"
    raise_or_return(res)
  end

  def self.do_post(url, post_body = {})
    puts "POSTING: #{url} with: #{post_body.inspect}"
    uri = parse_url_and_normalize_path(url)
    

    req = Net::HTTP::Post.new(path_from_uri(uri))
    req.set_form_data(post_body)
    res = Net::HTTP.new(uri.host, uri.port).start do |http|
      http.request(req)
    end
    raise_or_return(res)
  end

  def self.do_put(url, post_body = {})
    post_body.merge!(:_method => "put")
    req = Net::HTTP::Post.new(path_from_uri(uri))
    req.set_form_data(post_body)
    res = Net::HTTP.new(uri.host, uri.port).start do |http|
      http.request(req)
    end
    raise_or_return(res)
  end

private
  def self.parse_url_and_normalize_path(url)
    uri = URI.parse(url)
    uri.path = "/" if uri.path.empty?
    return uri
  end

  def self.path_from_uri(uri)
    path = uri.path
    path += "?#{uri.query}" if uri.query and !uri.query.empty?
    path
  end

  def self.raise_or_return(res)
    case res
    when Net::HTTPSuccess, Net::HTTPRedirection
      puts "Sucessfully connected"
      return true
    else
      puts "Severe: response was a #{res}"
      raise "Could not connect to"
    end
  end

end
