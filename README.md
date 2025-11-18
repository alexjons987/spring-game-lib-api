# Game Library API
Students can choose a fun theme, **but the requirement structure is the same**.
## 1. Project structure
The project should follow a clean structure:
* `config/`
* `controller/`
* `dto/`
* `entity/`
* `exception/`
* `repository/`
* `service/`
## 2. Database (MySQL)
* The application should be connected to a MySQL database
* The table should be created via **JPA**
* At least **one main entity**, e.g.:
  * Game (id, title, genre, rating, release)
* At least **one relationship entity**, e.g.:
  * Publisher
  * Developer
  * Category
  * Tag
  * Review
Relationship at least:
* One-To-Many or Many-To-Many
Example:
* A Game can have many Reviews
*Or Game can have many Tags
## 3. CRUD + Relationships
Game must have:
* `GET /games`
* `GET /games/{id}`
* `POST /games`
* `PUT /games/{id}`
* `DELETE /games/{id}`
Relation endpoints (at least 1)
One of the following:
* Add a Review to a Game
* Connect Tags to a Game
* Connect Game to Publisher
Requirements:
* When creating a Game, the relationship must be set via ID
* When GET, the relationship's data should also be displayed
## 4. DTOs
* Use **DTOs for all incoming and outgoing requests**
* No entity should be exposed directly
* For example:
  * `GameRequestDTO`
  * `GameResponseDTO`
  * `ReviewDTO`
## 5. Service layer
Controller → Service → Repository
* No logic in the controller
* All business logic should be in the service
## 6. Validation
Validate DTOs:
* title → `@NotBlank`
* rating → `@Min(1)` `@Max(10)`
* releaseYear → `@Min(1950)`
If validation fails, the correct error message should be returned.
## 7. Custom Exceptions
Custom exception system:
* `ResourceNotFoundException`
* `BadRequestException`

A **GlobalExceptionHandler** should exist and return JSON error responses like:
```json
{
    "error": "Game not found",
    "status": 404,
    "timestamp": "2025-01-01T12:00:00"
}
```
## 8. Search & Filtering
At least one of the following:
* Search games by genre
* Filter games with rating > X
* Search by title (contains)
* Sorting (asc/desc)
* Pagination (page & size)
## 9. Extra endpoint
Let students be creative. Example:
* `/games/top-rated` → return games with rating > 9
* `/games/random` → return a random game
* `/games/{id}/summary` → generate a small text description
* `/games/stats` → number of games per genre
## 10. Configure Spring Security for the following requirements:
### A. Enable HTTP Basic authentication
* The application should use **HTTP Basic**
* CSRF should be disabled (for simplicity with the REST API)
### B. Allow/deny access
* **1.** Allow all traffic without authentication to:
  * `/public/**`
* **2.** Require authentication for:
  * all other endpoints in the application
  * including `/private/secret`
  * including all CRUD endpoints you built earlier
### C. Create an in-memory user
* username: `student`
* password: `password`
* role: `USER`
* The password should be encoded with a PasswordEncoder (e.g. BCrypt)
### D. Test in Postman/browser
1. ✔ `GET /public/hello` should work without login
2. ✔ `GET /private/secret` should give 401 Unauthorized without credentials
3. ✔ When you enter the correct username + password, all protected endpoints should work