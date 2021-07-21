/*
 * IslandEntity.ktity.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.data.entity

import javax.persistence.*

@Entity(name = "island")
class IslandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @OneToMany(targetEntity = TileEntity::class, cascade = [CascadeType.ALL])
    var tiles: MutableList<TileEntity> = ArrayList()
}

