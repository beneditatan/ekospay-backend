# eKosPay API

## Context

eKosPay is a web application for owners of Indonesian boarding houses, commonly known as kos. The project started with a practical problem. When rent collection goes through a caretaker, it can be hard for the owner to see who has paid and reconcile those payments with the money received. eKosPay's long-term goal is to let owners collect rent directly and keep payment records tied to the correct property and room.

This repository contains the Spring Boot backend API. A separate web frontend will use the API for owner onboarding and, later, rent management.

## Objective

The first milestone gives an owner a way to create a profile, register one or more boarding houses, and add rooms with their base monthly rent. The API reports what setup is still missing. Onboarding is complete when at least one of the owner's properties has a room.

The milestone targets local development with test data. Authentication, tenant management, and payments are planned for later milestones.

See the [functional requirements tracker](docs/initial-requirements.md) for the API behavior and implementation status.

## Prerequisites

- Java 21 or newer
- Maven 3.6.3 or newer
- Node.js 24
- Access to the linked Neon project

## Neon setup

Authenticate and deploy the services declared in `neon.ts`:

```bash
npx neon@latest login
npx neon@latest deploy
```

The deploy command writes Neon-managed variables to the gitignored `.env.local` file. The application uses the pooled `DATABASE_URL` for normal query traffic. Future Flyway migrations must use `DATABASE_URL_UNPOOLED`.

## Build

```bash
npm run build
```

## Run

```bash
npm start
```

Verify the database connection:

```bash
curl http://localhost:8080/api/v1/health/database
```
