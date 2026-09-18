# 错题集 · 安卓 APK 壳（WebView）

把线上错题集（https://aca60fe289408b722v2.app.workbuddy.host）包成一个安卓 App。
App 内就是原网页，**数据保存在本机（WebView localStorage）并保留 GitHub Gist 云同步 —— 与电脑/手机浏览器访问完全一致，维持现状**。

## 目录结构
```
apk-webview/
├── build.gradle / settings.gradle / gradle.properties   # Gradle 工程
├── app/
│   ├── build.gradle
│   ├── src/main/AndroidManifest.xml
│   ├── src/main/java/com/cuotiji/app/MainActivity.java   # WebView 加载线上地址
│   └── src/main/res/...                                  # 图标、名称
└── .github/workflows/build-apk.yml                       # 一键打 APK
```

## 方式一：GitHub Actions 一键出包（推荐，无需本机装 SDK）

目标仓库：`https://github.com/ttmmmushroom/cuotiji`

> ⚠️ 安全：下面命令里的 `<你的PAT>` 用你的 Personal Access Token 占位，token 等同账号密码，**不要写进任何会被提交的文件**，用一次就到 GitHub → Settings → Developer settings → PAT 里 Revoke 掉。

### A. 命令行（最省事）
```bash
# 1) 下载本仓库 ZIP 并解压后，进入 apk-webview 目录
cd apk-webview

# 2) 初始化并提交
git init
git add .
git commit -m "init: 错题集 WebView APK"

# 3) 关联你的仓库并推送（用户名随意，密码/令牌处粘贴你的 PAT）
git branch -M main
git remote add origin https://ttmmmushroom:你的PAT@github.com/ttmmmushroom/cuotiji.git
git push -u origin main
```
推送成功即自动触发构建；若没自动跑，去仓库 **Actions → Build APK → Run workflow**。

### B. 网页上传（不会用 git 也行）
1. 下载本仓库 ZIP，解压到本地任意文件夹。
2. 打开 `https://github.com/ttmmmushroom/cuotiji`。
3. 点 **Add file → Upload files**，把解压出来的**全部文件和文件夹**拖进去
   （注意：必须包含隐藏的 `.github` 文件夹——Windows 资源管理器勾「显示→隐藏的项目」，macOS 用 `Shift+Cmd+.` 让隐藏文件可见后再拖）。
4. 拉到底点 **Commit changes**。
5. 首次会提示开启 Actions，点 **I understand… enable them**，构建随即开始。

### 取包 & 安装
1. 构建约 2–5 分钟，绿色 ✓ 即成功。
2. 点进该条 workflow run → 底部 **Artifacts → cuotiji-debug-apk** 下载（是个 zip）。
3. 解压得到 `app-debug.apk`，传到手机安装即可（需允许「安装未知来源应用」）。


> debug 签名已可被安卓直接安装；若要上架应用商店，再配 release 签名即可。

## 方式二：本机用 Android Studio 构建
1. 安装 Android Studio 与 SDK（Platform 34 + Build-Tools 34.0.0）。
2. 用 Android Studio 打开 `apk-webview` 目录（Import Project）。
3. 菜单 Build → Build Bundle(s) / APK(s) → Build APK(s)。
4. 在 `app/build/outputs/apk/debug/` 拿到 `app-debug.apk`。

## 想换成自己的部署地址？
改 `app/src/main/java/com/cuotiji/app/MainActivity.java` 里的 `APP_URL` 即可。
