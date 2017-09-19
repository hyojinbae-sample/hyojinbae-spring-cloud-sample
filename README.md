# hyojinbae-spring-cloud-sample
- http://projects.spring.io/spring-cloud/
- https://github.com/spring-cloud-samples/fortune-teller

# spring-cloud-config
- server
- client

## document
- https://github.com/spring-cloud/spring-cloud-config
- https://spring.io/guides/gs/centralized-configuration/

## sample
- https://github.com/spring-cloud/spring-cloud-config/tree/master/spring-cloud-config-sample
- https://github.com/spring-cloud-samples/configserver

## run
- http://localhost:8081/foo/development
- http://localhost:8082/foo/development

## 設定変更
```
curl -X POST http://localhost:8081/refresh
curl -X POST http://localhost:8082/refresh
```