# Morse Code Translator (GUI + REST API)

This project is a full-featured **Morse Code Translator** built with:

- 🎨 A **Java Swing GUI** for local use with sound playback.
- 🌐 A **Spring Boot REST API** to encode/decode Morse code and return Morse audio as a `.wav` file.

---

## 🧩 Features

### GUI Application (Swing)
- Encode plain text to Morse code.
- Decode Morse code to plain text.
- Play Morse code as beeps.
- Copy/paste integration with system clipboard.
- Stylish, responsive layout using `JFrame`.

### REST API (Spring Boot)
- `/api/morse/encode?text=...` – Encode plain text.
- `/api/morse/decode?code=...` – Decode Morse code.
- `/api/morse/sound?text=...` – Download Morse code as audio (`.wav`).

---

## 📁 Project Structure


---

## 🚀 How to Run

### 💻 GUI Application (Java Swing)

1. Make sure you have **Java 11+** installed.
2. Open your terminal:
    ```bash
    mvn clean compile
    mvn exec:java -Dexec.mainClass="com.capaciti.morse.MainFrame"
    ```
    Alternatively, run it via your IDE (IntelliJ, Eclipse) by executing the `MainFrame` class.

---

### 🌐 REST API (Spring Boot)

1. Run the backend:
    ```bash
    mvn spring-boot:run
    ```

2. Access API endpoints:
    - Encode: [http://localhost:8080/api/morse/encode?text=HELLO](http://localhost:8080/api/morse/encode?text=HELLO)
    - Decode: [http://localhost:8080/api/morse/decode?code=.... . .-.. .-.. ---](http://localhost:8080/api/morse/decode?code=.... . .-.. .-.. ---)
    - Download Audio: [http://localhost:8080/api/morse/sound?text=HELLO](http://localhost:8080/api/morse/sound?text=HELLO)

---

## ⚙️ Technologies Used

- **Java 11+**
- **Java Swing** for GUI
- **Spring Boot** for REST API
- **Maven** for dependency management
- **Java Sound API** for audio generation

---

## 📦 Dependencies

Main dependencies are defined in `pom.xml`, including:

- `spring-boot-starter-web`
- `javax.sound.sampled` (built-in)
- Any optional test dependencies (JUnit)

---

## 🧪 Example

### Encode:
```bash
GET /api/morse/encode?text=SOS
# Output: ... --- ...
