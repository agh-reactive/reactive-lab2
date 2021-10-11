# Reactive programming in Scala<br>Lab 2: Designing actor systems

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

## Example 2: BuddyChat

The updated version can be found here: [https://github.com/agh-reactive/buddychat](https://github.com/agh-reactive/buddychat)

### Running
```
git clone https://github.com/agh-reactive/buddychat.git
cd buddychat
sbt run
```

## Assignment: E-Shop system

Your assignment is to implement a part of an e-Shop system composed of actors **Cart** and **Checkout**

### Cart actor

![Cart actor](cart.png)

### Checkout actor

![Checkout actor](checkout.png)

### Separating domain logic
Good practice in actor system design is to separate the domain logic from the communication logic. For example, the `Cart` actor should not implement both the state of the cart and the communication protocol, because testing the business logic (cart state) will not be possible in isolation, only through the asynchronous communication protocol. In this case, it is better to separate a `case class Cart` representing the domain object from `actor CartActor` implementing the communication protocol:

```
case class Cart(items: Seq[Any]) {
    def contains(item: Any): Boolean = ???
    def addItem(item: Any): Cart     = ???
    def removeItem(item: Any): Cart  = ???
    def size: Int                    = ???
  }


 class CartActor extends Actor {
    def receive = {
      case AddItem(item, count) => ???
      ...
    }
  }

  object CartActor {
    def apply(): Behavior[Command] = Behaviors.receiveMessage {
      case AddItem(item, count) => ???
      ...
    }
  }
```

## Assignments
#### (20 pts) Implement actors **CartActor** and **Checkout**
- Design messages as `Case classes`. Distinguish between messages-commands (e.g. `AddItem`) and messages-events (e.g. `ItemAdded`). More about this [here](https://www.informit.com/articles/article.aspx?p=2428369).
```
object CartActor {
    sealed trait Command
    case class AddItem(id: String) extends Command
    ...
    sealed trait Event
    case class ItemAdded(id: String) extends Event
  }
```
- To implement a state machine, please use the standard actor `context.become` mechanism.
- Create an app, which performs a simple test of created actors.
- To implement time limits please use [scheduler's](https://doc.akka.io/docs/akka/current/scheduler.html#classic-scheduler) or [timer's](https://doc.akka.io/docs/akka/current/typed/from-classic.html#timers) mechanism. The actor should schedule sending a message to itself to mark that maximum time has elapsed:
  - CartTimer: the time after which the cart is emptied.
  - CheckoutTimer: the time after which checkout transaction is cancelled.
  - PaymentTimer: maximum wait time for payment transaction completion, after which payment operation is cancelled.

#### (10 pkt) Use [Akka Typed](https://doc.akka.io/docs/akka/current/typed/actors.html) to implement TypedCartActor
#### (10 pkt) Use [Akka Typed](https://doc.akka.io/docs/akka/current/typed/actors.html) to implement TypedCheckout

## Implementation 
To implement the above tasks please use [prepared template containing initial test suites](https://github.com/agh-reactive/reactive-scala-labs-templates) 

Tests can be run with `sbt test`.

The template was created to ease homework implementation and focus more on significant parts of tasks. This template is adjusted and updated yearly, and some oversight can happen. Such issues and improvements can be raised directly on the github repo. Such activities will be rewarded with additional points. During task evaluation, the focus will be on crucial parts of the code and theory behind particular akka mechanisms.

## Submission
- Create a new private repository on github named `reactive-scala-labs`
- Add your teacher to this repository as a collaborator with write access
- Clone the repo `https://github.com/agh-reactive/reactive-scala-labs-templates` and push it to the repo created in step 1
- Create branch `lab2-solution` and add your full name to `README.md` file
- Create a `Pull Request` from `lab2-solution` to `master` branch
- Send the link to the `PR` as a solution to this assignment
