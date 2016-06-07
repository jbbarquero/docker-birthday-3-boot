$ mvn clean verify
$ sudo docker build -t jbbarquero/voting .
$ sudo docker run -p 8080:8080 --name voting jbbarquero/voting
$ sudo docker start -i jbbarquero/voting

NOTA:
$ sudo docker exec -it $(sudo docker ps -ql) bash