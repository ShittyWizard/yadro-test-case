# Test case for YADRO

<h2> Description </h2>
RESTful service for getting information about network interfaces + CLI client for it.

Include Unit-tests( **JUnit**) connected to **Travis-CI** and **DockerHub**.

<h2> Getting started with cli_net </h2>
First of all you should install Docker - https://docs.docker.com/install/

After that, you need to install **Java 8** - [OpenJDK](https://openjdk.java.net/install/) or OracleJDK, 
because I could not dockerize client application properly (docker client container can't connect to server-container). 
I try to *--link* server to client app, but it does not work. Then, I decided to make JAR-file for client (it works properly with docker server container).


After installing Docker, you can pull server's docker-image:
    
    docker pull omegacat/test-case-server:latest_version
    
Run this image (-p 8080:8080 for getting 8080 port outside the container):

    docker run -d -p 8080:8080 --name clinet_server test-case-server
    
Check that docker container is working:

    docker ps

You will get something like that:

    CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
    fde0f4f389b5        test-case-server    "/bin/sh -c 'java -j…"   2 minutes ago       Up 2 minutes        0.0.0.0:8080->8080/tcp   clinet_server

Okay, then we should run client application.

Clone this repository. Client's JAR-file located in *client/target/client-1.0-SNAPSHOT.jar*.

For running it go to *yadro_test_case/client/target* and run command:

    java -jar client-1.0-SNAPSHOT.jar

You will get "welcome message" and tips for usage.

<h2> Examples of commands </h2>

Getting API version of service:

    cli_net version -s localhost -p 8080

Getting list of network interfaces:

    cli_net list -s localhost -p 8080
    
Getting more info about network interface (for example - "lo"):

    cli_net show lo -s localhost -p 8080

Argument of "-v" (API version of service) is not required (default value = "v1"). 

Example for getting info about network interface with target API version(v1):

    cli_net show -v v1 lo -s localhost -p 8080