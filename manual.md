
# Manual



# Basic Interfaces

## Round Strategy

```
void performRound(Population<I> population);
```

**purpose**: Determines how each round of the genetic algorithm is run, an key part
 * of the genetic algorithm. Generally use either generational or steady state.

### Generational Round Strategy (implementation)

**description**: Generation round strategy, where each individual in the population is replaced each round by a new set of offspring.

### Steady State Round Strategy (implementation)

**description**: Steady state round strategy where a round consists of a single individual being removed, and a single individual being added.

## Populator

```
I getIndividual();
```

**purpose**: To create new individuals, mainly used to seed populations at initialisation.

**example**: See StringPopulator, which generates random strings of a given length.

**notes**:

## Population Model

```
int getPopulationUnitSize();

void writeData(RunData runData);

void doPerformRound(RoundStrategy roundStrategy, RunData runData);

boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration);
```

**purpose**: A model representing a population or set of populations within an algorithm.

### Single Population Model (implementation)

**description**: A simple implementation of population model with a single population of a given size.

### Multi-deme Population Model (implementation)

**description**: A Multi-deme population model where several populations are evolved in isolation from each other. A migration
 * strategy is used to move individuals between these demes.

**notes**:
 - The Migration Model interface is used to determine how migrations between demes are handled.

    ```
    void performMigration(PopulationModel<I> populationModel);
    ```

 - A Uniform Migration Model is one such implementation where, with a given probability, an individual is taken from each population. These selected individuals are then shuffled and placed back randomly into populations.

## Fitness Function

```
double calculateFitness(I individual, Population<I> population);
```

**purpose**: Calculates the fitness of an individual.

**notes**:
 - Define your own, either with a class implementing this, or with a lambda expression.
 - This is typically passed to the population model, which handles individual fitness.

## Mutator

```
I mutate(I individual);
```

**purpose**: To mutator or alter individuals.

**example**: See PerLociStringMutator, which mutates each loci independently with a fixed probability.

**notes**:
 - If your individuals are represented using mutable objects, create a clone of the input individual.

### Mutator Pool

**purpose**: A collection of mutators - uses same interface as a normal mutator.

**example**: See StochasticOneMutatorPool and StochasticAllMutatorPool.

**notes**:
 - Provides a convenient way to pool together a set of mutation operations.

## Combiner

```
int getNumberPerCrossover();

I combine(List<I> individuals);
```

**purpose**: To combine a list of individuals into a new individual. Used to implement cross-over operators.

**example**: See StringUniformCrossover.

**notes**:
 - Used to implement cross-over and other similar approaches.
 - getNumberPerCrossover should return the number of individuals that should be passed in to combine.
 - The round strategy will then ensure that this is called correctly.
