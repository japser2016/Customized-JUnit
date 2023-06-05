# Customized JUnit

This project provides a custom implementation of JUnit, a well-known unit testing framework in Java, along with an extension that supports QuickCheck-style random test generation. The custom JUnit implementation has its own annotations like `@Test`, `@Before`, `@After`, `@BeforeClass`, and `@AfterClass`, following similar behavior to the original JUnit framework. It also provides a fluent assertion API and introduces a new `@Property` annotation for QuickCheck-style testing.

## Features

1. Custom JUnit Implementation: This feature allows the user to run test cases defined with the custom `@Test` annotation. Test execution follows a certain order and respects `@Before`, `@After`, `@BeforeClass`, and `@AfterClass` annotations.

2. Fluent Assertion API: Allows chaining of assertions for objects. It provides several methods such as `isNotNull()`, `isEqualTo()`, `isInstanceOf()`, and many more. This feature also provides specific methods for `String`, `boolean`, and `int` types.

3. QuickCheck-style Testing: This feature lets the user specify test cases with `@Property` annotations that are run repeatedly with random choices of parameters, leading to more extensive and thorough testing.
