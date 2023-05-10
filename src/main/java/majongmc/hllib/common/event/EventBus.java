package majongmc.hllib.common.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.jodah.typetools.TypeResolver;

public class EventBus {
	public static final EventBus EVENT_BUS=new EventBus();
	private List<Consumer<Event>> processors=new ArrayList<>();
	public <T extends Event> void addListener(Consumer<T> a) {
		this.processors.add((Consumer<Event>) a);
	}
	public boolean post(Event event) {
		for(Consumer<Event> i:processors) {
			Class<Event> listenerClass = (Class<Event>) TypeResolver.resolveRawArgument(Consumer.class, i.getClass());
			if(listenerClass.isInstance(event)) 
				i.accept(event);
		}
		return event.isCanceled();
	}
	public static EventBus getModEventBus() {
		return EVENT_BUS;
	}
}
