#Imports Modules
import socket
import matplotlib.pyplot as plt
from matplotlib import animation
import time

# Lists for Sensors Data
AccerometerData = []
GyroscopeData = []
MagnetometerData = []
LinearaccData = []
GravityData = []




#Defines Server Values
listensocket = socket.socket()
Port = 8000
maxConnections = 999
IP = socket.gethostname() #Gets Hostname Of Current Macheine

listensocket.bind(('',Port))

#Opens Server
listensocket.listen(maxConnections)
print("Server started at " + IP + " on port " + str(Port))

#Accepts Incomming Connection
(clientsocket, address) = listensocket.accept()
print("New connection made!")

running = True



#Main
while running:
    #print("\n")
    message = ""
    message = clientsocket.recv(2000).decode() #Receives Message
    if not message == "":
        cords = message.split(" ")
        #print(len(cords))
        if(len(cords) == 16):
            #accer
            tempList = [float(cords[0]), float(cords[1]), float(cords[2])]
            AccerometerData.append(tempList)

            #GyroScop
            tempList = [float(cords[3]), float(cords[4]), float(cords[5])]
            GyroscopeData.append(tempList)

            #Magnetometer
            tempList = [float(cords[6]), float(cords[7]), float(cords[8])]
            MagnetometerData.append(tempList)

            #LinearAcc
            tempList = [float(cords[9]), float(cords[10]), float(cords[11])]
            LinearaccData.append(tempList)

            #Gravity
            tempList = [float(cords[12]), float(cords[13]), float(cords[14])]
            GravityData.append(tempList)


            print(message)  # Prints Message

    #Closes Server If Message Is Nothing (Client Terminated)
    else:
        clientsocket.close()
        running = False


#plt.show()
print("We are out")
print(AccerometerData)

print("________________________________________________________________")
print(GyroscopeData)
print("________________________________________________________________")
print(MagnetometerData)
print("________________________________________________________________")
print(LinearaccData)
print("________________________________________________________________")
print(GravityData)
print("________________________________________________________________")
