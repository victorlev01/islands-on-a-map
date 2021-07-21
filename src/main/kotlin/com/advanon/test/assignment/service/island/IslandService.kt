/*
 * IslandService.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.service.island

import com.advanon.test.assignment.data.entity.IslandEntity
import com.advanon.test.assignment.data.entity.TileEntity
import com.advanon.test.assignment.data.enums.TileType
import com.advanon.test.assignment.data.repository.IslandRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

@Service
class IslandService(val islandRepository: IslandRepository) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(javaClass)
        val searchRangeX = intArrayOf(-1, 0, 1, 0)
        val searchRangeY = searchRangeX.reversed()
    }

    fun getAll(): Iterable<IslandEntity> {
        return islandRepository.findAll()
    }

    fun findById(id: Long): Optional<IslandEntity> {
        return islandRepository.findById(id)
    }

    fun getIslandsFromTiles(tiles: List<TileEntity>): List<IslandEntity>? {
        val landTiles: MutableMap<Pair<Int, Int>, TileEntity> = HashMap()
        tiles.forEach { tile ->
            run {
                if (TileType.land == tile.type) {
                    landTiles[tile.x!! to tile.y!!] = tile
                }
            }
        }
        val islands = ArrayList<IslandEntity>()
        val coordinatesSet: HashSet<Pair<Int, Int>> = HashSet()
        coordinatesSet.addAll(landTiles.keys)
        for (key in coordinatesSet) {
            if (landTiles.containsKey(key)) {
                val tile = landTiles[key]!!
                val island = IslandEntity()
                island.tiles = arrayListOf(tile)
                islands.add(island)
                landTiles.remove(key)
                mergeAdjacentLand(tile, island, landTiles)
            }
        }
        logger.debug("filtered islands {}", islands)
        return islands
    }

    private fun mergeAdjacentLand(tile: TileEntity,
                                  island: IslandEntity,
                                  landTiles: MutableMap<Pair<Int, Int>, TileEntity>) {
        val tileX = tile.x!!
        val tileY = tile.y!!
        for ((index, xOffset) in searchRangeX.withIndex()) {
            val yOffset = searchRangeY[index]
            val coordinates = Pair(tileX + xOffset, tileY + yOffset)
            if (landTiles.containsKey(coordinates)) {
                val adjacentTile = landTiles[coordinates]!!
                island.tiles.add(adjacentTile)
                landTiles.remove(coordinates)
                mergeAdjacentLand(adjacentTile, island, landTiles)
            }
        }
    }
}