# Gym Management System

## Description

A simple Java Swing application for managing gym members. It allows users to add regular and premium members, activate/deactivate memberships, mark attendance, and view member details. The application features a graphical user interface (GUI) with a background image and persists member data locally using file storage.

## Features

- **Add Members**:
  - Add new regular or premium members with detailed information (ID, name, location, contact, DOB, membership start date, etc.).
  - Specific fields for regular members (referral source, plan selection).
  - Specific fields for premium members (personal trainer).
- **View Members**:
  - View a list of all registered members.
  - Filter view by member type (Regular, Premium, or All).
- **Membership Management**:
  - Activate member accounts.
  - Deactivate member accounts.
- **Attendance Tracking**:
  - Mark member attendance. Loyalty points are updated accordingly.
- **Data Persistence**:
  - Member data is saved locally to a file (`gym_data.dat`) and loaded when the application restarts.
- **User Interface**:
  - Graphical user interface built with Java Swing.
  - Custom background image for the main window.

## Prerequisites

- Java Development Kit (JDK) version 8 or higher installed and configured on your system.

## How to Run the Application

1.  **Ensure Image is Present**:

    - Make sure the `gym_background.png` file is located in the root directory of the compiled classes (e.g., alongside your `.class` files or at the root of your project if running from an IDE that includes it in the classpath).

2.  **Compile the Java Files**:

    - Open a terminal or command prompt.
    - Navigate to the directory where your Java source files (`.java`) are located.
    - Run the following command to compile all necessary Java files:
      ```bash
      javac GymMember.java RegularMember.java PremiumMember.java GymGUI.java
      ```

3.  **Run the Application**:
    - After successful compilation, run the application using the main class `GymGUI`:
      ```bash
      java GymGUI
      ```
    - The Gym Management System GUI should now appear.

## Data Storage

- Member data is stored in a file named `gym_data.dat` in the same directory where the application is run. If this file is deleted, all stored member data will be lost.
