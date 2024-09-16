fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessages: Int) {
  
  if (numberOfMessages > 100){
    println("tu telefono va ha explotar!! Tienes +99 notificaciones" )
  } else if (numberOfMessages < 100){
    println("Tienes "+numberOfMessages+" notificaciones" )
  }

}
