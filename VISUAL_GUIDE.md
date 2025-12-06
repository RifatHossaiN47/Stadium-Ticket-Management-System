# Configuration Structure - Visual Guide

## 📁 Project Configuration Files

```
src/main/resources/
├── application.properties              ← Main config (sets active profile)
├── application-dev.properties          ← Local MySQL config
├── application-prod.properties         ← Online MySQL config ⚠️ UPDATE THIS
└── application-prod-env.properties     ← Alternative (uses env variables)
```

---

## 🔄 How Profile System Works

```
┌─────────────────────────────────────────────────────┐
│         application.properties (Main)                │
│  spring.profiles.active=dev OR prod                  │
└──────────────────┬──────────────────────────────────┘
                   │
          ┌────────┴────────┐
          │                 │
      ┌───▼────┐       ┌───▼────┐
      │  dev   │       │  prod  │
      │ Local  │       │ Online │
      └───┬────┘       └───┬────┘
          │                │
          │                │
   ┌──────▼──────┐   ┌────▼───────┐
   │  localhost  │   │  Railway/  │
   │   MySQL     │   │   Cloud    │
   │   :3306     │   │  Database  │
   └─────────────┘   └────────────┘
```

---

## 🚀 Deployment Flow

### Development (Your PC)

```
Your PC                      Local MySQL
  │                            │
  │  1. Set profile=dev        │
  │                            │
  │  2. Run application        │
  │────────────────────────────▶
  │                            │
  │  3. App connects           │
  │◀────────────────────────────
  │                            │
  └─ localhost:8082 (Running)
```

### Production (Online)

```
Hosting Platform          Online MySQL         Internet Users
      │                      │                       │
      │  1. Deploy app       │                       │
      │  profile=prod        │                       │
      │                      │                       │
      │  2. Connect          │                       │
      │──────────────────────▶                       │
      │                      │                       │
      │  3. App live         │                       │
      │                      │                       │
      │◀─────────────────────────────────────────────┤
      │                      │     4. Users access   │
      │  your-app.com:8082   │        website        │
      │                      │                       │
```

---

## ⚙️ Configuration Details

### application.properties (Main)

```properties
┌──────────────────────────────────────┐
│ server.port=8082                     │
│                                      │
│ # Choose profile:                    │
│ spring.profiles.active=dev ← Local   │
│ # OR                                 │
│ spring.profiles.active=prod ← Online │
│                                      │
│ # Common JPA settings                │
│ spring.jpa.show-sql=true             │
│ spring.jpa.hibernate.ddl-auto=update │
└──────────────────────────────────────┘
```

### application-dev.properties (Local)

```properties
┌─────────────────────────────────────────┐
│ Local MySQL on Your PC                  │
│                                         │
│ spring.datasource.url=                  │
│   jdbc:mysql://localhost:3306/          │
│   stadium_management                    │
│                                         │
│ spring.datasource.username=root         │
│ spring.datasource.password=abc?@,       │
└─────────────────────────────────────────┘
```

### application-prod.properties (Online)

```properties
┌─────────────────────────────────────────┐
│ Online MySQL (Cloud Database)           │
│                                         │
│ spring.datasource.url=                  │
│   jdbc:mysql://YOUR_HOST:PORT/          │
│   YOUR_DATABASE?useSSL=true...          │
│                                         │
│ spring.datasource.username=YOUR_USER    │
│ spring.datasource.password=YOUR_PASS    │
│                                         │
│ ⚠️ UPDATE THESE WITH REAL CREDENTIALS!   │
└─────────────────────────────────────────┘
```

---

## 📝 Step-by-Step Visual Workflow

### Step 1: Get Online Database

```
┌──────────────────────┐
│   1. Go to           │
│   railway.app        │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│   2. Sign up with    │
│   GitHub account     │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│   3. Create new      │
│   MySQL project      │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│   4. Copy connection │
│   details (host,     │
│   port, password)    │
└──────────────────────┘
```

### Step 2: Update Config

```
┌────────────────────────────────┐
│ Open:                          │
│ application-prod.properties    │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Replace placeholders with:     │
│ - Your database host           │
│ - Your database port           │
│ - Your username                │
│ - Your password                │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Save the file                  │
└────────────────────────────────┘
```

### Step 3: Test Locally

