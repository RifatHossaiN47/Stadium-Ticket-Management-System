# 🏟️ Stadium Ticket Management System

> A full-stack web application for managing stadium events, tickets, vendors, and organizers with secure payment processing and real-time inventory management.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 🌐 Live Demo

**🚀 Deployed Application:** [https://stadium-ticket-management-system-production.up.railway.app/](https://stadium-ticket-management-system-production.up.railway.app/)

> The application is live and running on Railway with online MySQL database.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Local Development Setup](#-local-development-setup)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [Deployment to Railway](#-deployment-to-railway)
- [API Endpoints](#-api-endpoints)
- [Database Schema](#-database-schema)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 👤 Customer Features

- User registration and authentication
- Browse available programs and events
- Real-time ticket availability checking
- Shopping cart management
- Secure payment processing
- Order history and ticket downloads
- Submit reviews and complaints

### 🎪 Event Organizer Features

- Organizer account management
- Create and manage programs/events
- Set ticket prices and availability
- View sales analytics
- Manage event schedules
- Handle customer complaints

### 🛒 Vendor Features

- Vendor registration and profile
- Gallery/booth booking system
- Inventory management
- Payment tracking
- Booking history

### 🏢 Stadium Management

- Multi-stadium support
- Gallery/section management
- Capacity management
- Facility booking

## 🛠️ Tech Stack

### Backend

- **Framework:** Spring Boot 3.1.5
- **Language:** Java 21 (compatible with Java 17)
- **ORM:** Hibernate 6.2.13 (Spring Data JPA)
- **Security:** Spring Security
- **Database:** MySQL 8.0+
- **Connection Pool:** HikariCP
- **Build Tool:** Maven 3.9+

### Frontend

- **Template Engine:** Thymeleaf
- **CSS:** Custom styles (pp.css, st.css, userstyle.css)
- **JavaScript:** Vanilla JS
- **UI:** Responsive design

### DevOps & Deployment

- **Version Control:** Git & GitHub
- **CI/CD:** Railway (automated deployments)
- **Database Hosting:** Railway MySQL
- **Container:** Spring Boot embedded Tomcat

## 📁 Project Structure

```
stadium-ticket_mngmnt-sys/
├── src/
│   ├── main/
│   │   ├── java/com/emojin/main/
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   └── MainController.java
│   │   │   ├── model/               # Entity classes
│   │   │   │   ├── Cart.java
│   │   │   │   ├── Customer.java
│   │   │   │   ├── EventOrganizer.java
│   │   │   │   ├── Gallery.java
│   │   │   │   ├── Payment.java
│   │   │   │   ├── Program.java
│   │   │   │   ├── ReviewsAndComplaints.java
│   │   │   │   ├── Stadium.java
│   │   │   │   ├── Ticket.java
│   │   │   │   └── Vendor.java
│   │   │   ├── repository/          # JPA Repositories
│   │   │   │   ├── CartRepository.java
│   │   │   │   ├── CustomerRepository.java
│   │   │   │   ├── EventOrganizerRepository.java
│   │   │   │   ├── GalleryRepository.java
│   │   │   │   ├── PaymentRepository.java
│   │   │   │   ├── ProgramRepository.java
│   │   │   │   ├── ReviewsAndComplaintsRepository.java
│   │   │   │   ├── StadiumRepository.java
│   │   │   │   ├── TicketRepository.java
│   │   │   │   └── VendorRepository.java
│   │   │   ├── service/             # Business logic
│   │   │   │   ├── CustomerService.java
│   │   │   │   ├── EventOrganizerService.java
│   │   │   │   ├── StadiumService.java
│   │   │   │   └── VendorService.java
│   │   │   ├── util/                # Utility classes
│   │   │   │   └── DatabaseConnectionTest.java
│   │   │   └── StadiumTicketMngmntSysApplication.java
│   │   └── resources/
│   │       ├── application.properties          # Main config
│   │       ├── application-dev.properties      # Local dev config
│   │       ├── application-prod.properties     # Production config
│   │       ├── data.sql                        # Initial data
│   │       ├── static/                         # CSS, JS, Images
│   │       │   ├── css/
│   │       │   └── images/
│   │       └── templates/                      # Thymeleaf templates
│   │           ├── index.html
│   │           ├── customerLogin.html
│   │           ├── CustomerRegister.html
│   │           ├── EventOrganizer_Login.html
│   │           ├── EventOrg_Register.html
│   │           ├── Vendor_Login.html
│   │           ├── Vendor_Register.html
│   │           ├── main_dashboard.html
│   │           ├── main_dashboard_org.html
│   │           ├── main_dashboard_vendor.html
│   │           ├── cart.html
│   │           ├── Ticketpurchase.html
│   │           ├── OrganizerPrograms.html
│   │           ├── vendorbookgallery.html
│   │           ├── setting.html
│   │           ├── setting_org.html
│   │           ├── setting_vendor.html
│   │           ├── updateprofile.html
│   │           ├── update_details_eventOrg.html
│   │           ├── update_details_vendor.html
│   │           ├── done.html
│   │           └── VendorDone.html
│   └── test/                        # Unit tests
├── target/                          # Build output
├── .mvn/                            # Maven wrapper
├── mvnw, mvnw.cmd                   # Maven wrapper scripts
├── pom.xml                          # Maven configuration
├── .gitignore                       # Git ignore rules
└── README.md                        # This file
```

## 📦 Prerequisites

### For Local Development:

- **Java Development Kit (JDK):** 17 or 21
  ```powershell
  java -version  # Should show 17 or 21
  ```
- **Maven:** 3.6+ (or use included Maven wrapper)
  ```powershell
  mvn -version
  ```
- **MySQL:** 8.0+ (local installation) OR Railway MySQL account
- **Git:** For version control
- **IDE:** IntelliJ IDEA, Eclipse, or VS Code (recommended)

### For Deployment:

- **GitHub Account:** For code repository
- **Railway Account:** For hosting (free tier available)

## 🚀 Local Development Setup

### Step 1: Clone the Repository

```powershell
git clone https://github.com/RifatHossaiN47/Stadium-Ticket-Management-System.git
cd stadium-ticket_mngmnt-sys
```

### Step 2: Configure Local Database

**Option A: Use Local MySQL**

1. Install MySQL 8.0+ on your computer
2. Create a database:
   ```sql
   CREATE DATABASE stadium_management;
   ```
3. Update `src/main/resources/application-dev.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/stadium_management
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   ```

**Option B: Use Railway MySQL (Recommended)**

1. Go to [Railway](https://railway.app/)
2. Sign up with GitHub
3. Create new project → "Provision MySQL"
4. Copy connection details from Variables tab
5. Update `src/main/resources/application-prod.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://YOUR_HOST:YOUR_PORT/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   ```

### Step 3: Set Active Profile

In `src/main/resources/application.properties`, set:

```properties
# For local MySQL:
spring.profiles.active=dev

# For Railway MySQL:
spring.profiles.active=prod
```

### Step 4: Build the Project

```powershell
# Using Maven wrapper (recommended)
.\mvnw.cmd clean install

# Or using installed Maven
mvn clean install
```

### Step 5: Run the Application

```powershell
# Using Maven wrapper
.\mvnw.cmd spring-boot:run

# Or using installed Maven
mvn spring-boot:run
```

The application will start on: **http://localhost:8082**

## ⚙️ Configuration

### Environment Profiles

The application uses Spring profiles for different environments:

| Profile | Purpose               | Config File                   | Database      |
| ------- | --------------------- | ----------------------------- | ------------- |
| `dev`   | Local development     | `application-dev.properties`  | Local MySQL   |
| `prod`  | Production deployment | `application-prod.properties` | Railway MySQL |

### Key Configuration Properties

**application.properties** (Main config):

```properties
server.port=8082
spring.profiles.active=prod
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

**application-dev.properties** (Local):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/stadium_management
spring.datasource.username=root
spring.datasource.password=YOUR_LOCAL_PASSWORD
```

**application-prod.properties** (Railway - NOT committed to Git):

```properties
spring.datasource.url=jdbc:mysql://mainline.proxy.rlwy.net:35421/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_RAILWAY_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 🏃 Running the Application

### Development Mode (with auto-reload)

```powershell
.\mvnw.cmd spring-boot:run
```

### Production Build

```powershell
# Build JAR file
.\mvnw.cmd clean package -DskipTests

# Run JAR
java -jar target/stadium-ticket_mngmnt-sys-0.0.1-SNAPSHOT.jar
```

### Access Points

- **Home Page:** http://localhost:8082/
- **Customer Login:** http://localhost:8082/customer/login
- **Organizer Login:** http://localhost:8082/organizer/login
- **Vendor Login:** http://localhost:8082/vendor/login

## 🚂 Deployment to Railway

### Prerequisites for Railway Deployment

1. ✅ GitHub account
2. ✅ Railway account (sign up at [railway.app](https://railway.app/))
3. ✅ Your code pushed to GitHub
4. ✅ Railway MySQL database created

### Step-by-Step Railway Deployment

#### Phase 1: Prepare Your Code

**1. Ensure .gitignore is properly configured**

Verify your `.gitignore` includes:

```
**/application-prod.properties
*.env
.env.*
*.log
*.sql
```

**2. Remove sensitive files from Git history (if already committed)**

```powershell
# Remove application-prod.properties from Git tracking
git rm --cached src/main/resources/application-prod.properties

# Commit the change
git add .gitignore
git commit -m "Remove sensitive configuration files"
```

**3. Set the active profile to 'prod'**

In `src/main/resources/application.properties`:

```properties
spring.profiles.active=prod
```

**4. Push to GitHub**

```powershell
# Add all changes
git add .

# Commit
git commit -m "Prepare for Railway deployment"

# Push to main branch
git push origin main
```

#### Phase 2: Set Up Railway Database

**1. Create Railway MySQL Database**

- Go to [Railway Dashboard](https://railway.app/dashboard)
- Click **"New Project"**
- Select **"Provision MySQL"**
- Wait for deployment (status should show "ACTIVE")

**2. Get Database Connection Details**

- Click on your MySQL service
- Go to **"Variables"** tab
- Note down these values:
  - `MYSQLHOST` (e.g., mainline.proxy.rlwy.net)
  - `MYSQLPORT` (e.g., 35421)
  - `MYSQLDATABASE` (e.g., railway)
  - `MYSQLUSER` (usually: root)
  - `MYSQLPASSWORD` (your generated password)

**3. Verify Database Connection (Optional)**

Test locally first:

```powershell
# Update application-prod.properties with Railway credentials
# Then run:
.\mvnw.cmd spring-boot:run
```

Look for: `✅ DATABASE CONNECTION SUCCESSFUL!`

#### Phase 3: Deploy Application to Railway

**1. Create New Web Service**

- In Railway Dashboard, click **"New"** → **"GitHub Repo"**
- Authorize Railway to access your GitHub
- Select: `RifatHossaiN47/Stadium-Ticket-Management-System`
- Railway will automatically detect it's a Spring Boot app

**2. Configure Build Settings**

Railway should auto-detect, but verify:

- **Build Command:** `./mvnw clean install -DskipTests`
- **Start Command:** `java -jar target/stadium-ticket_mngmnt-sys-0.0.1-SNAPSHOT.jar`

**3. Add Environment Variables**

In your web service, go to **"Variables"** tab and add:

```bash
# Spring Configuration
SPRING_PROFILES_ACTIVE=prod

# Database Connection (use your MySQL service variables)
SPRING_DATASOURCE_URL=jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=${MYSQLUSER}
SPRING_DATASOURCE_PASSWORD=${MYSQLPASSWORD}

# Hibernate Configuration
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect

# Server Configuration
SERVER_PORT=8080
```

**Important:** Railway uses port 8080 by default, but your app uses 8082. Add:

```
PORT=8082
```

Or modify your application to use Railway's `PORT` environment variable.

**4. Link MySQL Service**

- In your web service settings
- Go to **"Service Variables"** or **"Connect"**
- Click **"Add Variable Reference"**
- Select your MySQL service
- This automatically injects MySQL connection variables

**5. Deploy**

- Click **"Deploy"**
- Railway will:
  1. Clone your GitHub repo
  2. Build with Maven
  3. Create Docker container
  4. Start your application
  5. Generate public URL

**6. Monitor Deployment**

Watch the **"Deployments"** tab:

- ⏳ Building... (2-5 minutes)
- ⏳ Deploying...
- ✅ **ACTIVE** (Success!)

If you see errors, check the **"Logs"** tab.

#### Phase 4: Verify Deployment

**1. Get Your Public URL**

- Go to **"Settings"** → **"Networking"**
- Find your public URL (e.g., `https://your-app.up.railway.app`)
- Or click **"Generate Domain"** if not auto-generated

**2. Test Your Application**

Visit your URL and test:

- ✅ Home page loads
- ✅ Customer registration works
- ✅ Login functionality works
- ✅ Database operations (create/read/update/delete)
- ✅ Ticket booking works

**3. Check Logs**

In Railway dashboard:

```
Started StadiumTicketMngmntSysApplication in X seconds
✅ DATABASE CONNECTION SUCCESSFUL!
Tomcat started on port(s): 8082
```

### Common Railway Deployment Issues

#### Issue 1: Build Fails

**Error:** `Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin`

**Solution:**

- Ensure Java version matches in `pom.xml`
- Add build command: `./mvnw clean install -DskipTests`

#### Issue 2: Database Connection Failed

**Error:** `Communications link failure`

**Solutions:**

1. Verify MySQL service is **ACTIVE**
2. Check environment variables are correctly set
3. Ensure `MYSQLHOST` is public hostname (mainline.proxy.rlwy.net), not internal
4. Add `?useSSL=false&allowPublicKeyRetrieval=true` to connection URL

#### Issue 3: Port Binding Error

**Error:** `Port 8082 already in use`

**Solution:**
Add environment variable:

```
PORT=8080
```

Or update your application to read Railway's PORT:

```properties
server.port=${PORT:8082}
```

#### Issue 4: Application Crashes on Startup

**Solutions:**

1. Check logs in Railway dashboard
2. Verify all required environment variables are set
3. Test locally with production profile first
4. Ensure `ddl-auto=update` not `create-drop`

### Railway Configuration Best Practices

1. **Use Environment Variables:** Never hardcode credentials
2. **Enable Automatic Deployments:** Link GitHub for CI/CD
3. **Monitor Logs:** Check regularly for errors
4. **Set Up Health Checks:** Railway monitors your app automatically
5. **Use Railway CLI:** For advanced configurations
   ```powershell
   npm i -g @railway/cli
   railway login
   railway link
   ```

### Post-Deployment Checklist

- [ ] Application accessible via public URL
- [ ] Database connection successful
- [ ] Customer registration/login works
- [ ] Organizer features functional
- [ ] Vendor booking works
- [ ] Payment processing tested
- [ ] No errors in Railway logs
- [ ] HTTPS enabled (automatic with Railway)
- [ ] Environment variables secured
- [ ] Auto-deploy from GitHub enabled

## 📊 Database Schema

### Core Entities

```
Customer
├── customerId (PK)
├── username
├── password
├── email
├── phoneNumber
└── registrationDate

EventOrganizer
├── organizerId (PK)
├── organizerName
├── email
├── password
├── phoneNumber
└── organizationType

Vendor
├── vendorId (PK)
├── vendorName
├── email
├── password
├── phoneNumber
└── businessType

Stadium
├── stadiumId (PK)
├── stadiumName
├── location
├── capacity
└── facilities

Program
├── programId (PK)
├── programName
├── description
├── startDate
├── endDate
├── ticketPrice
├── availableTickets
├── stadiumId (FK)
└── organizerId (FK)

Ticket
├── ticketId (PK)
├── ticketType
├── price
├── purchaseDate
├── programId (FK)
└── customerId (FK)

Cart
├── cartId (PK)
├── customerId (FK)
├── programId (FK)
├── quantity
└── addedDate

Payment
├── paymentId (PK)
├── amount
├── paymentDate
├── paymentMethod
├── status
└── customerId (FK)

Gallery
├── galleryId (PK)
├── galleryName
├── location
├── capacity
├── rentalPrice
└── stadiumId (FK)

ReviewsAndComplaints
├── reviewId (PK)
├── customerId (FK)
├── programId (FK)
├── rating
├── comment
└── submittedDate
```

## 🔌 API Endpoints

### Customer Endpoints

```
GET  /                          # Home page
GET  /customer/register         # Registration form
POST /customer/register         # Register customer
GET  /customer/login            # Login form
POST /customer/login            # Authenticate customer
GET  /customer/dashboard        # Customer dashboard
GET  /customer/programs         # View programs
POST /customer/cart/add         # Add to cart
GET  /customer/cart             # View cart
POST /customer/checkout         # Process payment
GET  /customer/tickets          # View purchased tickets
GET  /customer/settings         # Account settings
POST /customer/update           # Update profile
```

### Event Organizer Endpoints

```
GET  /organizer/register        # Registration form
POST /organizer/register        # Register organizer
GET  /organizer/login           # Login form
POST /organizer/login           # Authenticate organizer
GET  /organizer/dashboard       # Organizer dashboard
GET  /organizer/programs        # Manage programs
POST /organizer/program/create  # Create new program
POST /organizer/program/update  # Update program
DELETE /organizer/program/{id}  # Delete program
GET  /organizer/sales           # View sales report
GET  /organizer/settings        # Account settings
```

### Vendor Endpoints

```
GET  /vendor/register           # Registration form
POST /vendor/register           # Register vendor
GET  /vendor/login              # Login form
POST /vendor/login              # Authenticate vendor
GET  /vendor/dashboard          # Vendor dashboard
GET  /vendor/galleries          # View available galleries
POST /vendor/gallery/book       # Book gallery
GET  /vendor/bookings           # View bookings
GET  /vendor/settings           # Account settings
```

## 🧪 Testing

### Run Tests

```powershell
# Run all tests
.\mvnw.cmd test

# Run specific test class
.\mvnw.cmd test -Dtest=StadiumTicketMngmntSysApplicationTests

# Skip tests during build
.\mvnw.cmd clean install -DskipTests
```

### Manual Testing Checklist

**Customer Flow:**

1. Register new customer
2. Login with credentials
3. Browse programs
4. Add tickets to cart
5. Checkout and pay
6. View purchased tickets

**Organizer Flow:**

1. Register as organizer
2. Login
3. Create new program
4. Set pricing and availability
5. View sales dashboard

**Vendor Flow:**

1. Register as vendor
2. Login
3. Browse available galleries
4. Book gallery space
5. View booking confirmations

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch:
   ```powershell
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes:
   ```powershell
   git commit -m "Add: your feature description"
   ```
4. Push to your branch:
   ```powershell
   git push origin feature/your-feature-name
   ```
5. Open a Pull Request

### Code Style Guidelines

- Follow Java naming conventions
- Use meaningful variable names
- Add comments for complex logic
- Write unit tests for new features
- Keep methods small and focused

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Rifat Hossain**

- GitHub: [@RifatHossaiN47](https://github.com/RifatHossaiN47)
- Repository: [Stadium-Ticket-Management-System](https://github.com/RifatHossaiN47/Stadium-Ticket-Management-System)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Railway team for easy deployment platform
- MySQL for reliable database management
- Thymeleaf for server-side templating

## 📞 Support

If you encounter any issues:

1. Check the [DEPLOYMENT_GUIDE.md](DEPLOYMENT_GUIDE.md) for detailed instructions
2. Review [QUICK_START.md](QUICK_START.md) for quick setup
3. Check Railway logs for deployment errors
4. Open an issue on GitHub

## 🔄 Version History

- **v0.0.1-SNAPSHOT** (Current)
  - Initial release
  - Core features implemented
  - Railway deployment configured
  - Multi-profile support (dev/prod)

---

**⭐ If you find this project helpful, please consider giving it a star on GitHub!**

**🚀 Ready to deploy? Follow the [Railway Deployment Guide](#-deployment-to-railway) above!**
