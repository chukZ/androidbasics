import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {
    open val deviceType = "unknown"

    open fun turnOn() {
        println("Smart device is turned on.")
    }

    open fun turnOff() {
        println("Smart device is turned off.")
    }

    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type = $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)
    
    var deviceStatus = "off"
        
    override fun turnOn() {
        super.turnOn()
        println("$name is turned on. Speaker volume is set to $speakerVolume and channel number set to $channelNumber.")
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }

    fun nextChannel() {
        if(deviceStatus == "on") {
            channelNumber++
            println("Channel number increased to $channelNumber")
        }
        else {
            println("unable to increase channel number, $name is turned off")
        }
    }

    fun previousChannel() {
        if(deviceStatus == "on") {
            channelNumber--
            println("Channel number decreased to $channelNumber")
        }
        else {
            println("unable to increase channel number, $name is turned off")
        }
    }

    fun increaseSpeakerVolume() {
        if(deviceStatus == "on") {
            speakerVolume++
            println("Speaker volume increased to $speakerVolume.")
        }
        else{
            println("unable to increase speaker volume, $name is turned off")
        }
    }

    fun decreaseSpeakerVolume() {
        if(deviceStatus == "on") {
            speakerVolume--
            println("Speaker volume decreased to $speakerVolume.")
        }
    	else {
            println("uanble to decrease speaker volume, $name is turned off")
        }
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)
    
    var deviceStatus = "off"

    override fun turnOn() {
        super.turnOn()
        deviceStatus = "on"
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        deviceStatus = "off"
        brightnessLevel = 0
        println("$name turned off.")
    }

    fun increaseBrightness() {
        if(deviceStatus == "on") {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
        }
        else {
            println("unable to increase brightnessLevel, $name is turned off")
        }
    }

    fun decreaseBrightness() {
        if(deviceStatus == "on") {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel.")
        }
        else {
            println("unable to decrease brightness level, $name is turned off")
        }
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    
    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()    
    }

    fun increaseTvVolume() {
      	smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
      	smartTvDevice.nextChannel()
    }    
    
    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()

        println("$smartLightDevice is turned off, unable to increase brightness")
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
       smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    
    // Corrected type assignment
    // smartDevice = SmartLightDevice("Google Light", "Utility")  // This line is causing the error

    // You can create instances of SmartTvDevice or SmartLightDevice separately
    val smartTvDevice = SmartTvDevice("Android TV", "Entertainment")

    // Example of using SmartTvDevice
    smartTvDevice.turnOff()
    smartTvDevice.increaseSpeakerVolume()
    smartTvDevice.nextChannel()

    smartTvDevice.printDeviceInfo()
    
    
    
}