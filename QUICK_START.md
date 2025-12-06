# Quick Start Guide - Online Database Setup

## 🚀 Quick Steps to Deploy

### 1️⃣ Get a Free Online MySQL Database (5 minutes)

**Recommended: Railway** (Easiest option)

```
1. Visit: https://railway.app/
2. Sign up with GitHub
3. Click "New Project" → "Provision MySQL"
4. Copy connection details from "Variables" tab
```

### 2️⃣ Update Your Configuration

Open: `src/main/resources/application-prod.properties`

Replace these lines with YOUR database details:

```properties
spring.datasource.url=jdbc:mysql://YOUR_HOST:YOUR_PORT/railway
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 3️⃣ Test Locally with Online Database

**Method 1: Change profile in application.properties** (Recommended)

```properties
# In application.properties, change:
spring.profiles.active=prod
```

Then run:

```powershell
.\mvnw.cmd spring-boot:run
```

**Method 2: Use command line** (PowerShell)

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=prod"
```

If it works, you'll see: "Started StadiumTicketMngmntSysApplication"

### 4️⃣ Deploy Your Application

**Option A: Railway (Easiest)**

```
1. Push code to GitHub
2. In Railway: "New Project" → "Deploy from GitHub"
3. Select your repo
4. Add env variable: SPRING_PROFILES_ACTIVE=prod
5. Done! Railway gives you a URL
```

**Option B: Render.com**

```
1. Push code to GitHub
2. Visit: https://render.com/
3. "New Web Service" → Connect your repo
4. Build: ./mvnw clean install -DskipTests
5. Start: java -jar target/stadium-ticket_mngmnt-sys-0.0.1-SNAPSHOT.jar
6. Add environment variable: SPRING_PROFILES_ACTIVE=prod
```

## 📝 Configuration Files Created

- ✅ `application.properties` - Main config (active profile)
- ✅ `application-dev.properties` - Local MySQL (your PC)
- ✅ `application-prod.properties` - Online MySQL (for deployment)
- ✅ `DEPLOYMENT_GUIDE.md` - Detailed deployment instructions
- ✅ `.env.example` - Template for environment variables

## 🔄 Switching Between Local and Online Database

### Use Local Database (Development):

In `application.properties`, set:

```properties
spring.profiles.active=dev
```

### Use Online Database (Production):

In `application.properties`, set:

```properties
spring.profiles.active=prod
```

## 🔐 Security Tips

1. **Never commit** `application-prod.properties` with real passwords to GitHub
2. Use environment variables on hosting platforms instead
3. The `.gitignore` file is already configured to protect your credentials

## 📊 Migrate Your Existing Data

If you have data in local MySQL and want to move it:

```bash
# 1. Export from local database
mysqldump -u root -pabc?@, stadium_management > backup.sql

# 2. Import to online database
mysql -h YOUR_ONLINE_HOST -u YOUR_USERNAME -pYOUR_PASSWORD YOUR_DATABASE < backup.sql
```

## ✅ Testing Checklist

Before deploying:

- [ ] Online database is created and accessible
- [ ] Updated `application-prod.properties` with correct credentials
- [ ] Changed `application.properties` to use `prod` profile
- [ ] Tested locally with: `.\mvnw.cmd spring-boot:run`
- [ ] Application connects and starts successfully
- [ ] Can perform database operations (create, read, update, delete)

After deploying:

- [ ] Website is accessible via URL
- [ ] Can register/login users
- [ ] Database operations work online
- [ ] No connection errors in logs

## 🆘 Common Issues

### "Access denied for user"

- Check username and password in application-prod.properties
- Verify credentials on your database provider dashboard

### "Unknown database"

- Create the database on your online provider
- Or change database name in connection URL

### "Connection timeout"

- Check if database allows remote connections
- Verify firewall rules on database provider
- Add your IP to whitelist if required

### "SSL connection error"

- Add `?useSSL=false` to connection URL (only for testing)
- Or configure SSL properly with certificates

## 📞 Need Help?

1. Check `DEPLOYMENT_GUIDE.md` for detailed instructions
2. View application logs for specific errors
3. Test database connection with MySQL Workbench first
4. Verify all credentials are correct

## 🎉 You're Ready!

Your application is now configured for both:

- ✅ Local development (using your PC's MySQL)
- ✅ Production deployment (using online MySQL)

Just update the credentials in `application-prod.properties` and deploy! 🚀
