# Phoenix

## State of development

***This framework is under heavy development and may have breaking changes
shipped with every release.***

See the [project](https://github.com/users/yannickkirschen/projects/1/views/1)
for more details.

- :heavy_check_mark: configuration
  model ([#1](https://github.com/yannickkirschen/phoenix/issues/1))
- :construction: basic
  architecture ([#2](https://github.com/yannickkirschen/phoenix/issues/2))
- :x: logo ([#3](https://github.com/yannickkirschen/phoenix/issues/3))
- :x: installation and update
  routines ([#13](https://github.com/yannickkirschen/phoenix/issues/13)
  , [#14](https://github.com/yannickkirschen/phoenix/issues/14)
  , [#15](https://github.com/yannickkirschen/phoenix/issues/15)
  , [#16](https://github.com/yannickkirschen/phoenix/issues/16))

## Abstract

Like a phoenix arising out of its own ashes, the Phoenix framework brings Java
back on the desktop. Built as a UI library on top of good old Swing, it features
a simple way to create rich client applications.

Developing standalone, rich client applications is fairly easy - even with raw
Swing. But when it comes to installing updates or even communicating with a
remote server (e.g. an API for your application), things get a little tough. And
what the heck do you do when your backend undergoes a breaking API change?

### More than a library

That's where Phoenix comes in place, as it is more than just a UI library.
Featuring tools and methods to handle updates at an ease and providing a common,
programmatic way to communicate with a backend API service, Phoenix is the most
advanced and elaborate way to create modern rich client applications.

### Go big

Users want to have a uniform interface. "Uniform" in this context also means
that users can operate a single application and complete all their tasks within
this application.

Here, too, Phoenix shines: each Phoenix application consists of independent
modules, so-called views, which in turn contain concrete components, the
dialogs. Views and dialogs are automatically recognized, loaded and then
displayed in a standardized way when the application is started.

This allows an application to be extended and developed by very many teams
without much effort.

### Satisfying developer's needs

As developers, we have special requirements to the software we build and tools
we use. When we have an application with a frontend and a backend, we want to
access different versions of our application at the same time. A cloud
environment makes this very easy: we just use different URLs for each
deployment.

But what if we have a rich client application? It is not always possible to
connect one version of the frontend to a different version of the backend. And
even if ... Do we really want to install multiple versions of our application
next to each other on our local machine?

Again, Phoenix has the solution: you just choose the version of your application
at startup and Phoenix makes sure that all UI elements are downloaded for the
desired version.

## At a glance

- easy to use UI library
- update handling
- multiple stages from a single UI

## Motivation

For many people, Swing is an outdated technology and Java is all but dead on the
desktop. Swing may be old, but it's certainly not obsolete. And what's so bad
about something being old anyway? If something has been around for so long, it
is mature and proven.

With that in mind, I started this project. For me, Java on the desktop is far
from dead and the highly praised web technologies don't solve all problems
either - and often create new ones. Java on the desktop is not dead until we
declare it dead! Phoenix is designed to help developers build Java applications
for the desktop as easily as possible.

But I am also realistic. Probably, Phoenix will never interest anyone, never
even be considered by anyone. And Java will probably never return to the
desktop. But why mope? We can just have some fun with this beautiful language!

That's my main motivation to develop Phoenix: *I just want to have some fun.*
And believe me: I had fun! It's fascinating what you learn in such a project,
for example about architecture or details of Java. It is definitely worth it!

So, if you want to have some fun, get a cup of coffee in Java City, turn on your
computer and contribute to this project!
