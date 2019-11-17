import matplotlib.pyplot as plt

greedy = open("Final Data\\Varying_Sensor_Target_225\\CGA\\targetCoverageRatio.txt", "r")
max_conflict = open("Final Data\\Varying_Sensor_Target_225\\TMxCH\\targetCoverageRatio.txt", "r")
min_conflict = open("Final Data\\Varying_Sensor_Target_225\\TMnCH\\targetCoverageRatio.txt", "r")
max_targetoriented = open("Final Data\\Varying_Sensor_Target_225\\TMxCHSE\\targetCoverageRatio.txt", "r")

max_conflict_225 = [None]*16
min_conflict_225 = [None]*16
max_targetoriented_225 = [None]*16
greedy_225 = [None]*16
i = 0
for line in greedy:
    greedy_225[i] = float(line)
    i += 1
i = 0
for line in max_conflict:
    max_conflict_225[i] = float(line)
    i += 1
i = 0
for line in min_conflict:
    min_conflict_225[i] = float(line)
    i += 1
i = 0
for line in max_targetoriented:
    max_targetoriented_225[i] = float(line)
    i += 1
x = [25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400]

plt.plot(x, max_targetoriented_225, '-y', label='max_targetoriented')
plt.plot(x, min_conflict_225, '-b', label='min_conflict')
plt.plot(x, max_conflict_225, '-g', label='max_conflict')
plt.plot(x, greedy_225, '-r', label='greedy')
plt.legend(loc='lower right')
plt.savefig('Graphs\\CR_sensor.png')
plt.close(1)

greedy = open("Final Data\\Varying_Target_Sensor_300\\CGA\\targetCoverageRatio.txt", "r")
max_conflict = open("Final Data\\Varying_Target_Sensor_300\\TMxCH\\targetCoverageRatio.txt", "r")
min_conflict = open("Final Data\\Varying_Target_Sensor_300\\TMnCH\\targetCoverageRatio.txt", "r")
max_targetoriented = open("Final Data\\Varying_Target_Sensor_300\\TMxCHSE\\targetCoverageRatio.txt", "r")

max_conflict_300 = [None]*9
min_conflict_300 = [None]*9
max_targetoriented_300 = [None]*9
greedy_300 = [None]*9
i = 0
for line in greedy:
    greedy_300[i] = float(line)
    i += 1
i = 0
for line in max_conflict:
    max_conflict_300[i] = float(line)
    i += 1
i = 0
for line in min_conflict:
    min_conflict_300[i] = float(line)
    i += 1
i = 0
for line in max_targetoriented:
    max_targetoriented_300[i] = float(line)
    i += 1
x = [25, 50, 75, 100, 125, 150, 175, 200, 225]

plt.plot(x, max_targetoriented_300, '-y', label='max_targetoriented')
plt.plot(x, min_conflict_300, '-b', label='min_conflict')
plt.plot(x, max_conflict_300, '-g', label='max_conflict')
plt.plot(x, greedy_300, '-r', label='greedy')
plt.legend(loc='lower right')
plt.savefig('Graphs\\CR_target.png')
plt.close(1)