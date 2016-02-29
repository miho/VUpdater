# VUpdater
[![Build Status](https://travis-ci.org/miho/VUpdater.svg?branch=master)](https://travis-ci.org/miho/VUpdater)
[![Coverage Status](https://coveralls.io/repos/github/miho/VUpdater/badge.svg?branch=master)](https://coveralls.io/github/miho/VUpdater?branch=master)

Updater for future VRL-based applications

### WARNING: Work In Progress ###

### Why? ##

The current updater for VRL-Studio does not support pre-computed delta updates and is not suitable for updating plugins. Another aspect is testability. The current updater is rather strongly coupled to the rest of the VRL ecosystem. Testing it in isolation is complicated. Currently, there are almost no unit tests and only a few integration tests.

#### File formats: ####

The idea is to move from xtream based system to [protobuf](https://github.com/google/protobuf) which allows to specify an efficient binary format (and optionally a slightly more verbose json based format) with only one specification file. Protobuf can then generate a serializable data model for many popular languages (Java, C++, Python, Go, ...). This would allow us to reuse the file format for projects in otherlanguages.

#### Existing Solutions: ###

While we might use existing solutions in the end, we will experiment with our own ideas. Currently, we only use a very small subset of popular existing solutions.

### Design Ideas ###

As a start we specify a simple repository format that supports publication of software packages (just like the current repository) and corresponding pre-computed delta-updates. It is obviously important to support signatures (ASC) and checksums (SHA1). 

#### Dependencies ####

Even thoug a dependency system is not necessary for VRL-Studio updates, it might become important for the plugin system. So, we should definitely think about dependencies.

Maybe we will use existing solutions for a plugin system updater (maven, ivy,...).
