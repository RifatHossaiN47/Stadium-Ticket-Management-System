# Online Database Deployment Guide

## Step 1: Choose and Setup Online MySQL Database

### Option A: Railway (Recommended - Easy & Free Tier)

1. Go to https://railway.app/
2. Sign up with GitHub
3. Click "New Project" → "Provision MySQL"
4. Get connection details from "Connect" tab:
   - Host: `containers-us-west-xxx.railway.app`
   - Port: `6379`
   - Database: `railway`
   - Username: `root`
   - Password: (provided by Railway)

### Option B: PlanetScale (Serverless MySQL)

1. Go to https://planetscale.com/
2. Sign up and create a new database
3. Get connection string from dashboard
4. Note: Use `?sslMode=REQUIRED` in connection URL

### Option C: AWS RDS (Production-grade)

1. Go to AWS Console → RDS
2. Create MySQL database instance
3. Configure security groups to allow your IP
4. Get endpoint URL from RDS dashboard

### Option D: Aiven (Free Trial)

1. Go to https://aiven.io/
2. Sign up and create MySQL service
3. Get connection details from service overview

## Step 2: Update Application Configuration

Edit `src/main/resources/application-prod.properties` with your online database credentials:

```properties
spring.datasource.url=jdbc:mysql://YOUR_HOST:YOUR_PORT/YOUR_DATABASE?useSSL=true&serverTimezone=UTC
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Example for Railway:

```properties
spring.datasource.url=jdbc:mysql://containers-us-west-123.railway.app:6379/railway?useSSL=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_railway_password
```

## Step 3: Test Online Database Connection Locally

Run with production profile:

```bash
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=prod
```

Or set in application.properties:

```properties
spring.profiles.active=prod
```

## Step 4: Migrate Your Existing Data

### Option 1: Export from local MySQL and import to online

```bash
# Export from local database
mysqldump -u root -p stadium_management > stadium_management_backup.sql

# Import to online database (replace with your online DB credentials)
mysql -h your-host.com -u your-username -p your-database-name < stadium_management_backup.sql
```

### Option 2: Let Hibernate create tables automatically

- Keep `spring.jpa.hibernate.ddl-auto=update` in production temporarily
- Run the application once to create tables
- Then change to `validate` for safety

## Step 5: Deploy Your Application

### Option A: Deploy to Railway

1. Push your code to GitHub
2. In Railway, click "New Project" → "Deploy from GitHub"
3. Select your repository
4. Railway will auto-detect Spring Boot and deploy
5. Add environment variable: `SPRING_PROFILES_ACTIVE=prod`

### Option B: Deploy to Heroku

```bash
# Install Heroku CLI
heroku login
heroku create your-app-name

# Add MySQL addon or use external database
heroku config:set SPRING_PROFILES_ACTIVE=prod
heroku config:set SPRING_DATASOURCE_URL=jdbc:mysql://...
heroku config:set SPRING_DATASOURCE_USERNAME=...
heroku config:set SPRING_DATASOURCE_PASSWORD=...

git push heroku master
```

### Option C: Deploy to Render

1. Go to https://render.com/
2. Connect GitHub repository
3. Create new "Web Service"
4. Set environment variables for database connection
5. Build command: `./mvnw clean install -DskipTests`
6. Start command: `java -jar target/stadium-ticket_mngmnt-sys-0.0.1-SNAPSHOT.jar`

### Option D: Deploy to AWS Elastic Beanstalk

```bash
# Install EB CLI
eb init -p java-17 stadium-ticket-app
eb create stadium-ticket-env
eb setenv SPRING_PROFILES_ACTIVE=prod
eb deploy
```

## Step 6: Security Best Practices

### Use Environment Variables for Production

Instead of hardcoding credentials in application-prod.properties:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

Then set these as environment variables on your hosting platform.

### Add to .gitignore

Make sure sensitive files are ignored:

```
application-prod.properties
*.properties
!application.properties
!application-dev.properties
```

## Step 7: Build for Production

```bash
# Clean build
.\mvnw.cmd clean package -DskipTests

# The JAR file will be in target/ folder
# Deploy this JAR to your hosting platform
```

## Troubleshooting

### SSL Connection Issues

Add to connection URL:

```
?useSSL=true&requireSSL=true&verifyServerCertificate=false
```

### Connection Timeout

Add to application-prod.properties:

```properties
spring.datasource.hikari.connection-timeout=60000
```

### Firewall Issues

- Whitelist your deployment server IP in online database settings
- Check security groups (AWS) or firewall rules

### Table Not Found

- Set `spring.jpa.hibernate.ddl-auto=update` temporarily
- Or manually run SQL migration scripts

## Testing Checklist

- [ ] Can connect to online database from local machine
- [ ] All tables are created in online database
- [ ] Data is migrated (if needed)
- [ ] Application runs with prod profile locally
- [ ] Environment variables are set on hosting platform
- [ ] Application deployed successfully
- [ ] Can access website and perform operations
- [ ] Database operations work correctly

## Need Help?

- Check application logs for connection errors
- Verify database credentials
- Test connection with MySQL Workbench or command line first
- Ensure database allows remote connections
