package reactive2

//////////////////////////
// Typed Actor context  //
//////////////////////////

import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

import scala.concurrent.Await
import scala.concurrent.duration._

// With Scala 3 and significant indention style
object TypedToggleActor:

  enum Command:
    case HowAreYou(relyTo: ActorRef[String]) extends Command
    case Done(relyTo: ActorRef[String]) extends Command

  import Command._

  def happy: Behavior[Command] = Behaviors.receiveMessage {
    case HowAreYou(replyTo) =>
      replyTo ! "happy"
      sad
    case Done(replyTo) =>
      replyTo ! "Done"
      Behaviors.stopped
  }

  def sad: Behavior[Command] = Behaviors.receiveMessage {
    case HowAreYou(replyTo) =>
      replyTo ! "sad"
      happy
    case Done(replyTo) =>
      replyTo ! "Done"
      Behaviors.stopped
  }

  def apply(): Behavior[Command] = happy

object TypedToggleMain:

  def apply(): Behavior[String] = Behaviors.setup(context => apply(context.spawn(TypedToggleActor(), "toggle").ref))

  def apply(toggle: ActorRef[TypedToggleActor.Command]): Behavior[String] =
    Behaviors.receive((context, msg) =>
      msg match {
        case "Init" =>
          toggle ! TypedToggleActor.Command.HowAreYou(context.self)
          toggle ! TypedToggleActor.Command.HowAreYou(context.self)
          toggle ! TypedToggleActor.Command.HowAreYou(context.self)
          toggle ! TypedToggleActor.Command.Done(context.self)
          Behaviors.same
        case "Done" =>
          context.system.terminate
          Behaviors.stopped
        case msg: String =>
          println(s" received: $msg")
          Behaviors.same
      })

@main def typedToggleApp(): Unit =
  val system: ActorSystem[String] = ActorSystem(TypedToggleMain(), "Reactive2")

  system ! "Init"

  Await.result(system.whenTerminated, Duration.Inf)
