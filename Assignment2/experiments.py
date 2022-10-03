#!/ usr/ bin / env python3
import csv
import subprocess
from typing import List, Tuple
import matplotlib.pyplot as plt # type: ignore
import numpy as np # type: ignore

TIMEOUT = 30

# run the given jar package ,
# provide the given arg as the command - line
# argument,
# feed the given input string to the stdin of the
# process,
# and return the stdout from the process as string
def run_java (jar: str, arg : str, input : str ) -> str:
    p = subprocess.Popen (['java','-jar', jar ,arg],
    stdin = subprocess.PIPE,
    stdout = subprocess.PIPE)
    ( output ,_) = p.communicate(input.encode('utf-8'),
    timeout = TIMEOUT)
    return output.decode ('utf-8')


""" if __name__ == '__main__':
    print (run_java ('app/build/libs/app.jar',
    'hash ','1000000')) """


if __name__ == '__main__':
    with open('results.csv', 'w') as f:
        writer = csv.DictWriter(f,
            fieldnames=['n'])
        writer.writeheader()
        input: str = run_java('app/build/libs/app.jar','hash','1000000')
        results = input.splitlines()

        resultsInt = []
        for (n) in results:
            resultsInt.append(int(n))
            writer.writerow({
                'n': n
        }) 

        ## GENERATE PLOT ## 
    
        plt.hist(resultsInt, density=False, bins=24)  # density=False makes count
        
        plt.ylabel('Count')
        plt.xlabel('Numbers')

        plt.show()