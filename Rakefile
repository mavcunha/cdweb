# vim: ts=2 sw=2 expandtab number
require 'fileutils'
require 'rake/packagetask'

PLAY = `which play`.chomp 
DEPLOY_DIR = ENV['PLAY_DEPLOY_DIR'] || '/tmp'
PROD_FLAG  = ENV['PLAY_PROD_FLAG'] ? '--%prod' : ''

if PROD_FLAG.empty?
  SUDO_BIN = ''
else
  SUDO_BIN = `which sudo`.chomp
end

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

task :start_server do 
  puts `#{SUDO_BIN} #{PLAY} start #{PROD_FLAG}` 
  fail "Fail to start Play!" if $?.exitstatus != 0
  puts "DONE!"
end

task :stop_server do
  puts `#{SUDO_BIN} #{PLAY} stop #{PROD_FLAG}`
  fail "Fail to stop Play!" if $?.exitstatus != 0
  puts "DONE!"
end

task :restart_server => [:stop_server,:start_server]

task :clean_deploy_dir do
 FileUtils.rm_r Dir["#{DEPLOY_DIR}/cdweb/*"]
end

task :deploy => [:package,:clean_deploy_dir] do
  puts `tar zxvf pkg/cdweb.tar.gz -C #{DEPLOY_DIR}`
  fail "Deployment failure." if $?.exitstatus != 0
end

Rake::PackageTask.new('cdweb', :noversion) do |p|
  p.need_tar_gz = true
  p.package_files.include(["Rakefile","app/**/*","conf/**/*","lib/**/*","public/**/*"])
end

