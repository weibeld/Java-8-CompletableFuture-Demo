# Best-Price Finder

Toy example application demonstrating `CompletableFuture` of Java 8.

Adapted from the best-price finder application in Chapter 11 of [Java 8 in Action](https://www.manning.com/books/java-8-in-action).

## Description

The application emulates a client-server application where the client makes calls with a non-neglectable latency to various servers. These calls could for example be HTTP requests.

The client makes use of the new `CompletableFuture` class of Java 8 to avoid blocking on these calls, and to string together multiple dependent and independent calls (i.e. the result of one call is needed as the input to another call).

## Usage

~~~bash
gradle test
~~~

If you don't have Gradle installed, or only some incompatible version, use:

~~~bash
./gradlew test
~~~

## Classes

**Client:**

- `BestPriceFinder`

**Servers:**

- `Shop`
- `DiscountService`
- `ExchangeService`

**Tests:**

- `BestPriceFinderTestSingleShop`
- `BestPriceFinderTestAllShops`

## Notes

Java 8 `CompletableFuture` has in some way similar goals as [RxJava](https://github.com/ReactiveX/RxJava), namely the composition of *asynchronous* programs (an issue of concurrency) in a declarative way. However, RxJava is focused on reacting to *streams* of data, whereas `CompletableFuture` is focused on reacting to *single* tems of data. The *reactive stream* nature of RxJava can however be imitated in Java 8 by combining the Streams API with `CompletableFuture`, that is, by working with `Stream`s of `CompletableFuture`s, as we do in our example application.

At the moment, the reactive programming capabilities of RxJava are much more comprehensive than the ones of `CompletableFuture`, especially due to the high number of stream operators that RxJava provides. However, `CompletableFuture` may still have its usage justifications, especially if the required asynchronous operations are simple, like single network requests that return a single response. 

In Java 9, the Java language takes another step in the direction of the *reactive programming* paradigm by the introduction of the [Flow API](http://gee.cs.oswego.edu/dl/jsr166/dist/docs/java/util/concurrent/Flow.html). This API implements the [Reactive Streams](http://www.reactive-streams.org/) specification and its goal is to improve the support for reactive programming in the core language. The Flow API is, however, not a replacement for RxJava, as it defines mostly interfaces, but no implementations of stream operators. These operators either need to be implemented by the users of the API, or a third-party library like RxJava can be used. Actually, it is planned that the Flow API and RxJava will live in some sort of [symbiosis](https://github.com/ReactiveX/RxJava/wiki/Reactive-Streams) in Java 9.

In summary, thus, it is definitely worth to learn `CompletableFuture` of Java 8 for simple asynchronous programs. However, it is also worth to learn RxJava for more complex asynchronous programs, and RxJava most likely *will* still be relevant in future Java versions.
