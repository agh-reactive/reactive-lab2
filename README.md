# Reactive programming in Scala - Lab 2: Designing actor systems

## Example 1: Toggle and BankApp

```git clone https://github.com/balis/reactive-lab2```

### Running in console

```
cd reactive-lab2
sbt "runMain reactive2.BankApp"
sbt "runMain reactive2.ToggleApp"
```

### Running in IntelliJ

**Method 1**: After opening the project, select `sbt shell` on the toolbar at the bottom of the screen, then type:
```
sbt "runMain reactive2.BankApp"
sbt "runMain reactive2.ToggleApp"
```

**Method 2**: right-click on the `object BankApp` and select `Run: BankApp`

### Configuration
Configuration of logging can be found in [src/main/resources/application.conf](https://github.com/agh-reactive/reactive-lab2/blob/master/src/main/resources/application.conf)

## Example 2: BuddyChat (legacy)

Updated version can be found here: [https://github.com/agh-reactive/buddychat](https://github.com/agh-reactive/buddychat)

### Running
```
git clone https://github.com/agh-reactive/buddychat.git
cd buddychat
sbt run
```

## Assignments: E-Shop

Your assignment is to implement a part of an e-Shop system composed of the following actors:
