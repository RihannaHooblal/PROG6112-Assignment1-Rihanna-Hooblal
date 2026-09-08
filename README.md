## Overview
This project implements the requirements in **Programming Assignment 1** using Java and Maven. It is designed to open directly in Apache NetBeans as a Maven project.

The project contains:
- **Section A:** TV Series Management Application.
- **Section B:** A separate Smart Fitness Centre console application demonstrating arrays, loops, inheritance, constructors and information hiding.
- **JUnit 5 unit tests** for both sections
- ## Section A unit tests
`SeriesTest.java` contains all seven tests requested in the paper:
- `TestSearchSeries()`
- `TestSearchSeries_SeriesNotFound()`
- `TestUpdateSeries()`
- `TestDeleteSeries()`
- `TestDeleteSeries_SeriesNotFound()`
- `TestSeriesAgeRestriction_AgeValid()`
- `TestSeriesAgeRestriction_SeriesAgeInValid()`

Run tests in NetBeans by:
1. Right-click the project.
2. Select **Test**.

## Section B - Smart Fitness Centre Management System

### Main class
Run:
`FitnessCentreApp.java`

The application demonstrates:
- **Arrays:** a `FitnessMember[]` array stores members.
- **Advanced/two-dimensional arrays:** an `int[][]` array stores weekly attendance.
- **Loops:** nested loops calculate attendance totals and report totals.
- **Inheritance:** `RegularMember` and `PremiumMember` inherit from abstract `FitnessMember`.
- **Constructors:** all member classes use constructors.
- **Information hiding:** class attributes are private and accessed through methods.
- **Method overriding/polymorphism:** each membership type implements `calculateMonthlyFee()` differently.
- **Console report:** `buildReport()` creates the final report.

## Section B unit tests
`FitnessCentreAppTest.java` tests:
1. Regular member fee calculation.
2. Premium member fee calculation and inheritance behaviour.
3. Report contents and totals.
