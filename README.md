# Qint_backend

```mermaid
erDiagram
    user {
        Long id PK
        String email UK
        String password
        Long correct_answers
        Long incorrect_answers
    }

    category {
        Long id PK
        String name
    }

    question {
        Long id PK
        String contents
        String commentary
        Long question_id FK
        Long category_id FK
    }
    
    answer {
        Long id PK
        String text
        boolean is_correct
        Long question_id FK
    }

    user_incorrect_answers {
        Long id PK
        Long user_id FK
        Long question_id FK
        Long answer_id FK "유저가 선택한 오답"
    }

    email_verification {
        Long id PK
        String auth_code
        String email
        Boolean is_verified
        DateTime created_at
    }

    refresh_token {
        int token_id PK
        String token
        String email
        date expiry_date
    }

    user ||--o{ user_incorrect_answers : has
    category ||--o{ question : contains
    question ||--|{ answer : has
    question ||--o{ user_incorrect_answers : is_incorrect
    user_incorrect_answers |o--o| answer : has
```