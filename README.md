🧩 第5週課題：DBと連携するSpring Bootアプリを作ろう

〜データ永続化・入力検証・例外処理〜

🎯 目的
	1.	**Spring Boot + JPA + H2（または PostgreSQL）**でデータを永続化する。
	2.	入力バリデーション（@Validated, @NotBlank, @Size, etc.）を実装する。
	3.	例外ハンドリング（@ControllerAdviceや@ExceptionHandler）を導入し、APIを安定化させる。

⸻

📘 学習範囲
	•	書籍：『Spring Boot 3 プログラミング入門』
	•	第3〜5章（データアクセス、バリデーション、エラーハンドリング）
	•	補足：Spring公式ドキュメント
	•	Spring Validation 入門￼
	•	Spring Boot Exception Handling￼

⸻

🧠 課題内容（提出必須）

課題①：タスク管理APIをDB対応に改良する

仕様：
前回（Week4）の「タスク管理API」を拡張し、H2 Database（またはPostgreSQL）に永続化するようにします。

要件：
	1.	Entity定義

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 50, message = "タイトルは50文字以内で入力してください")
    private String title;

    private boolean completed = false;
}


	2.	Repository層の導入

public interface TaskRepository extends JpaRepository<Task, Long> {}


	3.	Service層でRepositoryを利用
	•	CRUDをTaskServiceで実装
	•	不正IDアクセス時は例外をスロー（例：TaskNotFoundException）
	4.	Controllerの更新
	•	GET /api/tasks（一覧）
	•	POST /api/tasks（登録）
	•	PUT /api/tasks/{id}（更新）
	•	DELETE /api/tasks/{id}（削除）
	5.	例外ハンドリング
	•	TaskNotFoundException → 404エラー(JSONで{"error":"Task not found"}を返す)
	•	MethodArgumentNotValidException → 400エラー（入力エラー内容を返す）
	6.	DB設定
	•	application.yml に以下を追加

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


※PostgreSQLを使いたい場合は接続設定を自分で調べて変更してもOK。

⸻

課題②（発展）

以下のうち、1つ以上に挑戦してみてください。
	•	completed フラグを更新するAPI：PUT /api/tasks/{id}/complete
	•	検索API：GET /api/tasks?keyword=買い物
	•	完了・未完了をフィルタリングするクエリメソッド追加
	•	Flyway でDBマイグレーションを自動化
	•	Docker Compose でPostgreSQLを起動して接続

⸻

📦 提出物

GitHubにて「JavaTraining-Week4」リポジトリのmainブランチから「JavaTraning-Week5」というブランチを作成し、以下の構成で提出してください(プルリクエスト)。

JavaTraining-Week5/
├─ src/main/java/com/example/taskapp/
│   ├─ controller/
│   ├─ service/
│   ├─ repository/
│   ├─ entity/
│   └─ exception/
├─ src/main/resources/
│   └─ application.yml
├─ README.md
└─ screenshots/
    ├─ db_console.png       ← H2コンソールやpsqlの表示
    └─ curl_results.png     ← API動作結果

README.md 記載内容
	•	プロジェクト概要（1〜2行）
	•	環境構築手順（DB設定を含む）
	•	実行・確認手順（curl例など）
	•	例外ハンドリングの動作例（400/404の例）
	•	（発展課題を行った場合）その内容と結果

⸻

✨　提出方法
提出方法の詳細は、以下課題提出方法をご参照ください。

リポジトリ：
[JavaTraining-Week4](https://github.com/Kohei-mana/JavaTraining-Week4.git)

課題提出方法：
[課題提出方法]()

⸻

✅ チェックリスト

項目	チェック
GET/POST/PUT/DELETE のCRUDが正常動作する	☐
H2またはPostgreSQLにデータが永続化される	☐
Validationエラー時に400エラーを返す	☐
存在しないIDアクセス時に404エラーを返す	☐
READMEに再現手順と結果が明記されている	☐


⸻

🕓 提出期限
	•	第5週の最終日（日曜 23:59まで）

⸻

💡 アドバイス
	•	データが永続化されることを確認するため、アプリ再起動後もDBにデータが残るかを確認しましょう（H2ならmem→fileに切り替える）。
	•	バリデーションエラーや例外発生時のレスポンスJSONを整えることで、後のフロント連携に備えられます。
	•	FlywayやDocker Composeは挑戦的ですが、できれば1名でもトライしてみると実務感がつきます。

