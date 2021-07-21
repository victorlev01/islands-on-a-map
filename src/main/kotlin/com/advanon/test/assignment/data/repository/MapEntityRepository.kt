/*
 * MapEntityRepository.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.data.repository

import com.advanon.test.assignment.data.entity.MapEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MapEntityRepository : PagingAndSortingRepository<MapEntity, String> {

}
