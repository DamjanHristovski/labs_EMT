package mk.finki.ukim.mk.labs_emt.events;

import lombok.Getter;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {
    private final LocalDateTime when;
    public HostCreatedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}
