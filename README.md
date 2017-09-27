# ~~世界で一番複雑な~~HelloWorld
クラウドネイティブ版HelloWorld

# クラウドネイティブアプリケーションとは
![Cloud Native](https://d1fto35gcfffzn.cloudfront.net/images/topics/cloudnative/diagram-cloud-native.png)

- [クラウドネイティブアプリケーションとは](https://pivotal.io/jp/cloud-native)
- [【連載】いまさら聞けないマイクロサービスの基本 (1) マイクロサービスを理解するための3つのポイント](https://news.mynavi.jp/itsearch/article/devsoft/1594)

# feature
- Distributed/versioned configuration
- Service registration and discovery
- Routing
- Service-to-service calls
- Load balancing
- Circuit Breakers
- Global locks
- Leadership election and cluster state
- Distributed messaging

# hyojinbae-spring-cloud-sample
- http://projects.spring.io/spring-cloud/
- https://github.com/spring-cloud-samples/fortune-teller
- http://sivalabs.in/2017/08/spring-cloud-tutorials-introduction-to-spring-cloud-config-server/

# spring-cloud-config
![](https://i0.wp.com/sivalabs.in/wp-content/uploads/2017/08/config.png?resize=768%2C274)

- server
- client

bootstrap.ymlはapplication.ymlより先に読み込まれる。

## document
- https://github.com/spring-cloud/spring-cloud-config
- https://spring.io/guides/gs/centralized-configuration/

## sample
- https://github.com/spring-cloud/spring-cloud-config/tree/master/spring-cloud-config-sample
- https://github.com/spring-cloud-samples/configserver

## 実行してみる
※ /envエンドポイントのjsonが見やすくするために、Json formatter(chrome plugin)を追加する。

### config server 
- http://localhost:8888/env
- http://localhost:8888/configclient1/default
- http://localhost:8888/configclient2/default

### config client
- http://localhost:8081/env
- http://localhost:8082/env

## 設定変更
### 各アプリの/refreshを叩く方法
gitにプッシュした後、下記のAPIを叩く。

```
curl -X POST http://localhost:8081/refresh
curl -X POST http://localhost:8082/refresh
```

### auto refresh config chages
http://sivalabs.in/2017/08/spring-cloud-tutorials-auto-refresh-config-changes-using-spring-cloud-bus/

Spring Cloud Busモジュールを利用することで、複数のアプリケーションをメッセージブロッカーでリンクし、設定変更をブロードキャストすることができる。

```
cd ./hello-world-message-broker-server
docker-compose up
```

gitにプッシュした後、下記のAPIを叩く。

```
curl -X POST http://localhost:8081/bus/refresh
```
※ある1つのアプリで/bus/refreshするだけで、すべてのアプリの設定が更新される

## 設定の暗号化
DB接続先などの情報がうっかり漏洩するのを防ぐ

[参考したサイト](https://patrickgrimard.io/2016/03/04/encrypting-and-decrypting-configuration-property-values-in-spring-cloud/)
https://www.youtube.com/watch?v=1mwDM5xbWvU

### 事前準備
[Java Cryptography Extension (JCE) Unlimited Strength をダウンロード](http://www.oracle.com/technetwork/java/javase/downloads/index.html)


以前のファイルのbackupし、上記のファイルで置き換える（要解凍）

```
cd $JAVA_HOME/jre/lib/security
sudo mv US_export_policy.jar US_export_policy.jar.bak
sudo mv local_policy.jar local_policy.jar.bak
```

キー生成

```
keytool -genkeypair -alias mytestkey -keyalg RSA \
  -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" \
  -keypass changeme -keystore server.jks -storepass letmein \
  -validity 365
```

管理者権限でcmdを実行（windowsの場合)
※Javaのbinパスは適宜変更してください。
```
cd "C:\Program Files\Java\jdk1.8.0_144\bin"
keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass changeme -keystore %HOMEPATH%\server.jks -storepass letmein -validity 365
```

### configサーバの/src/main/resources/bootstrap.yml
複合されなくてはまっていたが、encryptから始まる設定をapplication.ymlからbootstrap.ymlに移動したら複合化できた
※[application.ymlには定義しても機能しない](https://github.com/spring-cloud/spring-cloud-config/issues/795#issuecomment-332223035)

### 値を暗号化/複合化する
設定を利用するクライアント側は、設定がローカルのファイルとして存在するか、config-serverにあるか、暗号化されているかどうかを気にせず、
設定を読み込む方法は同じ。

```
@Value("${xxxxx}")
```

### 暗号化・複合化エンドポイント
```
curl -d localhost:8888/encrypt 'hello'
curl -d localhost:8888/encrypt 'world'
```

```
curl -d localhost:8888/decrypt 'xxxxxxxxxxxxxxxx'
```

### 試す
- http://localhost:????/message

※暗号化されたメッセージが複合化されて見れる

# spring-cloud-netflix
- The patterns provided include Service Discovery (Eureka)
- Circuit Breaker (Hystrix)
- Intelligent Routing (Zuul) 
- Client Side Load Balancing (Ribbon)

[Spring Cloud Netflix](https://cloud.spring.io/spring-cloud-netflix/)

## eureka
[Introduction to Spring Cloud Netflix – Eureka](http://www.baeldung.com/spring-cloud-netflix-eureka)

![](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7w0eGIRzTzN5H1BJgx2iwJLhQ8MeLL7mckIi3V37poLgocTbHRg)

- Service Registry(Eureka Server)
- Eureka Client(自分自身をService Registryに登録する)
- Feign Client

※サービス間連携のためのホスト名・ポート番号ハードコーディングしなくてよくなる
※portが自動で張られるため、ローカルででもrolling updateが簡単に実現できる
### Eureka Server
Dash Board (http://localhost:8761/)

### Eureka Client
- HelloService
- WorldService

http://localhost:????/message
※port番号はEureka Server Dashboardから確認できます。
### Feign Client
HelloWorldService(http://localhost:8080)