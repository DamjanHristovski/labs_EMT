package mk.finki.ukim.mk.labs_emt.Jobs;

import mk.finki.ukim.mk.labs_emt.service.application.HostAppService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final HostAppService hostAppService;

    public ScheduledTasks(HostAppService hostAppService) {
        this.hostAppService = hostAppService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        this.hostAppService.refreshmaterializedView();
    }
}

