
SpringBootLearning (Parent)
├── SpringBootLearning (Main app/module)
├── module1/           # Module 1 files
├── module2/           # Module 2 files  
├── module3/           # Module 3 files
├── pom.xml            # Parent POM
├── mvnw               # Maven wrapper
├── src/
│   └── main/
│       └── java/
└── Other modules...   # Additional learning modules
```

## 🗂️ Module Breakdown

| Module | Focus Area | Key Files |
|--------|------------|-----------|
| **SpringBootLearning** | Core app & REST APIs | `SpringBootApplication.java` |
| **module1** | JPA/Hibernate basics | Entity classes, Repositories |
| **module2** | Spring Security | Config, JWT utils |
| **module3** | Advanced features | Custom queries, DTOs |

## 🚀 Quick Start (Multi-Module)

```bash
# Clone & navigate
git clone https://github.com/yourusername/spring-boot-learning.git
cd spring-boot-learning

# Build all modules
./mvnw clean install

# Run main app
cd SpringBootLearning
./mvnw spring-boot:run
```

**Base URL**: `http://localhost:8080`

## 🔑 Key Features Implemented

- **REST APIs**: CRUD operations across modules
- **Spring Data JPA**: Entities with `@OneToMany` relationships
- **Hibernate**: Lazy loading, custom queries (`@Query`)
- **Spring Security**: JWT auth, `@PreAuthorize` on endpoints
- **Multi-module**: Independent testing per concept

## 📋 API Examples

```
# Public endpoint (no auth)
GET /api/public/info

# Authenticated endpoints
POST /api/auth/login  # Returns JWT
GET /api/users        # Bearer token required
POST /api/admin/users # ADMIN role only
```

## 🛠️ Tech Stack

- **Spring Boot**: 3.x (multi-module)
- **Maven**: Multi-module parent/child POMs
- **Database**: H2 in-memory + PostgreSQL config
- **IDE**: IntelliJ IDEA (as shown in screenshot)
- **Testing**: JUnit 5, MockMvc per module

## 📖 Learning Path

1. **Module Setup**: Parent POM, child modules 
4. **Security Config**: JWT filters, `UserDetailsService`
5. **Integration**: Cross-module dependencies

## 🎯 Next Milestones

- Add Dockerfiles per module
- Spring Cloud Config for multi-module
- CI/CD with GitHub Actions
- AWS deployment (RDS + EC2)

## 🤝 Contribution

- Fork individual modules for your learning
- Add your own concept modules
- PRs welcome for better module organization!
