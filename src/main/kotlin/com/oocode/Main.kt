import com.oocode.AcmeForecaster
import org.http4k.client.JavaHttpClient
import java.time.DayOfWeek

fun main(args: Array<String>) {
    if (args.size != 2) {
        throw RuntimeException("Must specify Day and Place")
    }
    val dayOfWeek = DayOfWeek.valueOf(args[0].uppercase())
    printForecast(dayOfWeek, args[1])
    printForecast(dayOfWeek, args[1])
    printForecast(dayOfWeek, args[1])
}

private fun printForecast(dayOfWeek: DayOfWeek, place: String) {
    val acmeForecast = AcmeForecaster(JavaHttpClient()).forecast(dayOfWeek, place)

    val emoji =
        if (acmeForecast.min < 5) {
            "❄️"
        } else {
            if (acmeForecast.max < 15) {
                "🧥"
            } else {
                "🔥"
            }
        }
    println(message(emoji, acmeForecast.description, acmeForecast.min, acmeForecast.max))
}

private fun message(emoji: String, description: String, min: Int, max: Int) =
    "$description $emoji Expect temperatures in the range $min-${max}°C."

fun moo() = "boo"
