package com.lyb.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获取访问次数
     * @return 第几次访问
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            //判断轮询次数是否超过int最大值，next取current+ 1
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance getinstances(List<ServiceInstance> serviceInstances) {
        //获取轮询下标
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
