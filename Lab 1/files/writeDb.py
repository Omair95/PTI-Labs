#!/usr/bin/python

import csv

c = csv.writer(open("rentals.csv", "a"))
c.writerow(["1","2"])
