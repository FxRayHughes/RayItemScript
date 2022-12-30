package ray.mintcat.rayitemscript

import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.actions.*
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.getDataFolder
import taboolib.module.configuration.*
import taboolib.module.configuration.util.getStringColored
import taboolib.module.configuration.util.getStringListColored
import java.io.File

object Loader {

    val files = ArrayList<File>()
    val configs = ArrayList<Configuration>()

    @Config("group/main.yml")
    lateinit var exp: ConfigFile

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        files.clear()
        configs.clear()
        RayItemScript.script.clear()
        loadFile(File(getDataFolder(), "group/"))
        loadConfig()
        create()
    }

    fun create() {
        configs.forEach { config ->
            build(config)
        }
    }

    fun build(configuration: Configuration) {
        //读取配置文件
        configuration.getKeys(false).forEach { key ->
            val lore = configuration.getStringListColored("${key}.item.lore")
            if (lore.isEmpty()) {
                return@forEach
            }
            val item = ItemData(
                lore,
                configuration.getString("${key}.item.nbt_key"),
                configuration.getString("${key}.item.nbt_value"),
                configuration.getString("${key}.item.slot"),
            )
            val listener = ListenerData(
                configuration.getBoolean("${key}.listener.any", false),
                configuration.getStringList("${key}.listener.action")
            )
            val consume = ConsumeData(
                configuration.getInt("${key}.consume.amount", 1),
                configuration.getBoolean("${key}.consume.take", false),
                configuration.getString("${key}.consume.message")
            )
            val conditions = ConditionsData(
                configuration.getInt("${key}.conditions.probability.value", 100),
                configuration.getString("${key}.conditions.probability.message"),
                configuration.getString("${key}.conditions.permissions.value"),
                configuration.getString("${key}.conditions.permissions.message"),
                configuration.getStringList("${key}.conditions.kether")
            )
            val cooldown = CooldownData(
                configuration.getString("${key}.cooldown.group"),
                configuration.getLong("${key}.cooldown.time"),
                configuration.getString("${key}.cooldown.message")
            )
            val action = configuration.getStringList("${key}.action")
            RayItemScript.script.add(
                ScriptData(
                    key,
                    listener, item, consume, cooldown, conditions, action
                )
            )
        }
    }

    fun loadConfig() {
        files.forEach {
            configs.add(Configuration.loadFromFile(it, type = Type.YAML))
        }
    }

    fun loadFile(file: File) {
        if (file.isFile) {
            files.add(file)
        } else {
            file.listFiles()?.forEach {
                loadFile(it)
            }
        }
    }

}