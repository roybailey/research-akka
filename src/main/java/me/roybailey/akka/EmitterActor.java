package me.roybailey.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.concurrent.atomic.AtomicLong;


/**
 * The Emitter receives a Command and emits Events that are picked up by subscribed Handler. The Handler is listening for emitted Event.class
 */
public class EmitterActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    AtomicLong guid = new AtomicLong();

    @Override
    public void onReceive(Object msg) {

        if (msg instanceof Event) {

            log.info("Emitting Event: " + msg);

            String data = ((Event) msg).getData();

            getContext().system().eventStream().publish(new Notification(data, "" + guid.addAndGet(1)));
        }
    }


}
