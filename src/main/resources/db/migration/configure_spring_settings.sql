ALTER SYSTEM SET spring.datasource.url = '';
ALTER SYSTEM SET spring.datasource.username = 'RM550629';
ALTER SYSTEM SET spring.datasource.password = 'fiap24';
ALTER SYSTEM SET spring.datasource.driver-class-name = 'oracle.jdbc.OracleDriver';
ALTER SYSTEM SET spring.jpa.hibernate.ddl-auto = 'none';
ALTER SYSTEM SET spring.flyway.enabled = 'true';
ALTER SYSTEM SET spring.flyway.locations = 'classpath:db/migration';
ALTER SYSTEM SET spring.jpa.database-platform = 'org.hibernate.dialect.OracleDialect';

