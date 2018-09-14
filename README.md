# chat-service
Instructions for compiling:
Install Maven 
Install MongoDB - uses MongoDB to persist the data
clone the github project chat-service
Run mvn clean install - create the jar file

Instructions for running:
Navigate to the target directory chat-service
start mongodb - MongoDB on port 27017 on local host
java -jar *.jar


Decisions:
1. chose Spring boot starts for ease of developing the micro services.
2. MongoDB for persisting the data - created two collections ActiveChats and ExpiredChats
3. Lombok annotations(No need to write getter, setter , toString or equals)


Limitations:
1. It is basic Rest Services implementations with a basic storage solution
2. 

If i had more time i would implement all the spring boot features (Spring boot cloud features), additional batch job to mark all messages from active to expired after the expiration_date is met. User Session management, authentication, user management etc, will make it deployale in a Docker container etc.

Scaling:
We can scale it horizontally using the Containers and Container management solutions with multiple VM etc. As for Storage we can uses mongo db replication and sharing of the chats on multiple VMS

