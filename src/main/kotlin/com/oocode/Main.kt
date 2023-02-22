import com.teamoptimization.LocatorClient
import com.teamoptimization.MetOfficeForecasterClient
import java.time.DayOfWeek

fun main(args: Array<String>) {
    if (args.size != 2) {
        throw RuntimeException("Must specify Day and Place")
    }
    forecast(args[0], args[1])
}

fun forecast(day: String, place: String) {
    val forecaster = MetOfficeForecasterClient()
    val dayNumber = DayOfWeek.valueOf(day.uppercase()).value
    val location = LocatorClient().locationOf(place)
    val (minTemp, maxTemp, description) = forecaster.forecast(dayNumber, location.lat, location.long)
    println("forecaster: $place day=$day min=$minTemp max=$maxTemp description=$description")
}

fun moo() = "boo"
