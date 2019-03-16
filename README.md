# OpenlyricsJ

This project is a Java JAXB implementation of the Openlyrics xml standard for exchange of worship songs.

## Getting Started

Simply pull down the project and run the gradle wrapper.
```
./gradlew
```

### Prerequisites

You'll need a copy of Java 8 installed on your host.

## Deployment

As this is a Spring Boot app, just assemble the jar file and run it with Java.

```
./gradlew assemble
cd build/libs
java -jar openlp-convert.jar
```

## Built With
[Gradle](https://gradle.org/) - Dependency management

## Authors

* Thomas Brian Holdren - *Initial work* - [CyanFocus](https://www.cyanfocus.com/wordpress/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Openlyric for attempting to standardize a format for exchange of songs.
