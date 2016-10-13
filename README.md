# Genetiq

Genetic programming library, implemented in Java.
If you are interested in the library then please feel free to contribute!

See manual for more detailed docs.

## High-Level Goals

 - Object orientated approach to genetic algorithms.
 - Cohesive and interchangable modules.
 - Provide support for a range of popular GA techniques, and allow extensibility for new ones.
 - Easy to use and understand.
 - Provide support for multi-core processing.

## Supported Algorithms

| Algorithm   | Description                                                                                      | Parallel Support? |
|-------------|--------------------------------------------------------------------------------------------------|-------------------|
| GA [x]      | A simple genetic algorithm runner which support multiple population models and round strategies. | None.             |
| CCGA [ ]    | A co-operative co-evolutional genetic algorithm.                                                 |                   |

## Included Representation

 - String
    - Comes with support for basic objects such as populators, mutators, crossover, etc.
    - Simple, good for examples and proof of concept.
