# petstore-demo

This is a demo project that uses Spring Integration. It listens to messages from a PubSub and processes them.

## Technologies
- Java
- [Gradle](https://docs.gradle.org)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Integration](https://spring.io/projects/spring-integration)
- [Spring Cloud GCP](https://cloud.google.com/java/docs/spring)

## Getting Started

### Using PubSub Emulator

This application uses a GCP PubSub. If you don't have a real GCP account, you can use a PubSub Emulator. 
We use [thekevjames/gcloud-pubsub-emulator](https://github.com/TheKevJames/tools/tree/master/docker-gcloud-pubsub-emulator), 
which can automatically create the necessary project, topic and subscription by passing in an environment variable.

To start the emulator, you can run the following:
```
docker run --rm -it -p 8681:8681 \
    -e PUBSUB_EMULATOR_WAIT_TIMEOUT=60 \
    -e PUBSUB_PROJECT1=petstore-project,pet-intake-topic:pet-intake-subscription \
   quay.io/thekevjames/gcloud-pubsub-emulator:latest
```

**NOTE**:
There is some race condition where sometimes the project/topic/subscription creation didn't happen in timely manner.
When this happens, you will see this message:
```
Operation timed out
```
There is an [PR](https://github.com/TheKevJames/tools/pull/1081) to fix this but the maintainer has not reviewed or
approved it. You may want to check out the branch that has the fix, and build the docker image yourself.

### Publishing messages to Emulator
To publish messages to the PubSub Emulator above, you can run the PubSub Emulator UI docker:
```
docker run -p 4200:80 ghcr.io/neoscript/pubsub-emulator-ui:latest
```
The first time you run the UI, you'll have to click the **Attach new project** and enter the name `petstore-project`.
It should then show the subscription that's automatically created by the emulator.
Click the `petstore-intake-topic` and a large input box should show up where you can paste a json message. Sample json messages can be found in `src/main/resources/pets` directory

### Running the App

1. Run the app using Spring profile `local` from an IDE of your choice OR run
   ```
   ./gradlew bootRun --args="--spring.profiles.active=local"
   ```
