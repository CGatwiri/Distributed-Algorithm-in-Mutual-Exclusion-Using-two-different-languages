import socket


def node4_program():
    port = 9004
    process_ID = 104
    state = 1
    timestamp = 10
    cs = "add()"
    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    serversocket.bind(("localhost", port))
    serversocket.listen(5)

    print("connection successful")

    conn, address = serversocket.accept()

    while True:
        try:
            received = conn.recv(1024).decode()
            # msg = received.decode('utf-8')
            print("Process 1: " + received)

            conn.send("hey".encode())
        except:
            continue

            if state == 0:
                print("hey")
                conn.send("ok")
                # serversocket.send("Ok".encode('utf-8'))

            elif state == 1:
                serversocket.send("Also interested,comparing timestamps...".encode('utf-8'))
                time = 8

                if time < timestamp:
                    serversocket.send(
                        "You have a greater timestamp,therefore I have entered the CS,you have been queued".encode(
                            'utf-8'))
                else:
                    serversocket.send("I have a lower timestamp,enter Critical Section".encode('utf-8'))

            elif state == 2:
                serversocket.send("In critical state...".encode('utf-8'))
    conn.close()


node4_program()
