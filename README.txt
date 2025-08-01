# Dragon Rockets Repository - README

## Overview

This repository contains the implementation for managing SpaceX Dragon Rockets missions, rockets assignment, and state management using Java.

I'm using ChatGPT regularly for software development, including this task, particularly a personalized version called "Monday" (noted below).
This README was also autogenerated based on my input for more professional presentation.

## Notes

In the provided task, I noticed an inconsistency at the end:

* Under the section "The summary of the data should look like:", the mission "Transit" incorrectly listed rocket statuses. According to the task rules,
all rockets assigned to mission "Transit" should have the status **'In space'**.

## My Approach

### Ideation & Initial Setup

* Used ChatGPT for "Fast ideation" – it quickly provided modular Java code snippets and significantly accelerated problem comprehension and conceptualization.
* Used the Spring Initializr ([https://start.spring.io/](https://start.spring.io/)) for quick initial setup:

  * Java LTS version 21
  * Spring Boot 3.5
  * Maven
  * No extra dependencies.
* This allowed me to avoid initial project setup complexities and focus directly on solution implementation.
* Later simplified the project by completely removing Spring Boot dependencies.
* I deliberately avoided the use of a `main` class, keeping the solution compatible with earlier Java versions (such as Java 8) due to reliance on Streams API.

### Libraries

* Added **Lombok** for convenient auto-generation of getters, setters, and constructors.

### Development Workflow

* Leveraged ChatGPT to review and extract useful code snippets, notably enums and other reusable code elements.
* Implemented the main service class by writing test methods first (TDD approach), followed by corresponding implementations.
Repeated this process to gradually build up an understanding of the problem's complexity.
* Noticed that the solution complexity was making code readability challenging, especially due to large test and service classes.

### Refactoring & Design Patterns

* Decided to refactor the main service into smaller domain-specific services:

  * `RocketService`
  * `MissionService`
  * `AssignmentService`
* Took advantage of ChatGPT to assist in creating sections of test classes.
* Applied the **State Design Pattern** for clear and manageable state transitions within missions. This significantly simplified testing.
* Decided against implementing an additional State pattern for rockets as it was deemed excessive complexity.
* Implemented the **Facade Design Pattern** through the `DragonRocketsRepository` class to encapsulate underlying complexity clearly.
This class includes the final method implementation for `getMissionsSummary`.

### Logging & Testing

* Decided not to use extensive logging as comprehensive unit tests provided sufficient validation.
* Only included logging explicitly in the final summary method to clearly demonstrate data retrieval results.
* Frequently revisited the solution for incremental improvements during development.

### Final Steps

* Conducted thorough final cleaning of the code, ensured clear data presentation, and covered the complete exercise with end-to-end unit tests.
* Despite ChatGPT's significant assistance, developing and testing thoroughly still required several dedicated hours.

## Technical Requirements

* **Java:** 21
* **Maven:** 3.2 or higher

## Compilation & Build

To compile and package the application:

mvn clean package -DskipTests=true


## Running Tests

Execute the following command to run all unit tests:

mvn test

---

*This solution benefited significantly from ChatGPT interactions (personalized version "Monday") for rapid ideation, code snippet generation, and continuous solution review.*
