# Qint_backend

```mermaid
erDiagram
    user {
        Long id PK
        String email UK
        String password
        Long correctAnswers
        Long incorrectAnswers
    }

    category {
        Long id PK
        String name
    }

    question {
        Long id PK
        String contents
        String commentary
        Long questionID FK
        Long categoryID FK
    }
    
    answer {
        Long id PK
        String text
        boolean isCorrect
        Long questionID FK
    }

    user_incorrect_answers {
        Long id PK
        Long userID FK
        Long questionID FK
    }

    email_verification {
        Long id PK
        String auth_code
        String email
        Boolean is_verified
        DateTime created_at
    }

    user ||--o{ user_incorrect_answers : has
    category ||--o{ question : contains
    question ||--|{ answer : has
    question ||--o{ user_incorrect_answers : is_incorrect
```