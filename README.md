# Image Processing Application

This project is a Java-based application that integrates with Camunda Zeebe for workflow automation and a PostgreSQL database for data storage. It handles image-related workflows, including requesting images by type and retrieving the last stored image.

---

## **Features**
- **Zeebe Integration**: Processes jobs using Camunda Zeebe workers.
- **Image Request Workflows**: Handles image requests by type.
- **Last Stored Image Retrieval**: Retrieves the most recently stored image.
- **PostgreSQL Support**: Configures a PostgreSQL database for persistence.

---

## **Project Structure**

### **Packages**
- `io.github.aprilvuylsteke000.config`: Contains configuration loaders.
- `io.github.aprilvuylsteke000.handlers`: Handles job processing for specific workflow tasks.
- `io.github.aprilvuylsteke000.services`: Implements business logic for handling image workflows.
- `io.github.aprilvuylsteke000.workers`: Sets up Zeebe job workers.
- `io.github.aprilvuylsteke000`: The entry point for the application.

---

### **Class Overview**

#### **1. ConfigLoader**
- Loads configuration from the `application.properties` file.
- Provides properties such as Zeebe credentials and PostgreSQL configuration.

#### **2. Handlers**
- `ImageByTypeStorageServiceHandler`: Processes jobs of type `requestImageByType`.
- `ImageLastStoredServiceHandler`: Processes jobs of type `getLastStoredImage`.

#### **3. Services**
- `ImageByTypeStorageService`: Contains logic for requesting images by type.
- `ImageLastStoredService`: Contains logic for retrieving the last stored image.

#### **4. Workers**
- `ImageByTypeStorageWorker`: Starts a Zeebe worker for `requestImageByType`.
- `ImageLastStoredWorker`: Starts a Zeebe worker for `getLastStoredImage`.

#### **5. ImageApplication**
- The main entry point for the application.
- Sets up the Zeebe client with OAuth authentication.
- Starts the workers and connects to the Zeebe gateway.

---

## **Setup**

### **Prerequisites**
1. Java 17+
2. Maven
3. PostgreSQL database
4. Camunda Zeebe account

### **Configuration**
Add your credentials and configurations in `src/main/resources/application.properties`:
```properties
# Zeebe configuration
zeebe.address=21bd648c-0b98-4442-be21-c9272edbc4cf.ont-1.zeebe.camunda.io:443
zeebe.client-id=5mlRA9C91jBxfgSGHsQrO~Q2jrZ7_UA6
zeebe.client-secret=Qd-Z1Zk7ghhzBGaWo6Rb-GQCFBrg0aAeU_s6kCC7a-UATHAZc~L1JL8c64M3_zXt
zeebe.authorization-server-url=https://login.cloud.camunda.io/oauth/token
zeebe.token-audience=zeebe.camunda.io

# PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://your-database-url:5432/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
