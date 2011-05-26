# vim: ts=2 sw=2 expandtab number
require 'fileutils'

PLAY = `which play`.chomp 

abort "Could not find play on PATH" if PLAY.empty?

task :dependencies do
  out = `#{PLAY} dependencies --sync --verbose`
  puts out
  fail "Problems solving dependencies" if out.include? "UNRESOLVED DEPENDENCIES"
end

task :tests do
  out = `#{PLAY} auto-test 2>&1 `
  puts out
  fail "Tests failed" if File.exist? "test-result/result.failed"
end
