#Imports Modules
import socket
import time

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

message = "afs"
new1 = ""
#Main
while running:
    message = clientsocket.recv(1024).decode() #Receives Message
    if not message == "":
        print(message)  # Prints Message
        new1 = message
        time.sleep(0.5)
    #Closes Server If Message Is Nothing (Client Terminated)
    else:
        clientsocket.close()
        running = False



print("We are out")
print(new1)