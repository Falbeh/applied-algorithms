#!/ usr/ bin / env python3
# To run: python3 experiments.py (from terminal)

import subprocess
from typing import List, Dict, Tuple
import numpy as np  # type: ignore
import time
import csv

TIMEOUT = 1200

# run the given jar package ,
# provide the given arg as the command - line
# argument ,
# feed the given input string to the stdin of the
# process ,
# and return the stdout from the process as string


##########################
## RUN JAVA FROM PYTHON ##
##########################

def runjava (jar: str) -> str:
    p = subprocess.Popen (['java','-jar', jar],
    stdin = subprocess.PIPE,
    stdout = subprocess.PIPE)
    ( output ,_) = p.communicate(timeout = TIMEOUT)
    return output.decode ('utf-8')
    
######################
## MEASURE RUNTIMES ##
######################

def measure(jar: str) -> float:
    start: float = time.time()
    runjava(jar)
    end: float = time.time()
    return end - start

if __name__ == '__main__':
   # with open('results/n4Backtracking.csv', 'w') as f:
   #     writer = csv.DictWriter(f,
   #     fieldnames=['running time'])
   #     writer.writeheader()
   #     input: float = measure('app/build/libs/app.jar')
   #     writer.writerow({
   #         'running time': input
   #     }) 
   # with open('results/n3Backtracking.csv', 'w') as f:
   #     writer = csv.DictWriter(f,
   #     fieldnames=['running time'])
   #     writer.writeheader()
   #     input: float = measure('app/build/libs/app.jar')
   #     writer.writerow({
   #         'running time': input
   #     }) 
    with open('resultsManySolutions/n2DancingLinks.csv', 'w') as f:
       writer = csv.DictWriter(f,
       fieldnames=['running time'])
       writer.writeheader()
       input: float = measure('app/build/libs/app.jar')
       writer.writerow({
           'running time': input
       })  