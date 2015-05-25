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

    public static final Logger LOG = LoggerFactory.getLogger(DemoAkka.class);


    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("akka-system");

        Thread.sleep(5000);

        final ActorRef handler1 = actorSystem.actorOf(Props.create(Handler1Actor.class));
        actorSystem.eventStream().subscribe(handler1, Notification.class);

        final ActorRef handler2 = actorSystem.actorOf(Props.create(Handler2Actor.class));
        actorSystem.eventStream().subscribe(handler2, Notification.class);

        final ActorRef parentActorRef = actorSystem.actorOf(Props.create(ParentActor.class), "parent-akka");

        parentActorRef.tell(new Command("CMD 1"), null);
        parentActorRef.tell(new Command("CMD 2"), null);
        parentActorRef.tell(new Command("CMD 3"), null);
        parentActorRef.tell("echo", null);
        parentActorRef.tell(new Command("CMD 4"), null);
        parentActorRef.tell(new Command("CMD 5"), null);

        Thread.sleep(5000);
        LOG.debug("Actor System Shutdown Starting...");
        actorSystem.shutdown();
    }
}
