# vertx-quartz-scheduler

## Pré requisitos

- Maven 3+
- Java 8+
- Docker 18.02.0+ 
- Docker Compose 3.6 

## Preparando ambiente

- Execute o `docker-compose up` para o inicializar Postgres, onde será armazeado os agendamentos;
- Execute `mvn clean package` na pasta do projeto para realizar o build das aplicações.

## Executando 

- Inicialize o projeto executando o seguinte comando:  
````
mvn vertx:run
````

````
set 21, 2019 1:03:14 PM com.mchange.v2.c3p0.C3P0Registry 
INFORMAÇÕES: Initializing c3p0-0.9.5.4 [built 23-March-2019 23:00:48 -0700; debug? true; trace: 10]
set 21, 2019 1:03:14 PM com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource 
INFORMAÇÕES: Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, contextClassLoaderSource -> caller, dataSourceName -> 2rvxwna5ngocduavnjy6|42f93a98, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> org.postgresql.Driver, extensions -> {}, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, forceSynchronousCheckins -> false, forceUseNamedDriverClass -> false, identityToken -> 2rvxwna5ngocduavnjy6|42f93a98, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:postgresql://localhost:5432/vertx-quartz, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 10, maxStatements -> 0, maxStatementsPerConnection -> 120, minPoolSize -> 1, numHelperThreads -> 3, preferredTestQuery -> null, privilegeSpawnedThreads -> false, properties -> {user=******, password=******}, propertyCycle -> 0, statementCacheNumDeferredCloseThreads -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, userOverrides -> {}, usesTraditionalReflectiveProxies -> false ]
set 21, 2019 1:03:15 PM br.com.emmmanuelneri.fix.FixJobExecute
set 21, 2019 1:03:15 PM br.com.emmmanuelneri.schedule.queue.ScheduleQueueJobVerticle

````
