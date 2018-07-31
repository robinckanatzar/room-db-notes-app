package com.robinkanatzar.roomdbnotesapp.readme;

/*

* What is LiveData?

LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware,
meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
This awareness ensures LiveData only updates app component observers that are in an active lifecycle
state.

LiveData considers an observer, which is represented by the Observer class, to be in an active state
if its lifecycle is in the STARTED or RESUMED state. LiveData only notifies active observers about
updates. Inactive observers registered to watch LiveData objects aren't notified about changes.


Sources:
- https://developer.android.com/topic/libraries/architecture/livedata
*/
