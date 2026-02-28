# JavaTraining Week7

## 概要
Week6で作成したタスク管理アプリ（Thymeleaf + CRUD）に対して、Week7では Spring Security を導入し、
ログイン必須の画面（/tasks/**）を構築しました。あわせて MockMvc を用いた認証関連テストを追加した。

---

## 動作環境
- OS: Windows 11 (PowerShell)
- Java: 21.0.9 LTS
- Spring Boot: 3.5.10-SNAPSHOT
- Build Tool: Gradle

---

## 初期ユーザ（ログイン用）
アプリ起動時にDBへ初期ユーザを投入。

- Username: `testuser`
- Password: `password`
- Role: `USER`

---

## 認証要件（アクセス制御）
- `GET /login` : 認証不要（ログイン画面）
- `/css/**` : 認証不要（静的リソース）
- `/tasks/**` : 認証必須（未ログインの場合は `/login` にリダイレクト）
- `/logout` : ログアウト（POST）

---

## 起動手順（再現手順）
### 1. リポジトリ取得
```powershell
git clone <YOUR_REPOSITORY_URL>
cd JavaTraining-Week4
```
### 2. アプリ起動
```powershell
.\gradlew.bat bootRun
```
### 3. ブラウザでアクセス
-　http://localhost:8080/tasks
　 → 未ログインなら /login にリダイレクトされることを確認
### 4. ログイン
ログイン画面で以下を入力してログイン。
- Username: testuser
- Password: password
ログイン後に /tasks が表示されればOK。
### 5. ログアウト
画面の Logout ボタンからログアウト（/login?logout に遷移）。

## テスト実行方法
以下でテストを実行。
```powershell
.\gradlew.bat test
```
テストレポートは(build/reports/tests/test/index.html)に出力される。

## 実装内容（Week7）

### 追加/変更した主なファイル

- `config/SecurityConfig.java`
  - `/login` と静的リソースは `permitAll`
  - `/tasks/**` は `authenticated`
  - `formLogin` / `logout` を有効化

- `controller/LoginController.java`
  - `GET /login` でログイン画面を返す

- `templates/login.html`
  - ログインフォーム（CSRF token 含む）
  - `?error` / `?logout` の表示

- `entity/UserAccount.java` / `repository/UserAccountRepository.java`
  - DBユーザ管理

- `service/UserAccountService.java`
  - `UserDetailsService` 実装（DBからユーザ取得）

- `config/DataInitializerConfig.java`
  - 初期ユーザ `testuser/password` を起動時に投入

- `test/SecurityMockMvcTest.java`
  - 未ログイン時のリダイレクト
  - `@WithMockUser` の画面アクセス
  - `formLogin` の成功/失敗
  - `logout` + `csrf` のテスト