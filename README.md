# Dar al-Ilm (Library Management System)

**Dar al-Ilm** (House of Knowledge) is a modern, academic Library Management System built with Jakarta EE 10 and JSF. It provides a comprehensive solution for managing books, members, and loans in an academic or public library setting.


## Features

*   **Dashboard**: Real-time overview of library metrics (Total Books, Members, Active Loans) and insights (Recently Added, Low Stock).
*   **Book Management**:
    *   Cataloging of books with Title, Author, ISBN, and Quantity.
    *   Search functionality by Title, Author, or ISBN.
    *   Availability tracking (Available/Out of Stock status).
*   **Member Management**:
    *   Registration of library members.
    *   Duplicate email prevention.
*   **Loan Management**:
    *   Issue issues to members.
    *   Track active and returned loans.
    *   Automatic stock adjustment.
*   **Academic UI/UX**:
    *   Clean, typography-focused "Dar al-Ilm" theme.
    *   Responsive card-based layout.
    *   User-friendly feedback (Toast notifications, Error messages).

## Technology Stack

*   **Backend**: Jakarta EE 10 (EJB, CDI, JPA, JSF).
*   **Server**: WildFly 27.0.1.Final.
*   **Database**: MySQL (compatible with H2 for dev).
*   **Frontend**: Jakarta Faces (JSF 4.0), CSS3 (Custom Academic Theme), Google Fonts (Merriweather, Roboto).
*   **Build Tool**: Maven.

## Setup & Deployment

### Prerequisites
*   JDK 11 or 17.
*   Maven 3.8+.
*   WildFly 27+.
*   MySQL Database (Optional, H2 used by default in persistence.xml).

### Installation

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/abdellahBSK/Dar-al-Ilm.git
    cd Dar-al-Ilm
    ```

2.  **Configure Database** (If using MySQL):
    *   Update `src/main/resources/META-INF/persistence.xml` with your database credentials.

3.  **Build the Project**:
    ```bash
    mvn clean package
    ```

4.  **Deploy**:
    *   Copy the generated `target/lms.war` to your WildFly deployments directory:
    ```bash
    cp target/lms.war /path/to/wildfly/standalone/deployments/
    ```

5.  **Access the Application**:
    *   Open your browser and navigate to: [http://localhost:8080/lms](http://localhost:8080/lms)

## License
This project is licensed under the MIT License - see the LICENSE file for details.
