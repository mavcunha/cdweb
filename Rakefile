# vim: ts=2 sw=2 expandtab number
require 'fileutils'
require 'rake/packagetask'

PLAY = `which play`.chomp 
DEPLOY_DIR = ENV['PLAY_DEPLOY_DIR'] || '/tmp'
PROD_FLAG  = ENV['PLAY_PROD_FLAG'] ? '--%prod' : ''
SUDO_BIN = PROD_FLAG.empty? ? '' : `which sudo`.chomp

abort "Could not find play on PATH" if PLAY.empty?

task :dependencies do
  out = run_this "#{PLAY} dependencies --sync --verbose"
  fail "Problems solving dependencies" if out.include? "UNRESOLVED DEPENDENCIES"
end

task :deps_after_deploy do
  Dir.chdir(File.join(DEPLOY_DIR,'cdweb')) do
    out = run_this "#{PLAY} dependencies --sync --verbose"
    fail "Problems solving dependencies" if out.include? "UNRESOLVED DEPENDENCIES"
  end
end

task :tests do
  run_this "#{PLAY} auto-test 2>&1 "
  fail "Tests failed" if File.exist? "test-result/result.failed"
end

task :start_server do
  Dir.chdir(File.join(DEPLOY_DIR,'cdweb')) do
    run_this "#{SUDO_BIN} #{PLAY} start #{PROD_FLAG}"
  end
end

task :stop_server do
  Dir.chdir(File.join(DEPLOY_DIR,'cdweb')) do
    run_this "#{SUDO_BIN} #{PLAY} stop #{PROD_FLAG}"
  end
end

task :clean_deploy_dir do
 Dir.chdir(DEPLOY_DIR) do
   run_this "#{SUDO_BIN} rm -Rf cdweb/*"
 end
end

task :deploy => [:package,:clean_deploy_dir] do
  run_this "tar zxvf pkg/cdweb.tar.gz -C #{DEPLOY_DIR}"
  fail "Deployment failure." if $?.exitstatus != 0
end

Rake::PackageTask.new('cdweb', :noversion) do |p|
  p.need_tar_gz = true
  p.package_files.include(["Rakefile","app/**/*","conf/**/*","lib/**/*","public/**/*"])
end

private 
 def run_this(command)
  puts "running: #{command} (pwd:#{Dir.pwd})"
  puts out = `#{command}`
  fail "Fail to start/stop Play!" if $?.exitstatus != 0
  out
 end

