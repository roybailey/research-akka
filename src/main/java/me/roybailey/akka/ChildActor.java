package me.roybailey.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


/**
 * Child Actor receives an Event from its Parent and logs out the message.
 */
public class ChildActor extends UntypedActor {

    LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

    @Override
    public void preStart() {
        LOG.info("Starting");
    }

    @Override
    public void onReceive(Object msg) {
        LOG.info("Received Event: " + msg);
    }


}
