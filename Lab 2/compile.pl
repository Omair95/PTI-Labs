#!/usr/bin/perl
use warnings;

system("sudo javac -cp /usr/share/tomcat7/lib/servlet-api.jar:/var/lib/tomcat7/webapps/my_webapp/WEB-INF/lib/json-simple-1.1.1.jar /var/lib/tomcat7/webapps/my_webapp/WEB-INF/classes/mypackage/*.java");

system("sudo service tomcat7 stop");
system("sudo service tomcat7 start");
