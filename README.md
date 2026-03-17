# JavaTraining-Final

## 概要
ログイン後にタスクのCRUD（一覧/登録/編集/削除/完了切替）を行えるミニWebアプリ。  
画面（Thymeleaf）とREST API（/api/tasks）の両方で操作可能。

---

## 開発環境
- OS: Windows 11（PowerShell）
- Java: 21.0.9 LTS
- Spring Boot: 3.5.10-SNAPSHOT
- Build Tool: Gradle
- DB: H2（file）

---

## セットアップ手順

### 1) リポジトリ取得
    git clone <YOUR_REPOSITORY_URL>
    cd JavaTraining-Week4

### 2) アプリ起動
    .\gradlew.bat bootRun

---

## 起動後にアクセスするURL
- ログイン画面: http://localhost:8080/login
- タスク画面: http://localhost:8080/tasks
- REST API: http://localhost:8080/api/tasks
- H2 Console: http://localhost:8080/h2-console

---

## ログイン情報（初期ユーザ）
アプリ起動時にDBへ初期ユーザを投入。

- Username: `testuser`
- Password: `password`
- Role: `USER`

---

## 動作確認手順（画面）
1. http://localhost:8080/login にアクセス
2. `testuser / password` でログイン
3. `/tasks` で以下を確認
   - 一覧表示（/tasks）
   - 新規登録（/tasks/new）
   - 編集（/tasks/{id}/edit）
   - 削除
   - 完了切替
4. Logoutできることを確認（/login?logout）

---

## 動作確認手順（REST API）

### API仕様（一覧）
| Method | Path | 説明 | 主なレスポンス |
|---|---|---|---|
| GET | /api/tasks | タスク一覧取得 | 200 |
| POST | /api/tasks | タスク作成（title必須） | 200（または201） |
| PUT | /api/tasks/{id} | タスク更新（title/completed） | 200 |
| DELETE | /api/tasks/{id} | タスク削除 | 200（または204） |

### バリデーション / 例外
- title: `@NotBlank`, `@Size(max=50)`
- 不正入力：400（JSON）
- 存在しないID：404（JSON or 404ページ）

### curl例（例：一覧取得）

    curl.exe http://localhost:8080/api/tasks

---

## 認証要件（アクセス制御）
- `GET /login` : 認証不要（ログイン画面）
- `/css/**` : 認証不要（静的リソース）
- `/tasks/**` : 認証必須（未ログインの場合は `/login` にリダイレクト）
- `/logout` : ログアウト（POST）
- ログイン成功時は `/tasks` に遷移

---

## DB（永続化）
- H2 Console: http://localhost:8080/h2-console  
- DataSource URL（application.yml）例: `jdbc:h2:file:./data/tasks...`

---

## テスト実行方法（JUnit + MockMvc）
    .\gradlew.bat test

テストレポート：
- `build/reports/tests/test/index.html`

---

## アーキテクチャ（テキスト図）

    [Browser]
      |  /login, /tasks/**
      v
    [Controller]
      - TaskViewController (Thymeleaf)
      - TaskController (REST: /api/tasks)
      |
      v
    [Service] TaskService
      |
      v
    [Repository] TaskRepository (JPA)
      |
      v
    [DB] H2 (file)

    認証: Spring Security（フォームログイン）
    例外: ControllerAdvice（JSON/HTML）

---

## パッケージ構成
- `com.example.taskapp.controller` : 画面/RESTのController
- `com.example.taskapp.service` : ビジネスロジック
- `com.example.taskapp.repository` : DBアクセス（JpaRepository）
- `com.example.taskapp.entity` : Entity
- `com.example.taskapp.config` : Security / 初期ユーザ投入
- `com.example.taskapp.exception` : 例外 / ハンドリング

---

## 実装内容（Week8で統合した要素）
- CRUD（画面 / REST）
- Validation（title: NotBlank, Size max=50）
- 例外処理（404/400）
- 認証（/loginフォームログイン、/tasks/** 認証必須）
- DB永続化（H2 file）
- テスト（MockMvc 3本以上）

---

## 既知の制約・今後の改善点（3点以上）
- APIをcurl単体で完全に扱うためのセッション/CSRF手順をREADMEに整理したい
- エラーレスポンスの形式を共通DTO（例: ErrorResponse）に統一したい
- UIに検索/ソート/ページングのいずれかを追加したい
