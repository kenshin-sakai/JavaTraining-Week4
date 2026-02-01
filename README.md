# JavaTraining-Week5

## プロジェクト概要
Spring Boot と Spring Data JPA を用いて，タスク（id・title・completed）を管理する REST API を作成した．
H2 Database によるデータ永続化，入力バリデーション，例外ハンドリングを実装している．

---

## 環境構築手順（DB設定を含む）

### 動作環境
- OS：Windows 11
- Java：21.0.9 (LTS)
- ビルドツール：Gradle
- フレームワーク：Spring Boot 3.x
- DB：H2 Database（インメモリ）

### DB設定（application.yml）
src/main/resources/application.yml に以下を設定している．

spring:
  datasource:
    url: jdbc:h2:mem:tasks;DB_CLOSE_DELAY=-1
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console

---

## 実行・確認手順（curl例）

### アプリ起動
.\gradlew.bat bootRun

### API確認
$base = "http://localhost:8080/api/tasks"

GET:
curl.exe -i $base

POST:
{"title":"買い物"}

PUT:
{"title":"買い物（更新）","completed":true}

DELETE:
curl.exe -i -X DELETE "$base/1"

---

## 例外ハンドリングの動作例

### 400 Bad Request（バリデーションエラー）
{"title":""}

レスポンス例：
{"details":{"title":"タイトルを入力してください"},"error":"Validation failed"}

---

### 404 Not Found（存在しないID）
curl.exe -i -X DELETE "$base/9999"

レスポンス例：
{"error":"Task not found"}

---

### 確認事項
- CRUD が正常動作する
- H2 にデータが永続化される
- Validation エラー時に 400
- 不正 ID 指定時に 404

以上を確認済みである．
'@ | Set-Content README.md -Encoding UTF8
