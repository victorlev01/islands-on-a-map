/*
 * MapService.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.service.map

import com.advanon.test.assignment.data.dto.MapDto
import com.advanon.test.assignment.data.entity.MapEntity
import com.advanon.test.assignment.data.entity.TileEntity
import com.advanon.test.assignment.data.enums.TileType
import com.advanon.test.assignment.data.repository.MapEntityRepository
import com.advanon.test.assignment.service.island.IslandService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.transaction.Transactional

@Service
class MapService(val configuration: MapServiceConfiguration,
                 val islandService: IslandService,
                 val mapEntityRepository: MapEntityRepository) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(javaClass)
    }

    val restTemplate = RestTemplate()

    fun getMaps(): MapDto {
        val response = restTemplate.getForObject(configuration.url!!, MapDto::class.java)!!
        logger.debug("getMaps response $response")
        return response
    }

    @Transactional
    fun save(mapDto: MapDto) {
        val responseData = mapDto.data
        val mapId = responseData.id
        val mapType = responseData.type
        assert(mapType.equals("map"));
        val existingMap = mapEntityRepository.findById(mapId)
        if (existingMap.isPresent) {
            logger.debug("map with id = $mapId already exists, deleting previous map...")
            mapEntityRepository.delete(existingMap.get())
        }
        val mapEntity = getMapFromDto(mapDto)
        mapEntityRepository.save(mapEntity)

    }

    fun getMapFromDto(mapResponseDto: MapDto): MapEntity {
        val mapEntity = MapEntity()
        val attributes = mapResponseDto.attributes
        mapEntity.id = mapResponseDto.data.id
        val tiles = ArrayList<TileEntity>()
        attributes.tiles.forEach { tileDto ->
            val tile = getTileFromDto(tileDto)
            tiles.add(tile)
        }
        mapEntity.tiles = tiles
        val islands = islandService.getIslandsFromTiles(tiles)
        mapEntity.islands = islands
        return mapEntity
    }

    fun getTileFromDto(tileDto: MapDto.TileDto): TileEntity {
        val tile = TileEntity()
        tile.type = tileDto.type
        tile.x = tileDto.x
        tile.y = tileDto.y
        return tile;
    }


    fun getAsciiMap(id: String?): String {
        val mapEntity: MapEntity?
        if (id !== null) {
            mapEntity = mapEntityRepository.findById(id).get()
        } else {
            mapEntity = mapEntityRepository.findAll().firstOrNull()
        }
        if (mapEntity === null) {
            throw IllegalArgumentException("map with id = $id not found, you need to create one")
        }
        return drawAsciiMap(mapEntity!!)
    }

    fun drawAsciiMap(mapEntity: MapEntity): String {
        val sb = StringBuilder()
        val tileList = ArrayList<ArrayList<TileEntity>>()
        mapEntity.tiles!!.sortedWith(compareBy({ it.y }, { it.x })).forEach { tile ->
            run {
                val tileY = tile.y!!
                while (tileList.size <= tileY) {
                    tileList.add(ArrayList())
                }
                tileList[tileY].add(tile)
            }
        }

        tileList.forEach { tileArray ->
            run {
                if (!tileArray.isEmpty()) {
                    if (sb.isNotEmpty()) {
                        sb.append("\n")
                    }
                    sb.append("#")
                    tileArray.forEach { tile ->
                        run {
                            sb.append(if (TileType.water === tile.type) "-" else "X")
                        }
                    }
                }
            }
        }
        return sb.toString()
    }
}