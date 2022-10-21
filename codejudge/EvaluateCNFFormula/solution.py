#!/usr/bin/env python3

import re
import sys

line = sys.stdin.readline()
m = re.match(r'p cnf ([0-9]+) ([0-9])', line)
nv = int(m.group(1))
nc = int(m.group(2))

clauses = list()
clause = list()
line = sys.stdin.readline()
while not line.startswith('v'):
    for x in map(int,line.split()):
        if x == 0:
            clauses.append(clause)
            clause = list()
        else:
            clause.append(x)
    line = sys.stdin.readline()

ta = dict()
while line.startswith('v'):
    done = False
    for x in line.split():
        if x == 'v':
            continue
        x = int(x)
        if x > 0:
            ta[x] = True
        elif x < 0:
            ta[-x] = False
        else:
            done = True
            break
    if done:
        break
    line = sys.stdin.readline()

for clause in clauses:
    ok = False
    for x in clause:
        if x > 0 and ta[x]:
            ok = True
            break
        elif x < 0 and not ta[-x]:
            ok = True
            break
    if not ok:
        print('FALSE')
        quit()
print('TRUE')