import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val input = HttpClient(CIO).get<String>("")
}
