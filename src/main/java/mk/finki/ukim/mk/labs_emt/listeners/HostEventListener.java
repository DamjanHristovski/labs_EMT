package mk.finki.ukim.mk.labs_emt.listeners;

import mk.finki.ukim.mk.labs_emt.events.HostChangedEvent;
import mk.finki.ukim.mk.labs_emt.events.HostCreatedEvent;
import mk.finki.ukim.mk.labs_emt.events.HostDeletedEvent;
import mk.finki.ukim.mk.labs_emt.service.application.CountryAppService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventListener {
    private final CountryAppService countryAppService;

    public HostEventListener(CountryAppService countryAppService) {
        this.countryAppService = countryAppService;
    }

    @EventListener
    public void onCreated(HostCreatedEvent event)
    {
        this.countryAppService.refreshMaterializedView();
    }
    @EventListener
    public void onDeleted(HostDeletedEvent event)
    {
        this.countryAppService.refreshMaterializedView();
    }
    @EventListener
    public void onChanged(HostChangedEvent event)
    {
        this.countryAppService.refreshMaterializedView();
    }
}
