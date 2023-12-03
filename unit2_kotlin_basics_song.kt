class Song (
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int,
)
{	
    
    val isPopular: Boolean
    get() = playCount >= 1000
    
    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished, $title is ${if (isPopular) "popular" else "not popular"}")
    }
}

fun main (){
    val mySong = Song("Example Song", "Example Artist", 2022, 900)
    
    mySong.printSongDescription()
    }
