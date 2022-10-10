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
    random.seed(seed)
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
    initialSeed = 314158
    random.seed(initialSeed)
    seeds = [random.randint(0,1000000) for x in range(1000)]
    for i in range(0,len(seeds)):
        seed = seeds[i]
        with open('data/results' + str(i) + '.csv', 'w') as f:
            writer = csv.DictWriter(f,
                fieldnames=['n'])
            input: list = generateDistinctN(seed, 200_000)

            resultsInt = []
            for (n) in input:
                resultsInt.append(int(n))
                writer.writerow({
                    'n': n
            }) 
    
        ## m = 1024 , n = 10^6 (990000)
        ## m = 2048 , n = 10^6 (995000)
     
