/*
 * MapEntity.ktity.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.data.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "map")
class MapEntity {
    @Id
    var id: String? = null
    @OneToMany(targetEntity = TileEntity::class, cascade = [CascadeType.ALL])
    var tiles: List<TileEntity>? = null
    @OneToMany(targetEntity = IslandEntity::class, cascade = [CascadeType.ALL])
    var islands: List<IslandEntity>? = null
}