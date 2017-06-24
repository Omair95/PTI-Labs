
#!/usr/bin/python

import csv

cr = csv.reader(open("MYFILE.csv", "rb"))
for row in cr:
        print row[0], row[1]
