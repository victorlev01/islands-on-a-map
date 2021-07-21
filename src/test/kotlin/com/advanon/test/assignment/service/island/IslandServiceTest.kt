/*
 * IslandServiceTest.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.service.island

import com.advanon.test.assignment.data.dto.MapDto
import com.advanon.test.assignment.data.enums.TileType
import com.advanon.test.assignment.data.repository.IslandRepository
import com.advanon.test.assignment.data.repository.MapEntityRepository
import com.advanon.test.assignment.service.map.MapService
import com.advanon.test.assignment.service.map.MapServiceConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Answers
import org.mockito.Mockito
import java.util.*

internal class IslandServiceTest {
    private var islandRepository: IslandRepository? = null
    private var islandService: IslandService? = null
    private var mapService: MapService? = null
    @BeforeEach
    fun init() {
        islandRepository = Mockito.mock(IslandRepository::class.java, Answers.RETURNS_DEEP_STUBS)!!
        islandService = IslandService(islandRepository!!)
        mapService = MapService(MapServiceConfiguration(""),
                islandService!!,
                Mockito.mock(MapEntityRepository::class.java)
        )

    }

    @Test
    fun getMapFromDto() {

    }

    fun getMapDto(): MapDto {
        val mapsJSON = this.javaClass.getResource("/response/mapsData.json").readBytes()
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val mapDto = mapper.readValue(mapsJSON, MapDto::class.java)
        return mapDto
    }

    @Test
    fun getIslandsFromTiles() {
        val mapDto = getMapDto()
        val mapEntity = mapService!!.getMapFromDto(mapDto)
        val tiles = mapEntity.tiles!!
        val startNew = System.nanoTime();
        val islands = islandService!!.getIslandsFromTiles(tiles)!!
        println(mapService!!.drawAsciiMap(mapEntity))
        println("get islands from tiles finished in ${System.nanoTime() - startNew} nanoseconds,  islands size = ${islands.size}")
    }

    @Test
    fun getLargeNumberOfTiles() {
        val mapDto = getMapDto()
        val startNew = System.currentTimeMillis();
        val testTiles: ArrayList<MapDto.TileDto> = generateTestTiles()
        val testAttrs = MapDto.AttributesDto(testTiles)
        val newMapDto = MapDto(mapDto.data, testAttrs)
        val mapEntity = mapService!!.getMapFromDto(newMapDto)
        println("get Large Number of islands ${mapEntity.islands!!.size} ," +
                " tiles size=${mapEntity.tiles!!.size}" +
                " completed in ${System.currentTimeMillis() - startNew} millis")
    }

    private fun generateTestTiles(): ArrayList<MapDto.TileDto> {
        val newTiles = ArrayList<MapDto.TileDto>()
        val random = Random()
        val coordinatesMaxVal = 1000
        val min = coordinatesMaxVal / 2
        val randomHeight = (min + Math.random() * ((coordinatesMaxVal - min) + 1)).toInt()
        val randomWidth = (min + Math.random() * ((coordinatesMaxVal - min) + 1)).toInt()
        for (x in 0..randomWidth) {
            for (y in 0..randomHeight) {
                var tileType = TileType.water
                if (random.nextBoolean()) {
                    tileType = TileType.land
                }
                val tileDto = MapDto.TileDto(x = x, y = y, type = tileType)
                newTiles.add(tileDto)
            }
        }
        return newTiles
    }


}


