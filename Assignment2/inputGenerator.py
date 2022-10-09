#!/ usr/ bin / env python3
# To run: python3 experiments.py (from terminal)

import random
import subprocess
from typing import List, Dict, Tuple
from experiments import run_java
import numpy as np  # type: ignore
import time
import csv

TIMEOUT = 30

def generateDistinctN(seed, n: int):
    seed = random.sample(range(1, 2**28), 1)
    random.seed(seed[0])
    data = random.sample(range(1, 2**28), n)
    return data

""" def run_java(jar: str, arg: str, input: str) ->str:
    p = subprocess.Popen(['java', '-jar', jar, arg],
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE)
    (output, _) = p.communicate(input.encode('utf-8'),
        timeout=TIMEOUT)
    return output.decode('utf-8') """


if __name__ == '__main__':
    with open('results.csv', 'w') as f:
        writer = csv.DictWriter(f,
            fieldnames=['n'])
        writer.writeheader()
        input: list = generateDistinctN(314158, 1000000)

        resultsInt = []
        for (n) in input:
            resultsInt.append(int(n))
            writer.writerow({
                'n': n
        }) 
    
        
    
