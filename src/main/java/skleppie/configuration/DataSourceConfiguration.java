package skleppie.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:mysql://localhost:3306/db_app?useSSL=false")
                .username("devuser")
                .password("Charlie89")
                .driverClassName("com.mysql.cj.jdbc.Driver");

        return builder.build();
    }
}
