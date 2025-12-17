🚀 第4週課題：Spring BootでREST APIを作ろう 〜環境構築と最小プロダクト〜

🎯 目的
	1.	JDK17＋Spring Boot 3の開発環境を自力で構築できる。
	2.	Spring Initializrからプロジェクトを作成し、Controller層の最小APIを公開できる。
	3.	curlやブラウザで動作確認し、再現可能なREADMEを書ける。

⸻

🔧 前提（インストール）
	•	JDK 17（または 21）
	•	IntelliJ IDEA（Community で可）または VS Code（拡張機能: Java / Spring Boot）
	•	Git / GitHub アカウント
	•	ビルドは Gradle か Maven のどちらかを選択（以降は両方の例を提示）

⸻

📘 学習範囲（書籍）
	•	『Spring Boot 3 プログラミング入門』：第1〜2章（プロジェクト作成〜Controllerの基本）

⸻

🧠 課題内容（提出必須）

課題①：プロジェクトを作成する
	1.	Spring Initializr で新規プロジェクト作成
	•	Language: Java
	•	Spring Boot: 3.x
	•	Project: Gradle or Maven
	•	Dependencies: Spring Web（のみ）
	2.	パッケージ名は com.example.hellospring とする
	3.	起動確認して、http://localhost:8080/actuator/health が200を返すこと（Actuator未導入ならアプリの起動ログのスクショでも可）

起動コマンド例
	•	Gradle: ./gradlew bootRun
	•	Maven: ./mvnw spring-boot:run

⸻

課題②：Hello APIを作る（最小Controller）
	•	HelloController を作成し、以下を実装
	•	GET /hello → 文字列 "Hello, Spring Boot!" を返す
	•	ブラウザまたは curl で動作確認
	•	curl http://localhost:8080/hello

⸻

課題③：タスクAPI（最小版 REST）

目的：JSONの入出力と、Controllerの基本責務を体験する。
	1.	エンドポイント仕様（メモリ保持でOK・DB不要）
	•	GET /api/tasks
	•	例）[{"id":1,"title":"buy milk"},{"id":2,"title":"read book"}]
	•	POST /api/tasks
	•	リクエスト例：{"title":"new task"}
	•	返却例：{"id":3,"title":"new task"}
	2.	データ保持はアプリ起動中のみで可（List<Task> を @Service で管理）
	3.	バリデーション（最低限）
	•	title が空文字やnullの場合はHTTP 400を返す
	4.	パッケージ例

com.example.hellospring
├─ HelloController.java
├─ task/
│   ├─ Task.java        （id, title）
│   ├─ TaskService.java （List<Task>の管理）
│   └─ TaskController.java（/api/tasksの入出力）
└─ HellospringApplication.java



動作確認例

# 作成
curl -X POST -H "Content-Type: application/json" \
 -d '{"title":"new task"}' http://localhost:8080/api/tasks

# 一覧
curl http://localhost:8080/api/tasks



⸻

課題④（任意・発展）
	•	DELETE /api/tasks/{id} を追加
	•	PUT /api/tasks/{id} でタイトル更新
	•	例外ハンドリング（存在しないIDなら404）
	•	@Validated + @NotBlank で入力チェック
	•	application.yml を導入し、serverポートを 8081 に変更してみる

⸻

📦 提出物

以下構成のリポジトリを作成し、プルリクエストを作成してください。
※フォルダ構成はSpring Initializrで作成されたものをベースとする。

JavaTraining-Week4/
├─ src/main/java/com/example/hellospring/...
├─ README.md
└─ screenshots/
   ├─ run_server.png         ← 起動ログやIDEのRun画面
   └─ curl_results.png       ← curlでの動作確認結果

README.md（必須項目）
	•	プロジェクト概要（1〜2行）
	•	開発環境（JDK / IDE / ビルドツールのバージョン）
	•	セットアップ手順
	•	Gradle: ./gradlew bootRun（Windowsは gradlew.bat bootRun）
	•	Maven: ./mvnw spring-boot:run
	•	動作確認手順（/hello と /api/tasks の例）
	•	エラーが出た場合の対処（自分が踏んだ内容を1〜3行で記載）

⸻

✨　提出方法

以下リポジトリに、プルリクエストを作成する形で提出してください。
また、提出方法の詳細は、以下課題提出方法をご参照ください。

リポジトリ：
[JavaTraining-Week4](https://github.com/Kohei-mana/JavaTraining-Week4.git)

課題提出方法：
[課題提出方法]()

⸻

✅ 合格基準（チェックリスト）
	•	./gradlew bootRun または ./mvnw spring-boot:run で起動できる
	•	GET /hello がブラウザまたは curl で確認できる
	•	POST /api/tasks → GET /api/tasks の一連の流れが成功する
	•	バリデーション失敗時に HTTP 400 を返す
	•	READMEの手順通りに再現できる（第三者が再現可能）

⸻

🕓 提出期限
	•	第4週の最終日（日曜 23:59まで）
	•	提出方法：GitHubリポジトリURLを提出フォームに登録

⸻

💡 ヒント
	•	Controllerは「HTTPの入り口」、Serviceは「ロジック」、（今回は未使用だが）Repositoryは「データアクセス」という責務分離を意識
	•	まずは最小で動かす → 小さく追加の順で進める
	•	エラー時は起動ログとスタックトレースをREADMEに軽くメモしておくと、次週以降の自力調査が楽になります

