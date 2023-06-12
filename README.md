# 3. OOP
## Materials
- [OOP](https://docs.oracle.com/javase/tutorial/java/concepts/index.html)
- [Lecture 3](https://drive.google.com/file/d/17R4SCXEd9B8a89UsJ-nLNYMFwM\_p8Fn9/view?usp=sharing)
- [Lecture 4](https://drive.google.com/file/d/14RC5dJno\_FcSfBe\_FAd9zeqpFSUj8odH/view?usp=sharing)
- [Reflection](https://docs.oracle.com/javase/tutorial/reflect/)
- [Reflections Lib](https://github.com/ronmamo/reflections)
- [Faker](https://github.com/DiUS/java-faker)

## VideoLectures
-  [03.oop.u1. Classes & Object](https://youtu.be/e8CFYiHeUi0)
-  [03.oop.u2. Reflections. ENUM](https://youtu.be/RrqTz-Qbtqg)
-  [03.oop.u3. OOP principles](https://youtu.be/zG-MNwEW6JY)
-  [03.oop.u4. Equals&HashCodes. Abstract Classes](https://youtu.be/zPmcv6xRoag)
-  [03.oop.u5. Wrapper Classes](https://youtu.be/W5uTGYVeslk)
-  [03.oop.u6. Interfaces](https://youtu.be/JGgKbMcDnOo)

## Task #3
Before you start creating the source code, make sure you understand the Object-Oriented Programming (OOP) principles, which are essential for building a well-structured Java program.
To complete this task, you will need to create the following classes:
1. `Product`: This class should have three fields - `name`, `rate`, and `price`.
2. `Category`: This class should have a `name` field to represent a product category and a list of `Product` objects.
3. `Store`: This class should have a list of `Category` objects to represent the various product categories available in the store.
4. `RandomProductGenerator`: A utility class that uses the `Faker` library to generate a `Product` with random field values. The method for generating product names should use different fakers for different subcategories.
5. `RandomStorePopulator`: A utility class that populates the `Store` and `Category` objects with generated data.
6. `StoreApp`: A class with a main method that runs the store scenario. When you run the main method, the application should initialize the store with categories and products and then display the store data in a readable format.
In addition to these classes, create at least three subclasses of the `Category` class. Each subclass should represent a different product category, such as bikes, phones, milk, or any other category you like. You can explore the various fakers in the `Faker` library for inspiration. Be creative when choosing category names!
These categories should be loaded dynamically at runtime from a base category package using the `Reflections library`.
When printing the store's content, you may need to override the `toString()` methods in your classes. Remember that `String` is an immutable class, so consider using `String.format()` or `StringBuilder` to create a well-formatted output. Good luck, and have fun learning Java!

++Read this file when you have difficulty, or even before.
## Hints and FAQs
### Where to put classes?
To keep the project organized, it's recommended to put classes in relevant modules:
- `StoreApp`: This module contains the `StoreApp` class with the `main()` method. Its purpose is to run the store by creating an instance of `Store`, filling it with products, and printing all the categories and products. All the code related to running the store should be placed in this module.
- `Product`, `Category`, `BookCategory`, `PhoneCategory`, `FoodCategory`: These classes should be placed in the `domain` module. This module should contain only domain classes that answer the question: "What is contained in the store?" In the future, you could add classes like `User`, `Admin`, `Cart`, etc. in this module.
- `Store`, `RandomStorePopulator`: These classes should be placed in the store module. This module should contain all the business logic that answers the question: "How is the business logic implemented?" You may add interfaces, database connection logic, and other auxiliary classes in this module. Consider creating an auxiliary class like `StoreHelper` to transfer part of the business logic from `Store`, so that the `Store` class complies with the `Single Responsibility` principle and doesn't contain many loosely related methods.
### Package names
You can name the packages in modules as follows: 
- `com.coherentsolutions.consoleapp`,
- `com.coherentsolutions.domain`,
- `com.coherentsolutions.store`.
Inside each module, group related classes in packages that make sense to you. For example, you may group child categories in the `com.coherentsolutions.domain.categories` package.
### Working in small chunks
This task has three sub-tasks:
- Creation of domain classes **(20%)**;
- Connection of `Faker` and generation of random product names, prices and rates **(30%)**;
- Usage of `reflections` to create instances of child categories and add them into the category list **(50%)**!
### How to work smoother and faster?
To work smoothly and efficiently, consider creating a new branch in your `GitHub` project called `03OOP`. Then, create a branch from it to work on each sub-task. For example:
- `03OOP-domain`: In this branch, create domain classes and create a pull request to merge with the 03OOP branch.
- `03OOP-generator`: In this branch, work with `Faker` and `RandomProductGenerator`. Then, create a pull request to merge with the `03OOP` branch.
- `03OOP-reflections`: In this branch, work with reflections. Then, create a pull request to merge with the 03OOP branch.
Breaking down the task into smaller portions has several benefits. It allows you to focus on one sub-task at a time and avoid making big mistakes. It also makes it easier for your teacher to review your code and give you feedback. When you submit each pull request, your teacher can review it within 10-20 minutes and provide feedback promptly.
Remember to merge each branch with the `03OOP` branch before starting the next sub-task. This will ensure that you're working on the latest code and minimize the risk of conflicts.
