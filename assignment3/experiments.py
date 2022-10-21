#!/ usr/ bin / env python3
# To run: python3 experiments.py (from terminal)

import subprocess
from typing import List, Dict, Tuple
import numpy as np  # type: ignore
import time
import csv
import satsolver

TIMEOUT = 20

##########################
## RUN JAVA FROM PYTHON ##
##########################

def run_java(jar: str, input: str) ->str:
    p = subprocess.Popen(['java', '-jar', jar],
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE)
    (output, _) = p.communicate(input.encode('utf-8'),
        timeout=TIMEOUT)
    return output.decode('utf-8')



if __name__ == '__main__':
    ## GENERATE CNF FILE FROM JAVA
    print(run_java('evaluateCNF/app/build/libs/app.jar', 'p cnf 4 3\n 1 -2 3 0\n 2 -3 4 0\n -1 3 -4 0'))

    ## Solving SAT
 
