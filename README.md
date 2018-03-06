Lagom with Akka Clustering running with Docker-Compose
------------------------------------------------------

`sbt abc-impl/docker:publishLocal`

Run services:
`cd poc-docker`
`docker-compose up`

Send request:
```
curl -i http://localhost:12038/api/hello/John
```

