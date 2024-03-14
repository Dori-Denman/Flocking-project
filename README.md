# csc395-sim-dillahunt

#### Model Builder: Noah Dillahunt
#### GUI Support: Dori Denman
#### Dev Support: Xeng Yang, Ethan Kempenich, Godwill Afolabi, Guleid Eleie

<hr>

This is a simulation of an infectious disease and how it can spread within a population. There are individuals within a population who are moving around and coming into contact with others. Individuals can either be infected and potentially infect others or they can be healthy and become infected. The likelihood of infection depends on how infectious the disease is and the level of contact between an infected and healthy individual. 


<hr>

*Resources:*

There are several examples of simulatons of infectious disease. Here are just a few:
- Very detailed and professional looking simulation. https://www.youtube.com/watch?v=gxAaO2rsdIs&ab_channel=3Blue1Brown
- Basic simulation (looks very similar to our framework): https://juanitorduz.github.io/infection_sim/

<hr>

To compile, make sure the `bin` folder exists (at the same level of `src`). Move into the src folder, then

```
javac -d ../bin main/Simulation.java
```

`-d` is the destintation directory. The folder structure will be maintained within bin. Because Simulation.java references every other file in the project, this will prompt the compilation of every other java file.

To execute, from within the src folder
```
java -cp ../bin/ main.Simulation
```

`-cp` refers to the classpath, meaning where all the class files have been generated. Java knows to look inside this director for all .java files in the various packages. The `main()` method is inside the Simulation.java file, which is inside the `package main`.


