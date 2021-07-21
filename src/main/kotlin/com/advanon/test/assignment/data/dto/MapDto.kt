/*
 * MapDto.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.data.dto

import com.advanon.test.assignment.data.enums.TileType

data class MapDto(
        val data: DataDto,
        val attributes: AttributesDto
) {
    data class AttributesDto(
            val tiles: List<TileDto>
    )

    data class TileDto(
            val x: Int,
            val y: Int,
            val type: TileType
    )

    data class DataDto(
            val id: String,
            val type: String,
            val links: LinksDto
    )

    data class LinksDto(
            val self: String
    )
}

