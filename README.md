[![CircleCI](https://circleci.com/gh/Tetragramato/todo-sample/tree/master.svg?style=shield)](https://circleci.com/gh/Tetragramato/todo-sample/tree/master)
# Simple Todo Sample in Kotlin

This Todo Sample use `Spring Boot Starter Web`, and is fully tested (UT/IT).

The project run on default port : `8080`

**This is a support for other kind of POC, like front dev.**

## Build and launch

You can build the projet :

```bash
./gradlew clean build
```

You can launch the application as follow :

```bash
cd build/libs

java -jar todo-sample-0.0.1-SNAPSHOT.jar
```

Or for more convenience, just run the application in your IDE :)

## What you can do

There are 3 end points for the moment, based on a simple `Todo` Data class :

```kotlin
data class Todo(
        var id: UUID?,
        val name: String,
        val content: String
)
```

### POST

Create a Todo item :

```bash
curl -d '{"name":"toto", "content":"titi"}' -H "Content-Type: application/json" -X POST http://localhost:8080/todos
```

### GET

Get All Todos :

```bash
curl -X GET http://localhost:8080/todos
```

Get one Todo :

```bash
curl -X GET http://localhost:8080/todos/{UUID}
```