# ABOUT

This is a simple Spring Boot application built with Java 8.

The application is hosted on defualt 8080 port.

This endpoint is to upload the sample file

```http://localhost:8080/upload```

The following endpoint is to get the last n days of data.

```http://localhost:8080/last-n-days/60```

This is for getting average values per 7 days.

```http://localhost:8080/average/49```

This is built-in spring data rest endpoint to navigate all records.

```http://localhost:8080/health-metrics?page=1&size=20```



- The input file is not validated.
- The app need a running mongo instance in the backend. A container can be hosted locally on default port 27017


# On Kubernetes

- Package the application
- Run and check that docker image locally (optional)
- Push that packerized image to docker image registry server (like docker.io, Google Cloud Container Registry)
- Expose application to internet
- Scale up or out by using CPU/Memory usage
- Check health of the application