# JavaTraining Week6 - TaskApp（Thymeleaf）

## 目的
Spring MVC（Controller / Service / Repository）と Thymeleaf を用いて、タスク管理アプリにWeb画面（CRUD）を追加する。
フォーム入力のバリデーション表示、PRGパターン（Post → Redirect → Get）、フラッシュメッセージ、共通レイアウト化を体験する。

## 機能概要
- タスク一覧表示（/tasks）
- タスク新規作成（/tasks/new → POST /tasks）
- タスク編集（/tasks/{id}/edit → POST /tasks/{id}）
- タスク削除（POST /tasks/{id}/delete）
- （任意）完了/未完了の切り替え（POST /tasks/{id}/toggle）

## 起動手順
### 前提
- Java 21（JDK 21）
- Windows 11 / PowerShell

### 起動
```powershell
cd C:\Users\10xja\Desktop\JavaTraining-Week4
```

## 起動後のアクセス
起動後、ブラウザで http://localhost:8080/tasks にアクセスする。

## 画面URL一覧
- 一覧：GET /tasks
- 新規作成フォーム：GET /tasks/new
- 新規作成：POST /tasks
- 編集フォーム：GET /tasks/{id}/edit
- 更新：POST /tasks/{id}
- 削除：POST /tasks/{id}/delete
- （任意）完了切替：POST /tasks/{id}/toggle

## バリデーション（画面側）
フォームDTO（TaskForm）で入力を受け取り、@Validated + BindingResult により入力チェックを行う。  
エラー時は同一画面に戻し、th:errors でフィールド直下にメッセージを表示する。

## 例外ハンドリング（画面側）
存在しないIDアクセス時は TaskNotFoundException を @ControllerAdvice で捕捉し、templates/error/404.html を表示する。

## スクリーンショット
screenshots/ に以下を格納：
- list_view.png（一覧画面）
- form_error.png（バリデーションエラー表示）
- after_create_flash.png（登録後フラッシュメッセージ表示）


