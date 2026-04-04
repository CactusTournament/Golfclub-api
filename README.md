# Golf Club Tournament and Membership API

A Java Spring Boot REST API for managing golf club members and tournaments, with Docker and PostgreSQL support.

## Features
- CRUD for Members and Tournaments
- Many-to-many relationship (members join tournaments)
- Search endpoints for Members and Tournaments
- Dockerized for local development
- PostgreSQL database (local and AWS RDS supported)
- Ready for CI/CD

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose


### Running Locally (with Docker)

1. **Build the project:**
   ```sh
   mvn clean package
   ```
2. **Start with Docker Compose:**
   ```sh
   docker-compose up -d
   ```
3. **API Docs:**
   Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Troubleshooting Docker
- Ensure the JAR is built before running Docker Compose.
- If you get DB connection errors, check that the `db` service is healthy and ports are not in use.

### Search API Endpoints

#### Members
- `GET /api/members/search/name?name=...` — Search by member name
- `GET /api/members/search/email?email=...` — Search by email
- `GET /api/members/search/membershipType?membershipType=...` — Search by membership type
- `GET /api/members/search/phoneNumber?phoneNumber=...` — Search by phone number
- `GET /api/members/search/startDate?startDate=YYYY-MM-DD` — Search by tournament start date

#### Tournaments
- `GET /api/tournaments/search/name?name=...` — Search by tournament name
- `GET /api/tournaments/search/startDate?startDate=YYYY-MM-DD` — Search by start date
- `GET /api/tournaments/search/location?location=...` — Search by location
- `GET /api/tournaments/search/member?memberId=...` — Search tournaments by member ID

### Adding Members to Tournaments
- `POST /api/members/{memberId}/tournaments/{tournamentId}` — Add a member to a tournament

### Switching to AWS RDS
1. **Create an AWS RDS PostgreSQL instance** (see AWS docs for details).
2. **Update your `src/main/resources/application.yml`**:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://<rds-endpoint>:5432/golfclub-db
       username: <your_rds_username>
       password: <your_rds_password>
       driver-class-name: org.postgresql.Driver
   ```
3. **Import schema and data:**
   - Connect to RDS with psql using SSL:
     ```sh
     psql -h <rds-endpoint> -p 5432 -U <username> -d golfclub-db --sslmode=verify-full --sslrootcert=./global-bundle.pem
     ```
   - Run:
     ```sql
     \i 'src/main/resources/schema.sql'
     \i 'src/main/resources/data.sql'
     ```
4. **Restart your Spring Boot app** (locally or in Docker) to use the RDS database.

#### Notes
- If you encounter foreign key or duplicate key errors, clear the tables before re-importing.
- See screenshots for RDS setup, psql connection, and data verification.

### Deployment Steps
- See included screenshots for:
  - Docker running (terminal or Docker Desktop)
  - Postman API tests
  - Swagger UI
  - AWS RDS setup and connection

### Issues/Challenges
- Document any issues you encountered with RDS or Docker in this section.
- Example: "Had to adjust security group to allow inbound connections to RDS on port 5432."

### (Optional) GitHub Actions
- A sample workflow is included in `.github/workflows/ci.yml` to build and test the project on push/PR.
- To push Docker images to Docker Hub, add a job to the workflow and set up Docker Hub secrets.

---
## Project Structure
- `model/` - JPA entities
- `repository/` - Spring Data JPA repositories
- `service/` - Business logic
- `controller/` - REST endpoints
- `config/` - Configuration classes

## Docker
- `Dockerfile` - App container
- `docker-compose.yml` - App + PostgreSQL

## CI/CD
- See `.github/workflows/` for GitHub Actions example

---
MIT License
