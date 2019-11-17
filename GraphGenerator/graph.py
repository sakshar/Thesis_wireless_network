from numpy import *
import matplotlib.pyplot as plt

# evenly sampled time at 200ms intervals
sensor = []
sensor2= []
for i in range(0,16):
    sensor.append((i+1)*25)
    sensor2.append((i+1)*50)
greedy = open("greedyCoverage_225.txt","r")
greedyCover = []
for line in greedy:
    greedyCover.append(line)
conflict = open("conflictCoverage_225.txt","r")
conflictCover = []
for line in conflict:
    conflictCover.append(line)

# red dashes, blue squares and green triangles
#plt.figure(2)
#plt.plot(sensor, sensor, 'bs', sensor, sensor2, 'g^')
plt.figure(1)
plt.plot(sensor, greedyCover,'gs')
#plt.figure(2)
plt.plot(sensor, conflictCover, 'b^')
plt.show()