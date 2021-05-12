## 1. 多数据源配置支持
### application.yml配置
```
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    # 下面为连接池的补充设置，应用到上面所有数据源中
    # 初始化大小，最小，最大
    druid:
      default:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/flowchart?characterEncoding=utf8
        username: root
        password: 1234
        initial-size: 20
        min-idle: 20
        max-active: 200
        # 配置获取连接等待超时的时间
        max-wait: 60000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        time-between-eviction-runs-millis: 40000
        # 配置一个连接在池中最小生存的时间，单位是毫秒
        min-evictable-idle-time-millis: 300000
        validation-query: SELECT 1
        test-while-idle: true
        test-on-borrow: false
        test-on-return: false
        # 打开PSCache，并且指定每个连接上PSCache的大小
        pool-prepared-statements: true
        max-pool-prepared-statement-per-connection-size: 50
        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        #filters: stat,wall,log4j
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
        # 合并多个DruidDataSource的监控数据
        useGlobalDataSourceStat: true
      db2:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/flowchart?characterEncoding=utf8
        username: root
        password: 1234
        initial-size: 20
        min-idle: 20
        max-active: 200
        # 配置获取连接等待超时的时间
        max-wait: 60000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        time-between-eviction-runs-millis: 40000
        # 配置一个连接在池中最小生存的时间，单位是毫秒
        min-evictable-idle-time-millis: 300000
        validation-query: SELECT 1
        test-while-idle: true
        test-on-borrow: false
        test-on-return: false
        # 打开PSCache，并且指定每个连接上PSCache的大小
        pool-prepared-statements: true
        max-pool-prepared-statement-per-connection-size: 50
        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        #filters: stat,wall,log4j
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
        # 合并多个DruidDataSource的监控数据
        useGlobalDataSourceStat: true
```

### 创建数据源配置类

```
package cn.linkey.flowserver.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置类
 * @author alibao Y , walkwithdream@163.com
 * <p>createTime 2021-03-05 17:18 </p>
 * @version v1.0
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "default")
    @ConfigurationProperties(prefix="spring.datasource.druid.default")
    public DataSource dataSourceCmuser() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix="spring.datasource.druid.db2")
    public DataSource dataSourceIrms() {
        return DruidDataSourceBuilder.create().build();
    }
}

```
### 使用DEMO
使用@Autowired或@Qualifier引用数据源

```
@Autowired
private DataSource dataSource;//默认引用dataSource1


@Autowired
@Qualifier("dataSource2")
private DataSource dataSource;//引用dataSource2

```
### 参考地址
http://www.dengb.com/Javabc/1378667.html

