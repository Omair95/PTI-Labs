#! /usr/bin/python

# Llibreries
import cgi, os, re, sys, string, time, csv

# Programa principal
print "Content-type: text/html\n\n"
form = cgi.FieldStorage()
finalPrice = 0

def validate():
    if (form['dies_lloguer'].value == 0):
         print "<TITLE>Order</TITLE>\n"
         print "<H1>Order Sent</H1>\n"


if (os.environ['REQUEST_METHOD'] == "GET"):
    carMaker = form['maker_vehicle'].value
    carModel = form['model_vehicle'].value
    engine = form['sub_model_vehicle'].value
    NumberDays = form['dies_lloguer'].value
    NumberUnits = form['num_vehicles'].value

    if (NumberDays == '0' or NumberDays < '0'):
        print "<H1> Error: Number of days cannot be 0 or negative </H1>\n"
    elif (NumberUnits == '0' or NumberUnits < '0'):
        print "<H1> Error: Number of units cannot be 0 or negative </H1>\n"
    else:
        if carMaker == 'Seat':
            finalPrice += 50
        elif carMaker == 'Ford':
            finalPrice += 60

        if carModel == 'Economic':
            finalPrice += 10

        elif carModel == 'Semi-luxe':
            finalPrice += 20

        elif carModel == 'Luxe':
            finalPrice += 30

        elif carModel == 'Limusina':
            finalPrice += 40

        if engine == 'Diesel':
            finalPrice += 10
        elif engine == 'Gasolina':
            finalPrice += 20

        finalPrice = finalPrice * int(NumberDays) * int(NumberUnits)
        print "<H1> Order sent with this data <H1>"
        print "Price = %d" % finalPrice
        print "<p> Car maker = %s <p>" % carMaker
        print "<p> Car model = %s <p>" % carModel
        print "<p> Engine = %s <p>" % engine
        print "<p> Number of days = %s <p>" % NumberDays
        print "<p> Number of units = %s <p>" % NumberUnits
        print "<H1> Final price = %d euros<h1>" % finalPrice
        c = csv.writer(open("rentals.csv", "a"))

        c.writerow([carMaker,carModel, engine, NumberDays, NumberUnits, finalPrice])

    #validate()
    sys.exit()
