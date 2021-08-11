package org.dhj.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitAdmin
 *
 * @author wyg0405@gmail.com
 * @date 2019-11-25 15:11
 * @since JDK1.8
 * @version V1.0
 */

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        /*
         *  autoStartup 必须要设为 true ，否则Spring容器不会加载RabbitAdmin类
         */
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}

