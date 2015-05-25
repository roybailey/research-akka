package me.roybailey.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.concurrent.atomic.AtomicLong;


/**
 * The Emitter receives an Event and emits Notifications that are picked up by subscribed Handler.
 * The Handler is listening for emitted Notification.class
 */
public class EmitterActor extends UntypedActor {

    LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);
    AtomicLong guid = new AtomicLong();

    @Override
    public void onReceive(Object msg) {

        if (msg instanceof Event) {

            LOG.info("Emitting Event: " + msg);

            String data = ((Event) msg).getData();

            getContext().system().eventStream().publish(new Notification(data, "" + guid.addAndGet(1)));
        }
    }


}
