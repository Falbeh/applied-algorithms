#!/ usr/ bin / env python3
import csv
import subprocess
from typing import List, Tuple
import matplotlib.pyplot as plt # type: ignore
import numpy as np # type: ignore
from math import sqrt

## Calculate standard deviation
def standard_deviationLow(m, deviation):
    low = 200_000*(1-(deviation*(1.04/sqrt(m))))
    return low

def standard_deviationHigh(m, deviation):
    high = 200_000*(1+(deviation*(1.04/sqrt(m))))
    return high
 

## Read csv results files and calculate fraction of runs that are within different standard deviations
def read_results(m, deviation, filename: str):
    count = 0
    f = open(filename, "r")
    for row in f:
        if float(row) > standard_deviationLow(m, deviation) and float(row) < standard_deviationHigh(m, deviation): 
            count = count +1
    ## 
    return count/1000


## Generating latex table with standard deviations
def write_latex_tabular(filename: str):
    with open(filename,'w') as f:
        f.write(r'\begin{tabular}{rrr}' + '\n')
        f.write(r'$m$ & $n(1 \pm \sigma)$ & $n(1 \pm 2\sigma)$')
        f.write(r'\\\hline' + '\n')

        frac1x = read_results(256, 1, 'hyperResults/hyper256.csv')
        frac2x = read_results(256, 2, 'hyperResults/hyper256.csv')
        fields = [str(256),str(frac1x),str(frac2x)]
        f.write(' & '.join(fields) + r'\\' + '\n')
        frac1x = read_results(1024, 1, 'hyperResults/hyper1024.csv')
        frac2x = read_results(1024, 2, 'hyperResults/hyper1024.csv')
        fields = [str(1024),str(frac1x),str(frac2x)]
        f.write(' & '.join(fields) + r'\\' + '\n')
        frac1x = read_results(4096, 1, 'hyperResults/hyper4096.csv')
        frac2x = read_results(4096, 2, 'hyperResults/hyper4096.csv')
        fields = [str(4096),str(frac1x),str(frac2x)]
        f.write(' & '.join(fields) + r'\\' + '\n')
        f.write(r'\end{tabular}' + '\n')


if __name__ == '__main__':
    write_latex_tabular("hyperResults/standardDeviationTable.tex")