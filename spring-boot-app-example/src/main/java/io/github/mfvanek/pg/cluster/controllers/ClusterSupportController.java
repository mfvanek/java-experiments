package io.github.mfvanek.pg.cluster.controllers;

import io.github.mfvanek.pg.testing.PostgreSqlClusterWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/v1/cluster")
@RequiredArgsConstructor
public class ClusterSupportController {

    private final PostgreSqlClusterWrapper clusterWrapper;
    private final AtomicBoolean failoverWasPerformed = new AtomicBoolean(false);

    @PostMapping("/failover")
    public boolean switchPrimaryHost() {
        if (failoverWasPerformed.compareAndSet(false, true)) {
            clusterWrapper.stopFirstContainer();
            return true;
        }
        return false;
    }
}
