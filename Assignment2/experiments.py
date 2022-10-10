#!/ usr/ bin / env python3
import csv
import subprocess
from typing import List, Tuple
import matplotlib.pyplot as plt # type: ignore
import numpy as np # type: ignore

TIMEOUT = 30

# run the given jar package

def run_java (jar: str) -> str:
    p = subprocess.Popen (['java','-jar', jar],
    stdin = subprocess.PIPE,
    stdout = subprocess.PIPE)
    ( output ,_) = p.communicate(timeout = TIMEOUT)
    return output.decode ('utf-8')

if __name__ == '__main__':
    with open('results.csv', 'w') as f:
        """ writer = csv.DictWriter(f,
            fieldnames=['n'])
        writer.writeheader() """
        input: str = run_java('app/build/libs/app.jar')
        results = input.splitlines()

        resultsInt = []
        for (n) in results:
            resultsInt.append(int(n))
            """ writer.writerow({
                'n': n
            })  """

        
        ## GENERATE HISTOGRAM PLOT WITH RESULTS ## 
        plt.hist(resultsInt, density=False, bins=24)  # density=False makes count
        
        plt.ylabel('Count')
        plt.xlabel('p(h(x))')

        ## SHOW PLOT
        plt.show()