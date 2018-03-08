Lagom with Akka Clustering running with Docker-Compose
------------------------------------------------------

`sbt abc-impl/docker:publishLocal`

Run services:
`cd poc-docker`
`docker-compose up`

Send request abc service:
```
curl -i http://localhost:12038/api/hello/John
```

Send request xyz service:
```
curl -i http://localhost:12143/api/hello/John
```
