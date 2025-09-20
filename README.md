# Action Log App

行動記録アプリ。日々の行動（学習・運動など）を記録し、日/週/月単位で集計・可視化できる。
転職活動用のポートフォリオとして開発しました。

## 技術スタック

- Java 21, Spring Boot 3
- MyBatis, MySQL 8
- HTML/CSS/JavaScript
- Docker, Docker Compose
- AWS (EC2, RDS, Nginx, Let’s Encrypt)
- Linux (Ubuntu)
- Git, GitHub Actions (CI/CD)
- テスト: JUnit5, Spring Boot Test, MockMvc
- ワイヤーフレーム: Figma

## 機能一覧

- ユーザー登録 / ログイン (JWT 認証)
- 行動記録 CRUD（登録・一覧・編集・削除）
- 検索 / フィルタ（期間・カテゴリ・タグ）
- 集計（日/週/月、カテゴリ別）
- CSV エクスポート (UTF-8 BOM)
- ダッシュボード（合計時間・グラフ表示）
- 共有リンク（閲覧専用、任意実装）

## セットアップ（ローカル）

```bash
# 1. リポジトリを取得
git clone https://github.com/yourname/action-log-app.git
cd action-log-app

# 2. DockerでMySQL起動（初期スキーマとデータがsql/にある場合は自動で投入されます）
docker compose up -d

# 3. Spring Bootアプリを起動
./mvnw spring-boot:run
```

ブラウザで [http://localhost:8080](http://localhost:8080) にアクセスできます。

## 公開 URL

👉 https://yourapp.example.com
（AWS EC2 + RDS + Nginx + Let’s Encrypt でデプロイ）

## アーキテクチャ構成図

```
[Client] → [Nginx(EC2)] → [Spring Boot App] → [RDS(MySQL)]
```

## 設計資料

- [要件定義](docs/requirements.md)
- [設計書](docs/design.md)
- [ER 図](docs/er.png)
- [API 仕様](docs/api-spec.md)
- [運用 Runbook](docs/runbook.md)

## スクリーンショット

![Dashboard](docs/images/dashboard.png)

## テスト

- 単体テスト：JUnit5 + Spring Boot Test
- 連結テスト：MockMvc, Testcontainers (MySQL)
- CI/CD：GitHub Actions（push 時に自動ビルド & テスト & デプロイ）

## 今後の改善点

- UI/UX 改善（モバイル最適化）
- OAuth2 (Google 認証) 対応
- Docker + ECS/Fargate による本番運用
- 国際化 (i18n, 英語対応)

## ライセンス

This project is licensed under the MIT License.
