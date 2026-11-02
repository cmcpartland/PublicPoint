package de.publicpoint.map

import de.publicpoint.isDebugBuild
import org.maplibre.compose.logging.MapLogLevel
import org.maplibre.compose.logging.MapLogRecord
import org.maplibre.compose.logging.MapLogger
import org.maplibre.compose.logging.MapLogging
import org.maplibre.compose.util.DelicateMaplibreComposeApi

private object WarningLogger : MapLogger {
    override val minLevel = MapLogLevel.Warning

    override fun log(record: MapLogRecord) = MapLogging.platformLogger.log(record)
}

@OptIn(DelicateMaplibreComposeApi::class)
internal fun quietMapLogsInRelease() {
    if (!isDebugBuild) MapLogging.logger = WarningLogger
}
