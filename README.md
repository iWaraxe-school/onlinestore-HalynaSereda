# 2. Maven (RU)
## Task #2
Before starting the implementation of `OnlineStore,` we need to prepare the project structure and set up a dependency manager.
We will use Maven’ to handle our project dependencies and source code build.
Create a multi-module maven project in `IntelliJ Idea,` with such modules:
1. OnlineStore (this is the parent module)
2. domain
3. store
4. consoleApp
### Useful links
If you are unfamiliar with Maven, you can get your first impression of this build tool by visiting the following resources.
Additional read
- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)
- [Naming Maven](http://maven.apache.org/guides/mini/guide-naming-conventions.html)
- [Naming Java](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
## Learning Materials
If it’s still vague and unclear what to do, you’d better read a series of educational materials to help you better understand ’Maven’. Video lectures or, better to say, screencasts will show you what you have to do in detail.
### Maven lectures (part1)
#### 1.1. Installing maven
Large programs Getting to know Maven Downloading and installing Maven Environment Variables Local Maven repository […](https://www.craft.do/s/nXFWzvhq7uns4v)
#### 1.2. Creating a Maven project
One of the things Maven has standardized on first is the project description. Before Maven, every IDE had its own project file that stored information about the project and its build (and often in binary form) […](https://www.craft.do/s/z9hEumrEVNB8eE)
#### 1.3. Archetypes in Maven.
There is another way to create a Maven project in IDEA - based on an archetype […](https://www.craft.do/s/b9scnE0IQ1cRVc)
#### 1.4. Dependencies in a Maven project
How to search for libraries in the Maven Repository dependencies repository […](https://www.craft.do/s/fhnUlNxM9k28fh)
#### 1.5. Phases of a Maven project
List of project phases Project assembly Work cycles […](https://www.craft.do/s/KmpgDcxHPMIX9A)
#### 1.6. Maven plugins
Getting to know plugins Lifecycle and plugins Goals in Maven - goals […](https://www.craft.do/s/A7nMFw1DN6PlCC)
#### 1.7. Maven properties
Variables in Maven - properties Predefined variables in Maven […](https://www.craft.do/s/fiWjasjnJ2dJax)
#### 1.8. Building a Maven project
The project’s structure is described in the pom.xml file, which must be located in the project’s root folder. The contents of the project file are as follows […](https://www.craft.do/s/Kig0GT6fYEj3HN)
### Maven (part2)
#### 2.1. Advanced Maven project build
List of plugins to build in Maven Compilation plugin maven-compiler-plugin Build jar file maven-jar-plugin Generate buildnumber-maven-plugin plugin […](https://www.craft.do/s/ub3RIQ8ckTbyk6)
#### 2.2. File management during the build of a Maven project
Resource copying plugin maven-resources-plugin Resource filtering with maven-resources-plugin Source inclusion plugin maven-source-plugin Dependency copying plugin maven-dependency-plugin […](https://www.craft.do/s/rddAm3dbwGW3cv)
#### 2.3. Building a war-project
Differences between war and jar files. The maven-war-plugin to create a war file. Building a web application based on SpringBoot  […](https://www.craft.do/s/Qi2vUuJQqVsG7T)
#### 2.4. Testing a Maven project at build time
Testing in Maven. Test setup. How to quickly eliminate broken tests  […](https://www.craft.do/s/iIjURtPcg4SDC3)
#### 2.5. Deploying a project with Maven
Using maven-deploy-plugin. Deploying a web application in Tomcat using Maven. Deploying with the Cargo Plugin. Deploying with IntelliJ IDEA  […](https://www.craft.do/s/2bSi6vQyqTOZBz)
#### 2.6. Useful Maven plugins
Your own maven repository on GitHub. Package an assembly into a Docker image  […](https://www.craft.do/s/0dTWSblp2Quu6m)
## Video Lectures
1. [Maven Basics] (https://drive.google.com/file/d/1Vl4Zcz8iSx2HM41VOHlPs2VvYppkI769/view?usp=sharing) - Вasics of maven. How to add and manage dependencies, and how to create multimodular projects.
2. [How to use GitHub Classroom] (https://drive.google.com/file/d/1XJdz2a37VsBvBEmMVYRjcHOAEc5QlNPi/view)   - Workflows with GitHub Classroom. Common mistakes.
