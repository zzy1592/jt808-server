package org.yzh.web.config;

import io.github.yezhihao.netmc.NettyConfig;
import io.github.yezhihao.netmc.TCPServer;
import io.github.yezhihao.netmc.codec.Delimiter;
import io.github.yezhihao.netmc.codec.LengthField;
import io.github.yezhihao.netmc.core.HandlerMapping;
import io.github.yezhihao.netmc.core.SpringHandlerMapping;
import io.github.yezhihao.protostar.MLoadStrategy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.yzh.web.endpoint.JTHandlerInterceptor;

@Configuration
@ConditionalOnProperty(value = "tcp-server.alarm-file.enable", havingValue = "true")
public class JSATLConfig {

    public static byte[] DataFramePrefix = {0x30, 0x31, 0x63, 0x64};

    @Order(Integer.MIN_VALUE)
    @Component
    public class Starter implements InitializingBean, DisposableBean {

        private final TCPServer alarmFileServer;

        public Starter(@Value("${tcp-server.alarm-file.port}") int port,
                       JTMessageAdapter alarmFileMessageAdapter,
                       HandlerMapping alarmFileHandlerMapping,
                       JTHandlerInterceptor alarmFileHandlerInterceptor) {

            NettyConfig jtConfig = NettyConfig.custom()
                    .setPort(port)
                    .setMaxFrameLength(2 + 21 + 1023 * 2 + 1 + 2)
                    .setLengthField(new LengthField(DataFramePrefix, 1024 * 65, 58, 4))
                    .setDelimiters(new Delimiter(new byte[]{0x7e}))
                    .setDecoder(alarmFileMessageAdapter)
                    .setEncoder(alarmFileMessageAdapter)
                    .setHandlerMapping(alarmFileHandlerMapping)
                    .setHandlerInterceptor(alarmFileHandlerInterceptor)
                    .build();
            this.alarmFileServer = new TCPServer("报警附件服务", jtConfig);
        }

        @Override
        public void afterPropertiesSet() {
            alarmFileServer.start();
        }

        @Override
        public void destroy() {
            alarmFileServer.stop();
        }
    }

    @Bean
    public JTMessageAdapter alarmFileMessageAdapter(MLoadStrategy loadStrategy) {
        return new JTMessageAdapter(loadStrategy);
    }

    @Bean
    public HandlerMapping alarmFileHandlerMapping() {
        return new SpringHandlerMapping();
    }

    @Bean
    public JTHandlerInterceptor alarmFileHandlerInterceptor() {
        return new JTHandlerInterceptor();
    }
}