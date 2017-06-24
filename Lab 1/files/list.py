#! /usr/bin/python

# Llibreries
import cgi, os, re, sys, string, time, csv
PASSWORD = "HTTPdRocKs"
ADMIN = "admin"
print "Content-type: text/html\n\n"
form = cgi.FieldStorage()

if (os.environ['REQUEST_METHOD'] == "POST"):
    userId = form['userid'].value
    password = form['password'].value
    if (password == PASSWORD and userId == ADMIN):
        print "<TITLE>List of rentals</TITLE>\n"
        print "<H1> List of rentals </H1>\n"
        cr = csv.reader(open("rentals.csv", "rb"))
        print "<pre>" + "Car maker" + "      " + "Car model" + "          " + "Engine" + "     " + "Number of days" + "       " + "Number of units" + "        " + "Final price"
        print "<br />"
        for row in cr:
            if row[0] == 'Seat':
                if row[1] == 'Economic':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                            print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                elif row[1] == 'Semi-Luxe':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "          " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                        print "<pre>" + row[0] + "           " + row[1] + "          " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                elif row[1] == 'Luxe':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "               " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                        print "<pre>" + row[0] + "           " + row[1] + "               " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                elif row[1] == 'Limusina':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                        print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
            elif row[0] == 'Ford':
                if row[1] == 'Economic':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                            print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                elif row[1] == 'Semi-Luxe':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "          " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                        print "<pre>" + row[0] + "           " + row[1] + "          " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                elif row[1] == 'Luxe':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "               " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                        print "<pre>" + row[0] + "           " + row[1] + "               " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                elif row[1] == 'Limusina':
                    if row[2] == 'Diesel':
                        print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "          " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"
                    elif row[2] == 'Gasolina':
                        print "<pre>" + row[0] + "           " + row[1] + "           " + row[2] + "        " + row[3] + "                    " + row[4] + "                     " + row[5] + "<br />"

    sys.exit()
