# Research AKKA

Java8 based AKKA sandpit project with various snippets of code for testing and learning bits n pieces.

```
(DemoAkka)-[Command]->(ParentActor)-[Event]->(ChildActor,EmitterActor)
(EmitterActor)-[Notification]->Publish
    Subscribe-[Notification]->(Handler1)
    Subscribe-[Notification]->(Handler2)
```

what | description
-----|------------
`gradle build` | to compile and run the tests

package | description
--------|------------
`me.roybailey.akka` | simple akka building blocks

[MIT License](LICENSE)
