package com.robinkanatzar.roomdbnotesapp;

/* README

* What is Room?

The Room persistence library provides an abstraction layer over SQLite to allow for more robust
database access while harnessing the full power of SQLite.

The library helps you create a cache of your app's data on a device that's running your app.
This cache, which serves as your app's single source of truth, allows users to view a consistent
copy of key information within your app, regardless of whether users have an internet connection.

Apps that handle non-trivial amounts of structured data can benefit greatly from persisting that
data locally. The most common use case is to cache relevant pieces of data. That way, when the device
cannot access the network, the user can still browse that content while they are offline.
Any user-initiated content changes are then synced to the server after the device is back online.

Because Room takes care of these concerns for you, we highly recommend using Room instead of SQLite.

* 3 Main Parts

There are 3 major components in Room:

(1) Database: Contains the database holder and serves as the main access point for the underlying
connection to your app's persisted, relational data.

The class that's annotated with @Database should satisfy the following conditions:

(a) Be an abstract class that extends RoomDatabase.
(b) Include the list of entities associated with the database within the annotation.
(c) Contain an abstract method that has 0 arguments and returns the class that is annotated with @Dao.

At runtime, you can acquire an instance of Database by calling Room.databaseBuilder() or Room.inMemoryDatabaseBuilder().

(2) Entity: Represents a table within the database.

(3) DAO: Contains the methods used for accessing the database.


* Sources and Further Reading
- Android documents on Room: https://developer.android.com/topic/libraries/architecture/room

 */