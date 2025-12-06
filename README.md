# 🚀 JSONPlaceholder API Tests

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![RestAssured](https://img.shields.io/badge/Rest_Assured-5.4.0-green?style=for-the-badge)
![Allure](https://img.shields.io/badge/Allure_Report-2.25.0-orange?style=for-the-badge)
![Lombok](https://img.shields.io/badge/Lombok-1.18.30-red?style=for-the-badge)

A comprehensive API test automation project for [JSONPlaceholder](https://jsonplaceholder.typicode.com/), demonstrating modern Java testing practices using **RestAssured**, **JUnit 5**, **Allure Report**, and **Lombok**.

---

## 🛠 Tech Stack

- **Language**: Java 21
- **Testing Framework**: JUnit 5
- **HTTP Client**: RestAssured
- **Reporting**: Allure Report
- **Boilerplate Reduction**: Lombok
- **Build Tool**: Maven

## 📂 Project Structure


```
src/test/java
├── BaseTest.java               # Base congig for RestAssured and Allure
├── JsonPlaceholderApiTest.java # Main api test class
└── models
    └── Post.java               # POJO model with Lombok
```


## 🚀 Getting Started

### Prerequisites

- Java JDK 21
- Maven 3.6+

### Running Tests

Execute tests using Maven:


```bash
mvn clean test
```


## 📊 Viewing Reports

This project uses **Allure Report** to generate beautiful and detailed test reports.

### Instant Preview
To generate and open the report directly in your browser:


```bash
mvn allure:serve
```


### Generate Static Report
To build report files into `target/site/allure-maven-plugin`:


```bash
mvn allure:report
```


---

## 📝 Test Scenarios

| Method | Endpoint | Description | Status |
| :--- | :--- | :--- | :---: |
| `GET` | `/posts/1` | Retrieve post by ID | ✅ |
| `GET` | `/posts` | Retrieve all posts | ✅ |
| `POST` | `/posts` | Create a new post | ✅ |
| `PUT` | `/posts/1` | Full update of a post | ✅ |
| `PATCH` | `/posts/1` | Partial update of a post | ✅ |
| `DELETE` | `/posts/1` | Delete a post | ✅ |
| `GET` | `/posts` | Filter posts by user ID | ✅ |
| `GET` | `/posts/1/comments` | Get comments for a post | ✅ |

---