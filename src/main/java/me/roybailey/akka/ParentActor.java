package me.roybailey.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.UUID;

/**
 * ParentActor receives an instance of Command and emits an Event to the ChildActor.
 *
 * @author royrusso
 */
public class ParentActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final ActorRef childActor;
    private final ActorRef emitterActor;

    public ParentActor() {
        childActor = getContext().actorOf(Props.create(ChildActor.class), "child-akka");
        emitterActor = getContext().actorOf(Props.create(EmitterActor.class), "emitter-akka");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        log.info("Received Command: " + msg);

        if (msg instanceof Command) {
            final String data = ((Command) msg).getData();
            final Event event = new Event(data, UUID.randomUUID().toString());

            childActor.tell(event, getSelf());
            emitterActor.tell(event, getSelf());
        } else if (msg.equals("echo")) {
            log.info("ECHO!");
        }
    }
}
