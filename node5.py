import socket


def node4_program():
    port = 9005
    process_ID = 105
    state = 2
    timestamp = 10
    cs = "add()"
    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    serversocket.bind(("localhost", port))
    serversocket.listen(5)

    print("connection successful")

    conn, address = serversocket.accept()

    while True:
            received = conn.recv(1024)
            msg = received.decode('utf-8')
            print("Process 1: " + msg)

            # received1 = conn.recv(1024)
            # msg2 = received1.decode('utf-8')
            # print("Process 1: " + msg2)
            #
            # received2 = conn.recv(1024)
            # msg3 = received2.decode('utf-8')
            # print("Process 1: " + msg3)
            if state == 0:
                conn.send("ok")
                # serversocket.send("Ok".encode('utf-8'))

            elif state == 1:
                conn.send("Also interested,comparing timestamps...".encode('utf-8'))
                time = 8

                if time < timestamp:
                    conn.send("You have a greater timestamp,therefore I have entered the CS,you have been queued".encode('utf-8'))
                else:
                    conn.send("I have a lower timestamp,enter Critical Section".encode('utf-8'))

            elif state == 2:
                conn.send("In critical state...".encode('utf-8'))
    conn.close()

node4_program()