```
┌────────────────────────────────┐
│ Change application.properties: │
│ spring.profiles.active=prod    │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Run: .\mvnw.cmd spring-boot:run│
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ ✅ Success? See:                │
│ "Started Application..."       │
│ "DATABASE CONNECTION SUCCESS"  │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Test on: localhost:8082        │
└────────────────────────────────┘
```

### Step 4: Deploy

```
┌────────────────────────────────┐
│ Push code to GitHub            │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Go to Railway/Render           │
│ "Deploy from GitHub"           │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Select your repository         │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ Set environment variable:      │
│ SPRING_PROFILES_ACTIVE=prod    │
└────────────┬───────────────────┘
             │
             ▼
┌────────────────────────────────┐
│ ✅ Live! Get your website URL   │
└────────────────────────────────┘
```

---

## 🎯 Quick Decision Guide

### "Which profile should I use?"

```
                  Are you testing locally?
                          │
            ┌─────────────┴─────────────┐
            │                           │
          YES                          NO
            │                           │
            ▼                           ▼
    Do you want local             Are you deploying
    or online database?            to the internet?
            │                           │
    ┌───────┴────────┐                 │
    │                │                YES
  Local           Online               │
    │                │                 │
    ▼                ▼                 ▼
  Use dev        Use prod           Use prod
  profile        profile            profile
```

### "How do I switch profiles?"

```
┌───────────────────────────────────┐
│ Open: application.properties      │
│                                   │
│ Change this line:                 │
│ spring.profiles.active=___        │
│                                   │
│ Options:                          │
│ • dev  = Local database           │
│ • prod = Online database          │
└───────────────────────────────────┘
```

---

## 🔐 Security Visualization

### What's Protected?

```
┌────────────────────────────────────┐
│  Files on Your Computer            │
│  ├── application.properties  ✅     │
│  ├── application-dev.properties ✅  │
│  └── application-prod.properties ❌ │ ← NOT committed to Git
└────────────────────────────────────┘
                │
                ▼
┌────────────────────────────────────┐
│  GitHub Repository (Public)        │
│  ├── application.properties  ✅     │
│  ├── application-dev.properties ✅  │
│  └── application-prod.properties ❌ │ ← Blocked by .gitignore
└────────────────────────────────────┘
                │
                ▼
┌────────────────────────────────────┐
│  Hosting Platform (Railway/Render) │
│  └── Uses Environment Variables    │
│      SPRING_PROFILES_ACTIVE=prod   │
└────────────────────────────────────┘
```

---

## 📊 Database Connection Flow

### Development Mode

```
Your Application (dev profile)
        │
        │ Reads: application-dev.properties
        │
        ▼
┌──────────────────────┐
│ jdbc:mysql://        │
│ localhost:3306       │
│ stadium_management   │
│                      │
│ username: root       │
│ password: abc?@,     │
└──────────────────────┘
        │
        ▼
  Local MySQL Server
  (Your PC)
```

### Production Mode

```
Your Application (prod profile)
        │
        │ Reads: application-prod.properties
        │
        ▼
┌──────────────────────────────┐
│ jdbc:mysql://                │
│ containers-us-west.railway   │
│ :6379/railway                │
│                              │
│ username: root               │
│ password: xYz123...          │
└──────────────────────────────┘
        │
        │ Internet Connection
        ▼
  Online MySQL Server
  (Railway/AWS/etc)
```

---

## ✅ Checklist with Visual Status

```
Setup Checklist:
□ 1. Configuration files created
□ 2. Local database tested (dev profile)
□ 3. Online database created
□ 4. Production config updated
□ 5. Online database tested (prod profile)
□ 6. Code pushed to GitHub
□ 7. Deployed to hosting platform
□ 8. Website is live!

Progress Bar:
[████████░░] 80% Complete
(Steps 1-5 done, waiting for steps 6-8)
```

---

## 💡 Common Scenarios

### Scenario 1: Working locally

```
application.properties
├── spring.profiles.active=dev ✅
└── Uses localhost MySQL
```

### Scenario 2: Testing online DB locally

```
application.properties
├── spring.profiles.active=prod ✅
└── Uses online MySQL (but runs on your PC)
```

### Scenario 3: Deployed to internet

```
Hosting Platform
├── SPRING_PROFILES_ACTIVE=prod ✅
└── Uses online MySQL (runs on server)
```

---

This visual guide makes it easy to understand how everything connects!
