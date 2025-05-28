
---

## 🚀 How to Run

### 💻 GUI Application (Java Swing)

1. Ensure **Java 11+** is installed.
2. Run from terminal:
    ```bash
    mvn clean compile
    mvn exec:java -Dexec.mainClass="com.capaciti.morse.MainFrame"
    ```
    Or run `MainFrame` from your IDE.

---

### 🌐 REST API (Spring Boot)

1. Run backend:
    ```bash
    mvn spring-boot:run
    ```
2. API endpoints:
    - Encode: [http://localhost:8080/api/morse/encode?text=HELLO](http://localhost:8080/api/morse/encode?text=HELLO)
    - Decode: [http://localhost:8080/api/morse/decode?code=.... . .-.. .-.. ---](http://localhost:8080/api/morse/decode?code=.... . .-.. .-.. ---)
    - Audio: [http://localhost:8080/api/morse/sound?text=HELLO](http://localhost:8080/api/morse/sound?text=HELLO)

---

### ⚛️ React Frontend

1. Navigate to frontend:
    ```bash
    cd frontend
    ```
2. Install dependencies:
    ```bash
    npm install
    ```
3. Start the React app:
    ```bash
    npm start
    ```
4. Visit `http://localhost:3000` in your browser.

---

## ⚙️ Technologies Used

- Java 11+
- Java Swing (GUI)
- Spring Boot (REST API)
- React with Material-UI (Frontend)
- Maven (Java dependencies)
- Node.js / npm (Frontend dependencies)
- Java Sound API (Audio generation)

---

## 📦 Dependencies

- Backend (`pom.xml`):
  - `spring-boot-starter-web`
  - `javax.sound.sampled` (built-in)
  - JUnit (optional tests)
- Frontend (`package.json`):
  - `@mui/material`
  - `@mui/icons-material`
  - `react`, `react-dom`
  - Testing libraries (optional)

---

## 🧪 Examples

### Encode via API
```bash
GET /api/morse/encode?text=SOS
# Output: ... --- ...
