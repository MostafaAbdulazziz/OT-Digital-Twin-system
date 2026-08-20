# ControlPoint Digital Twin - Internship Challenge

A full-stack, mock Operational Technology (OT) Digital Twin system. This application simulates industrial assets (e.g., pumps, motors), generates live telemetry data (temperature and pressure), and visualizes the system state through a React-based frontend dashboard.

## Technology Stack

*   **Backend:** Java 21, Spring Boot 3, Spring Data JPA, H2 In-Memory Database.
*   **Frontend:** React, TypeScript, Vite.
*   **Infrastructure:** Docker, Docker Compose, Nginx.

---

##  Prerequisites

To run this application locally, you will need the following installed:

*   **Docker & Docker Compose** (Recommended for seamless execution)
*   *For manual/local development without Docker:*
    *   **Java 21** (Eclipse Temurin or similar JDK)
    *   **Maven 3.9+**
    *   **Node.js 20+** and **npm**

---

##  Run Instructions

### Option 1: Running via Docker (Recommended)

The project is fully containerized with multi-stage Docker builds and a custom Nginx web server configured for production.

1. Ensure Docker Desktop/Daemon is running.
2. Open a terminal at the root of the project (where `docker-compose.yml` is located).
3. Execute the following command:
   ```bash
   docker compose up --build



4. Access the application:
* **Frontend Dashboard:** [http://localhost:5173](http://localhost:5173)
* **Backend API:** `http://localhost:8080/api/assets`
* **H2 Database Console:** `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:digitaltwin`, Username: `sa`, no password)



### Option 2: Running Manually

**Start the Backend:**

1. Navigate to the `backend` directory: `cd backend`
2. Run the Spring Boot application: `./mvnw spring-boot:run` (or `mvn spring-boot:run`)
3. The backend will start on port 8080.

**Start the Frontend:**

1. Open a new terminal and navigate to the `frontend` directory: `cd frontend`
2. Install dependencies: `npm install`
3. Start the Vite development server: `npm run dev`
4. The frontend will start on port 5173.

---

## Assumptions & Design Choices

### 1. Clean Architecture & Separation of Concerns

The backend adheres strictly to the **Single Responsibility Principle**.

* **Interface-Driven Services:** Business logic is abstracted into interfaces (`AssetService`, `SensorReadingService`) with separate implementation classes to decouple components and facilitate future unit testing.
* **Entity-DTO-Mapper Pattern:** Database models (`Asset`, `SensorReading`) are strictly isolated from the API endpoints. Custom `@Component` mappers translate entities into Data Transfer Objects (DTOs), preventing internal data leakage and ensuring the API contract remains stable.

### 2. OT Simulation Engine (Heartbeat Polling)

The data simulator utilizes Spring's `@Scheduled(fixedRate = 5000)` rather than a Cron expression. In an Operational Technology (OT) context, a Programmable Logic Controller (PLC) operates on continuous hardware polling loops (heartbeats) rather than calendar-based clock times. `fixedRate` accurately models this physical hardware behavior.

### 3. Startup Data Seeding

Since H2 is an in-memory database that wipes on every restart, a `CommandLineRunner` (`DataSeeder.java`) was implemented to inject three sample industrial assets upon application boot. This prevents "empty state" errors, gives the simulation loop immediate targets to update, and provides immediate visual feedback in the UI.

### 4. Global Exception Handling

Instead of returning `null` or raw stack traces when an asset or reading is missing, the application relies on a `@ControllerAdvice` Global Exception Handler. It catches custom `ResourceNotFoundException`s and translates them into structured, standardized HTTP 404 JSON responses.

### 5. Deployment & Security Infrastructure

Given ControlPoint's mission as a cybersecurity provider, the deployment configuration includes proactive security measures:

* **Stateless API:** Security configurations explicitly disable CSRF and enforce a stateless session policy, which is best practice for REST APIs.
* **Multi-Stage Docker Builds:** Both backend and frontend use multi-stage Dockerfiles. The runtime images only contain compiled artifacts (the `.jar` and static `.js/.css`), keeping image sizes minimal and reducing the attack surface.
* **Hardened Nginx Configuration:** The frontend is served via Nginx with explicitly defined security headers (`X-Frame-Options`, `X-XSS-Protection`, `X-Content-Type-Options`) and aggressive caching strategies for static assets, while ensuring `index.html` is never cached so the Single Page Application (SPA) always routes correctly.

