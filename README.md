# JavaTraining-Week4

## 概要
Spring Boot を用いて、簡単な REST API（/hello と /api/tasks）を実装した課題の提出物である。
Java および Spring Boot は学習を始めたばかりの状態で取り組んだ。

## 開発環境
- JDK: 21.0.9 (Java SE 21 LTS)
- Spring Boot: 3.5.10-SNAPSHOT
- ビルドツール: Gradle (./gradlew, Windows は gradlew.bat)
- IDE: IntelliJ IDEA Community (日本語版)

## セットアップ手順
1. 本リポジトリを clone する。
2. プロジェクトルート（JavaTraining-Week4）で次のコマンドを実行する。
   - Windows:
       gradlew.bat bootRun
   - Mac / Linux:
       ./gradlew bootRun
3. Maven を利用する場合は、次のコマンドでも起動できる。
   - ./mvnw spring-boot:run

## 動作確認手順

1. アプリケーション起動確認
   - ブラウザで http://localhost:8080/ にアクセスし、エラーが出ていないことを確認する。
   - 起動ログに「Tomcat started on port 8080」「Started HellospringApplication」が出ていることを確認する。

2. Hello API
   - ブラウザまたは curl で次の URL にアクセスする。
       http://localhost:8080/hello
   - レスポンスとして文字列 "Hello, Spring Boot!" が返ってくることを確認する。

3. Task API（正常系）
   - 一覧取得:
       curl http://localhost:8080/api/tasks
   - タスク作成:
       curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"read book\"}" http://localhost:8080/api/tasks
   - 再度一覧を取得して、追加したタスクが含まれていることを確認する。

4. Task API（バリデーションエラー）
   - 空文字タイトルで POST する。
       curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"\"}" http://localhost:8080/api/tasks
   - HTTP 400 Bad Request が返ることを確認する。


## エラーが出た場合の対処（自分が踏んだもの）

- 最初、Gradle の Java toolchain が JDK 17 を探して起動に失敗したが、IntelliJ の Project SDK と Gradle JVM を JDK 21 に設定することで解決した。
- PowerShell で curl 実行時にセキュリティ警告が出たため、推奨どおり確認して Y を選択するか、Invoke-RestMethod(irm) を使用することで回避した。
