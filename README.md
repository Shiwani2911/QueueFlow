# QueueFlow

**A mobile-based queue management application built with Kotlin and Jetpack Compose.**

QueueFlow is an Android application prototype designed to make waiting in queues more convenient. It aims to let customers select a bank, choose a branch and service, and receive a digital queue token instead of relying entirely on physical queues.

The project is currently being developed as a learning project to explore Android development and solve a real-world problem.

## Features

- **Welcome Screen:** An introductory screen for the application.
- **Phone Login:** A phone-number-based login flow.
- **OTP Verification:** A manual OTP verification flow for demonstration purposes.
- **Bank Selection:** Select a bank from the available demo options.
- **Branch Selection:** Choose a branch.
- **Service Selection:** Select the service required.
- **Digital Queue Token:** Generate and view a queue token.
- **Queue Dashboard:** View queue-related information and token status.
- **My Tokens:** Access your tokens within the application.
- **Navigation:** Navigate between screens using Android navigation behavior.

## Technology Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **IDE:** Android Studio
- **Build System:** Gradle

## Current Project Status

QueueFlow is currently a prototype.

The following components are not yet connected to production services:

- OTP verification is manual and does not send real SMS messages.
- Queue information and demo data are managed locally in memory.
- No production backend or database has been integrated.
- Bank and branch information is currently demonstration data.
- No real banking application integration is available yet.

## Future Improvements

- Backend and database integration.
- Real phone-number authentication and SMS OTP.
- Persistent user accounts and queue tokens.
- Real-time queue updates.
- Business and branch management dashboards.
- Integration possibilities with existing banking applications.
- Support for additional sectors beyond banking.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/Shiwani2911/QueueFlow.git
   ```

2. Open the project in Android Studio.

3. Allow Gradle to sync.

4. Run the application on an Android emulator or a compatible Android device.

## Project Purpose

QueueFlow is being developed to strengthen practical Android development skills while exploring how digital queue management could improve customer experiences in places where people regularly wait for services.

---

**Project:** QueueFlow  
**Platform:** Android  
**Status:** In Development
