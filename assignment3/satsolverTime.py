import subprocess
import numpy as np # type: ignore
import csv
import time
## SOLVING CNF SAT USING KISSAT AND SAVING RESULT IN CNF FILE
TIMEOUT = 30

def solve():
        
    # declare .cnf file
    filename = f'cnf/UniqueSolution/n5dimacs.cnf'
    
    # invoke kissat
    cmd = ["kissat/build/kissat", "-q", filename]
    cmd = subprocess.Popen(cmd, stdout=subprocess.PIPE)
    solution = cmd.communicate()[0]
    solution = solution.decode('utf8')
    
    if solution == '':
        print("timeout")
        return None
        
    print("solved")

    return solution

def runjava (jar: str) -> str:
    p = subprocess.Popen (['java','-jar', jar],
    stdin = subprocess.PIPE,
    stdout = subprocess.PIPE)
    ( output ,_) = p.communicate(timeout = TIMEOUT)
    return output.decode ('utf-8')

def measure(jar: str) -> float:
    start: float = time.time()
    runjava(jar)
    solve()
    end: float = time.time()
    return end - start

#if __name__ == '__main__':
#    f = open("cnf/results.cnf", "a")
#    f.write(solve())
if __name__ == '__main__':
    with open('resultsOneSolution/n5SAT.csv', 'w') as f:
        writer = csv.DictWriter(f,
        fieldnames=['running time'])
        writer.writeheader()
        input: float = measure('app/build/libs/app.jar')
        writer.writerow({
            'running time': input
        })  