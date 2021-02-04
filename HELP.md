# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.internship-finder.demo' is invalid and this project uses 'com.internshipfinder.demo' instead.

# Getting Started

### Reference Documentation

For this project you need to have a postgres database installed with a datasource named internshipfinder.
In the DemoApplication.class you can find the main class with the spring boot configuration. Just run that class and all
database entities will be created, and the application is ready to use. 

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

# Architecture

The application is based on a simple Spring MVC architecture. That means that we have a persistence and a business layer
and on top of that we have the controller witch will expose the applications endpoints.

![image](https://user-images.githubusercontent.com/21227623/106894203-51303080-66f7-11eb-86e1-d55850dbd4f0.png)

In the image above we can see the three layers, the main class, and the config package that provides the needed beans for
the application and the web. Next we can see how the database is structured and also the relationship between the entities.

![image](https://user-images.githubusercontent.com/21227623/106895193-99038780-66f8-11eb-8186-999574a9613b.png)
![image](https://user-images.githubusercontent.com/21227623/106895393-d405bb00-66f8-11eb-8d51-66951093b8b6.png)

In the beginning the data structure was more complex, but after struggling with a lot of inefficient joins and selects,
we decided to restructure and have a simpler and more maintainable database.

![image](https://user-images.githubusercontent.com/21227623/106896650-88eca780-66fa-11eb-98b3-64234fce5879.png)

The business layer package is the most complex part, containing the logic of the application. We also decided that 
a facade pattern would suit our project. All the users must log in so for that we crated an interface and that interface
based on the user type calls the correct method. Also in this layer we can see authentication. We use a JWT that we create 
when the user logs in, and we validate that for the rest of the session. For the validation we use Springs security @PreAuthorize
where we check the role of the user. An example of this can be seen below.

```
@GetMapping
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<Set<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getCompanies());
    }