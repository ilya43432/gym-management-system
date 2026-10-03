# Gym Management System

A Java console application modeling the back-office operations of a gym — clients,
instructors, a secretary role, and scheduled sessions — built as an Object-Oriented
Programming course assignment. The focus of the project is correct application of
design patterns, custom exception handling, and encapsulated business rules rather than
a UI.

## Features

- **Client management** — register/unregister clients with age and duplicate-registration
  validation, enforced via custom checked exceptions.
- **Session scheduling** — create sessions of different types (Pilates, Machine Pilates,
  Thai Boxing, Ninja), each with its own price and group-size limits.
- **Instructor qualification checks** — an instructor can only be assigned to a session
  type they are certified to teach.
- **Secretary role** — a single secretary manages client registration, worker hiring, and
  session scheduling on behalf of the gym.
- **Notifications** — clients can be notified by session, by date, or gym-wide.
- **Gym balance tracking** — session payments and worker salaries are reflected in a
  running gym balance.
- **Action history** — every significant operation is recorded for later reporting via
  `Gym.printActionHistory()`.

## Design Patterns

| Pattern | Where | Why |
|---|---|---|
| **Singleton** | `Gym.java` | Exactly one `Gym` instance exists per run (`Gym.getInstance()`), since all clients, workers, and sessions belong to a single gym. |
| **Factory** | `Sessions/SessionFactory.java` | `SessionFactory.CreateSession(...)` builds the correct `Session` subclass (Pilates, MachinePilates, ThaiBoxing, Ninja) with its pricing/group-size pulled from `SessionConfig`, so callers never construct session subtypes directly. |
| **Observer** | `Notifications/Observer.java`, `Notifications/Publisher.java` | `Gym` implements `Publisher` and notifies registered `Observer`s (clients) by session, by date, or gym-wide. |

## Custom Exceptions

Business rules are enforced through dedicated checked exceptions rather than generic
errors:

- `InvalidAgeException` — client does not meet a session's/gym's age requirement.
- `DuplicateClientException` — attempting to register an already-registered client.
- `ClientNotRegisteredException` — operating on a client that was never registered.
- `InstructorNotQualifiedException` — assigning an instructor to a session type they are
  not certified for.

## Project Structure

```
gym/
├── management/
│   ├── Gym.java              # Singleton; owns clients, workers, sessions, balance
│   ├── Secretary.java         # Registration/scheduling operations
│   ├── Instructor.java        # Qualification checks
│   ├── GymWorkers.java
│   └── Sessions/              # Session types + SessionFactory (Factory pattern)
├── customers/                 # Client, Person, Gender
├── Notifications/              # Observer / Publisher
├── Exception/                  # Custom checked exceptions
└── Utils/
Main.java                       # Entry point / demo scenario
```

## Running

```bash
javac Main.java gym/**/*.java
java Main
```

`Main.java` sets up a sample gym, registers clients (including one that intentionally
fails `InvalidAgeException`), schedules sessions, and exercises the notification flows —
useful as a runnable demo of the full object graph.

## Tech Stack

Java · Custom exception hierarchy · Singleton / Factory / Observer patterns
