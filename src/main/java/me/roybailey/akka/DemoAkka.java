package me.roybailey.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main runtime class.
 */
public class DemoAkka {

    public static final Logger log = LoggerFactory.getLogger(DemoAkka.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("akka-system");

        Thread.sleep(5000);

        final ActorRef handler = actorSystem.actorOf(Props.create(HandlerActor.class));
        actorSystem.eventStream().subscribe(handler, Notification.class);

        final ActorRef parentActorRef = actorSystem.actorOf(Props.create(ParentActor.class), "parent-akka");

        parentActorRef.tell(new Command("CMD 1"), null);
        parentActorRef.tell(new Command("CMD 2"), null);
        parentActorRef.tell(new Command("CMD 3"), null);
        parentActorRef.tell("echo", null);
        parentActorRef.tell(new Command("CMD 4"), null);
        parentActorRef.tell(new Command("CMD 5"), null);

        Thread.sleep(5000);
        log.debug("Actor System Shutdown Starting...");
        actorSystem.shutdown();
    }
}
