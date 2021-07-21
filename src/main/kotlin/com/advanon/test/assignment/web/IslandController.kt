/*
 * IslandController.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.web

import com.advanon.test.assignment.data.entity.IslandEntity
import com.advanon.test.assignment.service.island.IslandService
import org.jetbrains.annotations.NotNull
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/islands")
class IslandController(val islandService: IslandService) {

    @GetMapping(path = [""], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getIslands(): Iterable<IslandEntity> = islandService.getAll()

    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getIslandById(@PathVariable @NotNull id: Long): Optional<IslandEntity> {
        return islandService.findById(id)
    }


}
