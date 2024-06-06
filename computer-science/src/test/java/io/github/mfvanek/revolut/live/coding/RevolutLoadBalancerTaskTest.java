package io.github.mfvanek.revolut.live.coding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;

import io.github.mfvanek.revolut.live.coding.RevolutLoadBalancerTask.LoadBalancer;
import io.github.mfvanek.revolut.live.coding.RevolutLoadBalancerTask.LoadBalancer.RegisterResult;
import org.junit.jupiter.api.Test;

class RevolutLoadBalancerTaskTest {

    @Test
    void registerShouldWork() {
        LoadBalancer balancer = new LoadBalancer();
        assertThat(balancer.register("b1"))
                .isEqualTo(RegisterResult.SUCCESS);
        assertThat(balancer.size())
                .isOne();
    }

    @Test
    void registerShouldAddOnlyOnce() {
        LoadBalancer balancer = new LoadBalancer();
        assertThat(balancer.register("b1")) // first
                .isEqualTo(RegisterResult.SUCCESS);
        assertThat(balancer.register("b1")) // second
                .isEqualTo(RegisterResult.ALREADY_REGISTERED);
        assertThat(balancer.size())
                .isOne();
    }

    @Test
    void registerShouldBeBoundedByTen() {
        LoadBalancer balancer = new LoadBalancer();
        for (int i = 0; i < 10; i++) {
            assertThat(balancer.register("b" + i))
                    .isEqualTo(RegisterResult.SUCCESS);
        }
        assertThat(balancer.register("b11"))
                .isEqualTo(RegisterResult.QUOTA_EXCEEDED);
        assertThat(balancer.size())
                .isEqualTo(10);
    }

    @Test
    void getShouldWork() {
        LoadBalancer balancer = new LoadBalancer();
        balancer.register("b1");
        assertThat(balancer.get())
                .isEqualTo("b1");
    }

    @Test
    void getShouldThrowExceptionWhenRegistryEmpty() {
        LoadBalancer balancer = new LoadBalancer();
        assertThatThrownBy(balancer::get)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Registry is empty");
    }

    @Test
    void getShouldWorkWithRandom() {
        LoadBalancer balancer = new LoadBalancer();
        balancer.register("b1");
        balancer.register("b2");

        Set<String> backends = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            backends.add(balancer.get());
        }
        assertThat(backends)
                .hasSize(2);
    }
}
