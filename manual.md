
# Manual



# Basic Interfaces

## Populator

```
I getIndividual();
```

purpose: To create new individuals, mainly used to seed populations at initialisation.

example: See StringPopulator, which generates random strings of a given length.

notes:

## Fitness Function

```

```



## Mutator

```
I mutate(I individual);
```

purpose: To mutator or alter individuals.

example: See PerLociStringMutator, which mutates each loci independently with a fixed probability.

notes:
 - If your individuals are represented using mutable objects, create a clone of the input individual.

### Mutator Pool

purpose: A collection of mutators - uses same interface as a normal mutator.

example: See StochasticOneMutatorPool and StochasticAllMutatorPool.

notes:
 - Provides a convenient way to pool together a set of mutation operations.

## Combiner

```
int getNumberPerCrossover();

I combine(List<I> individuals);
```

purpose: To combine a list of individuals into a new individual. Used to implement cross-over operators.

example: See StringUniformCrossover.

notes:
 - getNumberPerCrossover should return the number of individuals that should be passed in to combine.
