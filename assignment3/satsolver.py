import subprocess
import numpy as np # type: ignore

## SOLVING CNF SAT USING KISSAT AND SAVING RESULT IN CNF FILE

def solve():
        
    # declare .cnf file
    filename = f'cnf/ManySolution/n4dimacs.cnf'
    
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
    f = open("cnf/results.cnf", "a")
    f.write(solve())