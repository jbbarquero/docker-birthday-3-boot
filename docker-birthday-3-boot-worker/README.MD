$ mvn clean verify
$ sudo docker build -t jbbarquero/worker .
$ sudo docker run -p 8081:8080 --name worker jbbarquero/worker
$ sudo docker start -i jbbarquero/worker

NOTA:
$ sudo docker exec -it $(sudo docker ps -ql) bash