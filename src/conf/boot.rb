dir_name = File.dirname(__FILE__)
ruby_matcher = File.join(dir_name, "ruby", "**", "*.rb")
Dir.glob(ruby_matcher) do |f|
  require f
end