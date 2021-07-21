/*
 * IslandRepository.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.data.repository

import com.advanon.test.assignment.data.entity.IslandEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface IslandRepository : PagingAndSortingRepository<IslandEntity, Long> {

}
