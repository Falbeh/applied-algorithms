## INSPIRED BY IMPLEMENTATION: https://www.kaggle.com/code/ebouteillon/perfect-solve-of-puzzles-using-a-sat-solver

import subprocess
import numpy as np # type: ignore

def solve():
        
    # declare .cnf file
    filename = f'cnf/cnfgen.cnf'
    
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

if __name__ == '__main__':
    print(solve())