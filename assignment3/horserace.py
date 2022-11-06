#!/ usr/ bin / env python3
# To run: python3 experiments.py (from terminal)

import subprocess
from typing import List, Dict, Tuple
import numpy as np  # type: ignore
import time
import csv


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
    
######################
## MEASURE RUNTIMES ##
######################

def measure(algorithm: str, jar: str) -> float:
    
    start: float = time.time()
    result_string: str = runjava(jar, algorithm)
    end: float = time.time()
    assert result_string.strip() == 'null'
    return end - start

if __name__ == '__main__':
    
    #print(run_java('app/build/libs/app.jar',
    #              'backtracking','3\n1 2 3'))
   # print(run_java('threesum/app/build/libs/app.jar',
   #                'cubic','3\n1 2 -3'))
   print(runjava('app/build/libs/app.jar'))
   # print(benchmark('cubic',
   #            'threesum/app/build/libs/app.jar'))

# if __name__ == '__main__':
#     with open('results.csv', 'w') as f:
#         writer = csv.DictWriter(f,
#             fieldnames=['algorithm', 'n', 'time'])
#         writer.writeheader()
#         for algorithm, jar in INSTANCES:
#             results: List[Tuple[int, float]] = \
#                 benchmark(algorithm, jar)
#             for (n, t) in results:
#                 writer.writerow({
#                     'algorithm': algorithm,
#                     'n': n,
#                     'time': t
#                 })