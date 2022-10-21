#!/usr/bin/env python3

n = int(input())

l = 1
V = dict()
for i in range(n**2):
    for j in range(n**2):
        for k in range(n**2):
            V[(i,j,k)] = l
            l += 1

clauses = list()

for i in range(n**2):
    for k in range(n**2):
        clause = [V[(i,j,k)] for j in range(n**2)]
        clauses.append(clause)

for j in range(n**2):
    for k in range(n**2):
        clause = [V[(i,j,k)] for i in range(n**2)]
        clauses.append(clause)

for i0 in range(n):
    for j0 in range(n):
        for k in range(n**2):
            clause = list()
            for i in range(i0*n,(i0+1)*n):
                for j in range(j0*n,(j0+1)*n):
                    clause.append(V[(i,j,k)])
            clauses.append(clause)

for i in range(n**2):
    for j in range(n**2):
        clause = [V[(i,j,k)] for k in range(n**2)]
        clauses.append(clause)

for i in range(n**2):
    for j in range(n**2):
        for k1 in range(n**2):
            for k2 in range(k1+1,n**2):
                clause = [-V[(i,j,k1)],-V[(i,j,k2)]]
                clauses.append(clause)

print(f'p cnf {len(V)} {len(clauses)}')
for clause in clauses:
    print(' '.join(map(str,clause + [0])))
