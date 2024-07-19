# Getting Started

## Reference Documentation

For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.2/maven-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.2/maven-plugin/reference/html/#build-image)

## Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Starting up the project

- Ensure you have `maven` installed and added to your `PATH` variable. If you don't have it you install it via homebrew.

```bash
brew install maven
```

- Run the following command spin up the server.

```bash
mvn spring-boot:run
```

With this command dev tools is activated by default and you can take advantage of hot reloading.

- If you want to disable it run the application by first setting an env variable `DISABLE_DEVTOOLS`.

```bash
DISABLE_DEVTOOLS=true mvn spring-boot:run
```
