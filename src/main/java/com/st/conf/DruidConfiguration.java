package com.st.conf;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/18.
 */
@Configuration
public class DruidConfiguration {

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();

        return ds;
    }
}
