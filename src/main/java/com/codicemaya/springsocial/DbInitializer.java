/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codicemaya.springsocial;

;
import javax.sql.DataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jhovanni <jhovanni@github.com>
 */


@Component
public class DbInitializer implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        ClassPathResource resource = new ClassPathResource("org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql");
        runScript(resource);
    }

    private void runScript(Resource resource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(resource);
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
