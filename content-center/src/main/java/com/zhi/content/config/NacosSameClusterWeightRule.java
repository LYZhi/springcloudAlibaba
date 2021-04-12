package com.zhi.content.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.google.common.base.Objects;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LYZhi
 * @date 2021/4/9 下午1:38
 */
@Slf4j
public class NacosSameClusterWeightRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            //获取到配置文件中的集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            //想要请求的微服务相关名称
            String name = loadBalancer.getName();
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            //A
            List<Instance> instances = namingService.selectInstances(name, true);
            //过滤服务的所有实例b
            List<Instance> sameCluster = instances.stream()
                    .filter(instance -> Objects.equal(instance.getClusterName(), clusterName))
                    .collect(Collectors.toList());
            List<Instance> cluster = new ArrayList<>();
            if (CollectionUtils.isEmpty(sameCluster)) {
                cluster = instances;
                log.info("发生跨集群调用 name = {} instance = {} cluster = {}", name, instances, clusterName);
            } else {
                cluster = sameCluster;
            }

            Instance instance = ExtendBlance.getHostByRandomWeight2(cluster);
            log.info("选择的实例是 ==》{},端口是{}", instance, instance.getPort());

            return new NacosServer(instance);
        } catch (NacosException e) {
            log.info("出错了");
            return null;
        }
    }
}

class ExtendBlance extends Balancer {
    public static Instance getHostByRandomWeight2(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}