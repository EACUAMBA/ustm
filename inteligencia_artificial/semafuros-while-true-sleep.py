import time

print('Semafuros utilizando while true e sleep da thread actual.')

def printGreen():
    print('Verde')
    time.sleep(10)

def printGreenToTurnRight():
    print('Verde para cruzar ->')
    time.sleep(5)

def printYellow():
    print("Amarelo")
    time.sleep(5)

def printRed():
    print("Vermelho")
    time.sleep(10)

while True:
    printGreenToTurnRight()
    printGreen()
    printYellow()
    printRed()
