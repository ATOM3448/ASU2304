from os import name
import pandas as pd
import csv

BDMO = pd.read_csv('timetable-disciplines.csv', delimiter=',' )

BDID = BDMO["id"]
if (BDMO["id"] > 19030 == True):
    print(BDMO)

